package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during undoing a {@link Command}.
 */
public class UndoException extends Exception {
    public UndoException(String message) {
        super(message);
    }
}
