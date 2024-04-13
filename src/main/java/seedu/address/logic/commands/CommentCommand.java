package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Category;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonFactory;
import seedu.address.model.person.Phone;

/**
 * Comments an existing person in the address book.
 */
public class CommentCommand extends Command implements ReversibleCommand {

    public static final String COMMAND_WORD = "comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the comment of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) " + "COMMENT\n"
            + "Example: " + COMMAND_WORD + " 1 " + "This is a comment.";

    public static final String MESSAGE_COMMENT_PERSON_SUCCESS = "Commented Person: %1$s";
    public static final String MESSAGE_SUCCESS_UNDO = "Changes reverted: %1$s";

    private final Index index;
    private final Comment editComment;

    private Person originalPerson;
    private Person editedPerson;

    /**
     * @param index of the person in the filtered person list to comment
     * @param editComment comment to the person
     */
    public CommentCommand(Index index, Comment editComment) {
        requireNonNull(index);
        requireNonNull(editComment);
        this.index = index;
        this.editComment = editComment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editComment);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        this.originalPerson = personToEdit;
        this.editedPerson = editedPerson;

        model.addCommand(this);
        return new CommandResult(String.format(MESSAGE_COMMENT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);

        model.setPerson(editedPerson, originalPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(CommentCommand.MESSAGE_SUCCESS_UNDO, Messages.format(originalPerson)));
    }

    @Override
    public CommandResult redo(Model model) throws CommandException {
        requireNonNull(model);

        model.setPerson(originalPerson, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_COMMENT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     */
    private static Person createEditedPerson(Person personToEdit, Comment comment) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Category category = personToEdit.getCategory();
        Group group = personToEdit.getGroup();

        return PersonFactory.createPerson(name, phone, email, category, group, comment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommentCommand)) {
            return false;
        }

        CommentCommand otherCommentCommand = (CommentCommand) other;
        return index.equals(otherCommentCommand.index)
                && editComment.equals(otherCommentCommand.editComment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("index", index)
                .add("editComment", editComment).toString();
    }
}
