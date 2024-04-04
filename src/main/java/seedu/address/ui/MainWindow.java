package seedu.address.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.inputhistory.UserInputHistory;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing a menu bar
 * and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    private List<EventHandler<KeyEvent>> keyPressHandlers;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem eventListMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.keyPressHandlers = new ArrayList<>();
        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = HelpWindow.get();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    private void clearAccelerators() {
        helpMenuItem.setAccelerator(null);
        eventListMenuItem.setAccelerator(null);
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666 is fixed in later version of
         * SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will not
         * work when the focus is in them because the key event is consumed by the
         * TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is in
         * CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Sets the key press handler for the given key combination. This is a
     * workaround for the bug reported here
     * https://bugs.openjdk.java.net/browse/JDK-8131666.
     *
     * According to the bug report, TextInputControl (TextField, TextArea) will
     * consume function-key events. Because CommandBox contains a TextField, and
     * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will not
     * work when the focus is in them because the key event is consumed by the
     * TextInputControl(s).
     *
     * For now, we add following event filter to capture such key events and open
     * help window purposely so to support accelerators even when focus is in
     * CommandBox or ResultDisplay.
     *
     * @param func    the function to be executed when the key combination is
     *                pressed
     * @param keyComb the key combination
     * @return the event handler
     */
    private void setKeyPressHandler(Runnable func, KeyCombination keyComb) {
        EventHandler<KeyEvent> handler = event -> {
            if (keyComb.match(event)) {
                if (event.getTarget() instanceof TextInputControl) {
                    event.consume();
                }
                func.run();
            }
        };
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, handler);
        keyPressHandlers.add(handler);
    }

    private void clearKeyPressHandler() {
        keyPressHandlers.forEach(handler -> getRoot().removeEventFilter(KeyEvent.KEY_PRESSED, handler));
        keyPressHandlers.clear();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        logger.info("PersonListPanel: " + personListPanel);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        // set focus trigger for person list panel to F4
        setKeyPressHandler(personListPanel::focus, KeyCombination.valueOf("F4"));

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        // set focus trigger for result display to F3
        setKeyPressHandler(resultDisplay::focus, KeyCombination.valueOf("F3"));

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        UserInputHistory<String> history = logic.getUserInputHistory();
        CommandBox commandBox = new CommandBox(this::executeCommand, history::getPreviousChat, history::getNextChat,
                history::addChatToHistory);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        // set focus trigger for command box to F2
        setKeyPressHandler(commandBox::focus, KeyCombination.valueOf("F2"));
    }

    /**
     * Clear all the placeholders of this window.
     */
    private void removeInnerParts() {
        personListPanelPlaceholder.getChildren().clear();
        resultDisplayPlaceholder.getChildren().clear();
        statusbarPlaceholder.getChildren().clear();
        commandBoxPlaceholder.getChildren().clear();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /** Navigates to the Event Window */
    private void showEventWindow() throws IOException {
        // Clean up
        clearAccelerators();
        clearKeyPressHandler();
        removeInnerParts();

        // Close the current window
        primaryStage.close();

        // Create a new MainWindow
        EventWindow eventWindow = new EventWindow(primaryStage, logic);

        // Show the MainWindow
        eventWindow.show();
        eventWindow.fillInnerParts();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    @FXML
    void handleEvents() {
        primaryStage.getIcons();
        try {
            showEventWindow();
        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
        }
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
