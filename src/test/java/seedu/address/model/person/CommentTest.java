package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommentTest {
    @Test
    public void equals() {
        // same object -> returns true
        Comment comment = new Comment("Test comment");
        assertEquals(comment, comment);

        // null -> returns false
        assertNotEquals(comment, null);

        // different type -> returns false
        assertNotEquals(comment, 5);

        // different comment -> returns false
        Comment differentComment = new Comment("Different comment");
        assertNotEquals(comment, differentComment);

        // same comment -> returns true
        Comment sameComment = new Comment("Test comment");
        Comment sameComment2 = new Comment("Test comment");
        assertEquals(sameComment, sameComment2);
    }

    @Test
    public void isValidComment() {
        // no comment -> returns false
        assertFalse(Comment.isValidComment(""));

        // invalid comment -> returns false
        assertFalse(Comment.isValidComment(" "));

        // valid comment -> returns true
        assertTrue(Comment.isValidComment("Test comment"));
    }
}
