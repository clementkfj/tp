package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ShiftTest {

    public static final DateTimeFormatter VALID_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final String INVALID_FORMAT = "2021-12-12 12:00";
    public static final String INVALID_FORMAT2 = "12-12-2021 1200";
    public static final String INVALID_MONTH = "2021-13-20 0700";
    public static final String INVALID_DAY = "2021-12-40 0800";
    public static final String INVALID_TIME = "2021-12-12 2800";
    public static final String VALID_SHIFT = "2021-12-08 0800";
    public static final String VALID_SHIFT2 = "2021-11-11 1700";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Shift(null));
    }

    @Test
    public void constructor_invalidShiftName_throwsIllegalArgumentException() {
        String invalidShiftName = "";
        assertThrows(IllegalArgumentException.class, () -> new Shift(invalidShiftName));
    }

    @Test
    public void isValidShift_null() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Shift.isValidShift(null));
    }

    @Test
    public void isValidShift_success() {
        // valid shift using regex
        assertTrue(Shift.isValidShift(VALID_SHIFT));
        assertTrue(Shift.isValidShift(VALID_SHIFT2));

        // valid shift using formatter
        assertTrue(Shift.isValidShift(VALID_SHIFT, VALID_DATE_TIME_FORMATTER));
        assertTrue(Shift.isValidShift(VALID_SHIFT2, VALID_DATE_TIME_FORMATTER));
    }

    @Test
    public void isValidShiftInvalidMonth_failure() {
        // invalid month
        assertFalse(Shift.isValidShift(INVALID_MONTH));
        assertFalse(Shift.isValidShift(INVALID_MONTH, VALID_DATE_TIME_FORMATTER));
    }

    @Test
    public void isValidShiftInvalidDay_failure() {
        // invalid day
        assertFalse(Shift.isValidShift(INVALID_DAY));
        assertFalse(Shift.isValidShift(INVALID_DAY, VALID_DATE_TIME_FORMATTER));
    }

    @Test
    public void isValidShiftInvalidTime_failure() {
        // invalid time
        assertFalse(Shift.isValidShift(INVALID_TIME));
        assertFalse(Shift.isValidShift(INVALID_TIME, VALID_DATE_TIME_FORMATTER));
    }

    @Test
    public void isValidShiftInvalidFormat_failure() {
        // HH:mm instead of HHmm
        assertFalse(Shift.isValidShift(INVALID_FORMAT));
        assertFalse(Shift.isValidShift(INVALID_FORMAT2));

        // wrong order
        assertFalse(Shift.isValidShift(INVALID_FORMAT, VALID_DATE_TIME_FORMATTER));
        assertFalse(Shift.isValidShift(INVALID_FORMAT2, VALID_DATE_TIME_FORMATTER));
    }

}