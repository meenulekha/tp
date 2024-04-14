package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT1;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.UndoException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandTest {
    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        EventCommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);
        assertTrue(modelStub.eventsAdded.contains(validEvent));
        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent),
                commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class,
                AddEventCommand.MESSAGE_DUPLICATE_EVENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void undo_validEvent_success() throws CommandException, UndoException {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();
        AddEventCommand addCommand = new AddEventCommand(validEvent);

        // Execute the add command
        addCommand.execute(modelStub);

        // Undo the add command
        CommandResult undoResult = addCommand.undo(modelStub);

        // Verify that the event is removed from the model
        assertFalse(modelStub.hasEvent(validEvent));
        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS_UNDO, validEvent),
                undoResult.getFeedbackToUser());
    }

    @Test
    public void undo_nonExistentEvent_throwsCommandException() {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event nonExistentEvent = new EventBuilder().build();
        AddEventCommand addCommand = new AddEventCommand(nonExistentEvent);

        // Undo the add command for a non-existent event
        String expectedMessage = AddEventCommand.MESSAGE_UNDO_NONEXISTENT_EVENT;
        assertThrows(UndoException.class, expectedMessage, () -> addCommand.undo(modelStub));
    }

    @Test
    public void redo_validEvent_success() throws CommandException, UndoException {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();
        AddEventCommand addCommand = new AddEventCommand(validEvent);

        // Execute the add command
        addCommand.execute(modelStub);

        // Undo the add command
        addCommand.undo(modelStub);

        // Redo the add command
        CommandResult redoResult = addCommand.redo(modelStub);

        // Verify that the event is added back to the model
        assertTrue(modelStub.hasEvent(validEvent));
        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent),
                redoResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Event eventOne = new EventBuilder().withEventName("event 1").build();
        Event eventTwo = new EventBuilder().withEventName("event 2").build();
        AddEventCommand addEventOneCommand = new AddEventCommand(eventOne);
        AddEventCommand addEventTwoCommand = new AddEventCommand(eventTwo);

        // same object -> returns true
        assertTrue(addEventOneCommand.equals(addEventOneCommand));

        // same values -> returns true
        AddEventCommand addEventOneCommandCopy = new AddEventCommand(eventOne);
        assertTrue(addEventOneCommand.equals(addEventOneCommandCopy));

        // different types -> returns false
        assertFalse(addEventOneCommand.equals(1));

        // null -> returns false
        assertFalse(addEventOneCommand.equals(null));

        // different event -> returns false
        assertFalse(addEventOneCommand.equals(addEventTwoCommand));
    }

    @Test
    public void toStringMethod() {
        AddEventCommand addEventCommand = new AddEventCommand(EVENT1);
        String expected = AddEventCommand.class.getCanonicalName() + "{toAdd=" + EVENT1 + "}";
        assertEquals(expected, addEventCommand.toString());
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            return this.event.isSameEvent(event);
        }

        @Override
        public void addCommand(ReversibleCommand command) {
            return;
        }
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public void deleteEvent(Event target) {
            eventsAdded.remove(target);
        }

    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void groupPerson(Person target, int groupNumber) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CommandResult undoAddressBook() throws UndoException {
            return null;
        }

        @Override
        public CommandResult redoAddressBook() throws UndoException {
            return null;
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCommand(ReversibleCommand command) {
            return;
        }
        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEventBook getEventBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getEventBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBookFilePath(Path eventBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBook(ReadOnlyEventBook eventBook) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
