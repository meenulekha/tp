package seedu.address.model.Event.Exceptions;

public class DuplicateEventException extends RuntimeException {
    public DuplicateEventException() {
        super("Operation would result in duplicate events");
    }
}
