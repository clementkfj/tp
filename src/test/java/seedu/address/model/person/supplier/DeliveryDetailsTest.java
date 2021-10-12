package seedu.address.model.person.supplier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeliveryDetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeliveryDetails(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDeliveryDetail = "";
        assertThrows(IllegalArgumentException.class, () -> new DeliveryDetails(invalidDeliveryDetail));
    }

    @Test
    public void isValidDeliveryDetail() {
        // null name
        assertThrows(NullPointerException.class, () -> DeliveryDetails.isValidDeliveryDetail(null));

        // invalid supply type
        assertFalse(DeliveryDetails.isValidDeliveryDetail("")); // empty string
        assertFalse(DeliveryDetails.isValidDeliveryDetail(" ")); // spaces only
        assertFalse(DeliveryDetails.isValidDeliveryDetail("^")); // only non-alphanumeric characters
        assertFalse(DeliveryDetails.isValidDeliveryDetail("2nd March 18:00")); // contains non-alphanumeric characters

        // valid name
        assertTrue(DeliveryDetails.isValidDeliveryDetail("next week monday")); // alphabets only
        assertTrue(DeliveryDetails.isValidDeliveryDetail("1800")); // numbers only
        assertTrue(DeliveryDetails.isValidDeliveryDetail("2nd march 6pm")); // alphanumeric characters
        assertTrue(DeliveryDetails.isValidDeliveryDetail("October 27th 8am")); // with capital letters
        assertTrue(DeliveryDetails.isValidDeliveryDetail("27th October at around 2pm to 4 pm")); // long names
    }
}