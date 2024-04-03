package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;

public interface ReadOnlyEventBook {
    ObservableList<Event> getEventList();
}
