package seedu.address.ui;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.EventCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * The Event Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class EventWindow extends UiPart<Stage> {
    private static final String FXML = "EventWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private HelpWindow helpWindow;

    @FXML
    private MenuItem backMenuItem;
    @FXML
    private ResultDisplay eventResultDisplay;
    @FXML
    private StackPane eventResultDisplayPlaceholder;
    @FXML
    private EventListPanel eventListPanel;
    @FXML
    private StackPane eventListPanelPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;
    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private MenuItem helpMenuItem;

    private Logic logic;

    /**
     * Constructs an EventWindow with the specified primaryStage and logic.
     *
     * @param primaryStage The primary stage for the EventWindow.
     * @param logic        The logic component responsible for handling application logic.
     * @throws IllegalArgumentException if logic is null.
     */
    public EventWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        this.primaryStage = primaryStage;

        if (logic == null) {
            throw new IllegalArgumentException("Logic cannot be null");
        }
        this.logic = logic;
        this.helpWindow = new HelpWindow();

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();
        fillInnerParts();

    }
    /**
     * Handles the action event triggered when the user requests help.
     * If the help window is not already showing, it will be shown.
     * If the help window is already showing, it will be brought to focus.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    @FXML
    private void handleBack() {

        try {
            primaryStage.close();
            showMainWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }


    void fillInnerParts() {
        EventListPanel eventListPanel = new EventListPanel(logic.getFilteredEventList());
        eventListPanelPlaceholder.getChildren().add(eventListPanel.getRoot());
        if (eventResultDisplay == null) {
            eventResultDisplay = new ResultDisplay();
            eventResultDisplayPlaceholder.getChildren().add(eventResultDisplay.getRoot());
        }

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        EventCommandBox commandBox = new EventCommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }

    }
    void show() {
        primaryStage.show();
    }

    private void showMainWindow() throws IOException {
        // Close the current window
        primaryStage.close();

        // Create a new MainWindow
        MainWindow mainWindow = new MainWindow(primaryStage, logic);

        // Show the MainWindow
        mainWindow.show();
        mainWindow.fillInnerParts();
    }

    public EventListPanel getEventListPanel() {
        return eventListPanel;
    }
    private EventCommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            if (logic == null) {
                throw new IllegalStateException("Logic is not properly initialized");
            }
            EventCommandResult eventCommandResult = logic.executeEvent(commandText);
            logger.info("Result: " + eventCommandResult.getFeedbackToUser());
            if (eventResultDisplay == null) {
                throw new IllegalStateException("eventResultDisplay is not properly initialized");
            }
            eventResultDisplay.setFeedbackToUser(eventCommandResult.getFeedbackToUser());

            if (eventCommandResult.isShowHelp()) {
                handleHelp();
            }

            if (eventCommandResult.isExit()) {
                handleExit();
            }

            if (eventCommandResult.isBack()) {
                handleBack();
            }

            return eventCommandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            if (eventResultDisplay != null) {
                eventResultDisplay.setFeedbackToUser(e.getMessage());
            }
            eventResultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
