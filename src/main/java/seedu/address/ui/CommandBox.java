package seedu.address.ui;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private Consumer<String> commandToHistorySaver = (commandText) -> {
    };

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the
        // command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor} and 2
     * suppliers for the previous and next command.
     *
     * @param commandExecutor         The command executor.
     * @param previousCommandSupplier The supplier for the previous command.
     * @param nextCommandSupplier     The supplier for the next command.
     * @param commandToHistorySaver   The consumer for saving the command to
     *                                history.
     */
    public CommandBox(CommandExecutor commandExecutor, Supplier<String> previousCommandSupplier,
            Supplier<String> nextCommandSupplier, Consumer<String> commandToHistorySaver) {
        this(commandExecutor);
        setArrowKeyHandler(previousCommandSupplier, nextCommandSupplier);
        this.commandToHistorySaver = commandToHistorySaver;
    }

    /**
     * Sets the action handler for arrow key events. This handler will cycle through
     * the command history.
     */
    private void setArrowKeyHandler(Supplier<String> previousCommandSupplier, Supplier<String> nextCommandSupplier) {
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
            case UP:
                setNullableUserInput(previousCommandSupplier);
                event.consume();
                break;
            case DOWN:
                setNullableUserInput(nextCommandSupplier);
                event.consume();
                break;
            default:
                // let the event pass
            }
        });
    }

    /**
     * Focuses on the command box.
     */
    public void focus() {
        commandTextField.requestFocus();
    }

    /**
     * Sets the user input to the given supplier's result if it is not null.
     *
     * @param supplier The supplier to get the user input from.
     */
    private void setNullableUserInput(Supplier<String> supplier) {
        String result = supplier.get();
        if (result == null) {
            return;
        }
        commandTextField.setText(result);
        commandTextField.positionCaret(result.length());
    }

    /**
     * Saves the command to history.
     *
     * @param commandText The command text to be saved.
     */
    private void saveCommandToHistory(String commandText) {
        commandToHistorySaver.accept(commandText);
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }
        saveCommandToHistory(commandText);

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
