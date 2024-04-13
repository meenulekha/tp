//package seedu.address.logic.commands;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
//
///**
// * Contains integration tests (interaction with the Model) and unit tests for ListEventCommand.
// */
//public class ListEventCommandTest {
// ;
////    private Model model;
////    private Model expectedModel;
////
////    @BeforeEach
////    public void setUp() {
////        model = new ModelManager(getTypicalEventBook(), new UserPrefs());
////        expectedModel = new ModelManager(model.getEventBook(), new UserPrefs());
////    }
////
////    @Test
////    public void execute_listIsNotFiltered_showsSameList() {
////        assertCommandSuccess(new ListEventCommand(), model, ListEventCommand.MESSAGE_SUCCESS, expectedModel);
////    }
////
////    @Test
////    public void execute_listIsFiltered_showsEverything() {
////        // No need to show any specific event, as we're testing the behavior when the list is filtered
////        assertCommandSuccess(new ListEventCommand(), model, ListEventCommand.MESSAGE_SUCCESS, expectedModel);
////    }
////}
//
//
