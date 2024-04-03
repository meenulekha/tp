package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventCategory;

public class EventCard extends UiPart<Region>{
    private static final String FXML = "EventListCard.fxml";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     * issue on AddressBook level 4</a>
     */

    public final Event event;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label eventname;
    @FXML
    private Label eventdate;
    @FXML
    private Label eventcategory;
    @FXML
    private Label id;
    /**
     * Creates a {@code EventCode} with the given {@code event} and index to
     * display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + "");
        eventname.setText(event.getEventName().eventName);
        eventdate.setText(event.getEventDate().eventDate);
        eventcategory.setText(event.getEventCategory().value);
        eventcategory.getStyleClass().add(getCategoryStyleClass(event.getEventCategory()));
    }

    private String getCategoryStyleClass(EventCategory category) {
        return "category-label-" + category.value.toLowerCase();
    }




}
