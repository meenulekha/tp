package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.EventBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventCategory;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventFactory;
import seedu.address.model.event.EventName;
import seedu.address.model.person.Category;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonFactory;
import seedu.address.model.person.Phone;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
                PersonFactory.createPerson(new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), new Category("PARTICIPANT")),
                PersonFactory.createPerson(new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"), new Category("PARTICIPANT")),
                PersonFactory.createPerson(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@example.com"), new Category("PARTICIPANT")),
                PersonFactory.createPerson(new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@example.com"), new Category("PARTICIPANT")),
                PersonFactory.createPerson(new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@example.com"), new Category("PARTICIPANT")),
                PersonFactory.createPerson(new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@example.com"), new Category("PARTICIPANT")),
                PersonFactory.createPerson(new Name("Gogle"), new Phone("12345678"),
                        new Email("gogle@example.com"), new Category("SPONSOR")),
                PersonFactory.createPerson(new Name("Facebok"), new Phone("87654321"),
                        new Email("facebok@example.com"), new Category("SPONSOR")),
                PersonFactory.createPerson(new Name("Twiter"), new Phone("87654322"),
                        new Email("twiter@example.com"), new Category("SPONSOR")),
                PersonFactory.createPerson(new Name("Peter Lee"), new Phone("7171717"),
                        new Email("peterlee@example.com"), new Category("STAFF"))};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }


    public static Event[] getSampleEvents() {
        return new Event[]{
                EventFactory.createEvent(new EventName("Meeting"), new EventDate("23/05/2024"),
                        new EventCategory("PARTICIPANT")),
                EventFactory.createEvent(new EventName("Conference"), new EventDate("24/05/2024"),
                        new EventCategory("STAFF")),
                EventFactory.createEvent(new EventName("Seminar"), new EventDate("25/05/2024"),
                        new EventCategory("SPONSOR")),
                EventFactory.createEvent(new EventName("Workshop"), new EventDate("27/05/2024"),
                        new EventCategory("PARTICIPANT")),
                EventFactory.createEvent(new EventName("Training"), new EventDate("01/06/2024"),
                        new EventCategory("PARTICIPANT")),
                EventFactory.createEvent(new EventName("Lecture"), new EventDate("02/06/2024"),
                        new EventCategory("PARTICIPANT")),
                EventFactory.createEvent(new EventName("Meeting 2"), new EventDate("06/06/2024"),
                        new EventCategory("STAFF"))
        };

    }

    public static ReadOnlyEventBook getSampleEventBook() {
        EventBook sampleEb = new EventBook();
        for (Event sampleEvent : getSampleEvents()) {
            sampleEb.addEvent(sampleEvent);
        }
        return sampleEb;

    }
}


