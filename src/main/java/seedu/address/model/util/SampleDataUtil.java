package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.person.passenger.TripDay;
import seedu.address.model.person.passenger.TripTime;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    /**
     * Prevents SampleDataUtil from being instantiated.
     */
    private SampleDataUtil() {}

    public static Passenger[] getSamplePassengers() {

        //todo possibly change prices entered
        return new Passenger[] {
            new Passenger(new Name("Alex Yeoh"), new Phone("87438807"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new TripDay("monday"), new TripTime("1400"),
                new Price("1.69"),
                getTagSet("friends")),
            new Passenger(new Name("Bernice Yu"), new Phone("99272758"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new TripDay("wednesday"), new TripTime("2130"),
                new Price("1.69"),
                getTagSet("colleagues", "friends")),
            new Passenger(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new TripDay("thursday"), new TripTime("0845"),
                new Price("1.69"),
                getTagSet("neighbours")),
            new Passenger(new Name("David Li"), new Phone("91031282"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new TripDay("friday"), new TripTime("1305"),
                new Price("1.69"),
                getTagSet("family")),
            new Passenger(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new TripDay("tuesday"), new TripTime("1930"),
                new Price("1.69"),
                getTagSet("classmates")),
            new Passenger(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new TripDay("monday"), new TripTime("0915"),
                new Price("1.69"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Passenger samplePassenger : getSamplePassengers()) {
            sampleAb.addPassenger(samplePassenger);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
