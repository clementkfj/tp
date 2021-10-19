package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.reservation.Reservation;

public class ReservationCard extends UiPart<Region> {
    public static final String FXML = "ReservationListCard.fxml";
    private static final DateTimeFormatter DATE_TIME_PRINTING_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Reservation reservation;

    @FXML
    private HBox cardPane;

    @FXML
    private Label id;

    @FXML
    private Label phone;

    @FXML
    private Label numberOfPeople;

    @FXML
    private Label dateTime;

    /**
     * Creates a {@code ReservationCard} with the given {@code Reservation} and index to display.
     */
    public ReservationCard(Reservation reservation, int displayedIndex) {
        super(FXML);
        this.reservation = reservation;
        id.setText(displayedIndex + ". ");
        phone.setText("Phone: " + reservation.getPhone().value);
        numberOfPeople.setText(String.format("Table for %d", reservation.getNumberOfPeople()));
        dateTime.setText("Date and Time: " + reservation.getDateTime().format(DATE_TIME_PRINTING_FORMAT));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReservationCard)) {
            return false;
        }

        // state check
        ReservationCard that = (ReservationCard) other;
        return id.getText().equals(that.id.getText())
                && reservation.equals(that.reservation);
    }
}