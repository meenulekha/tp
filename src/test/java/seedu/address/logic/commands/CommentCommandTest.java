package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class CommentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
    @Test
    public void execute_comment_success() throws CommandException {
        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_PERSON, new Comment("This is a comment."));
        commentCommand.execute(model);
        assertEquals(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getComment(),
                new Comment("This is a comment."));
    }

    @Test
    public void execute_no_comment() {
        Person person = new PersonBuilder().build();
        Comment comment = new Comment("No comment provided.");
        assertEquals(comment, person.getComment());
    }

    @Test
    public void undo() throws CommandException {
        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_PERSON, new Comment("This is a comment."));
        commentCommand.execute(model);
        commentCommand.undo(model);
        assertEquals(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getComment(),
                new Comment("She is an Expert."));
    }

    @Test
    public void redo() throws CommandException {
        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_PERSON, new Comment("This is a comment."));
        commentCommand.execute(model);
        commentCommand.undo(model);
        commentCommand.redo(model);
        assertEquals(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getComment(),
                new Comment("This is a comment."));
    }
}
