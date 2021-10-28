package seedu.address.model.table;

public class Table {
    private static int numberOfTables = 0;

    private final int tableId;
    private final int numOfSeats;

    /**
     * Constructs a new Table object with unique ID and specified number of seats
     * @param numOfSeats number of seats the table has
     */
    public Table(int numOfSeats) {
        this.numOfSeats = numOfSeats;
        numberOfTables++;
        tableId = numberOfTables;
    }

    /**
     * Constructs a new Table object with specified ID and specified number of seats
     * @param numOfSeats number of seats the table has
     * @param tableId unique ID of table
     */
    public Table(int numOfSeats, int tableId) {
        this.numOfSeats = numOfSeats;
        this.tableId = tableId;
    }

    public boolean canFit(int numOfCustomers) {
        return numOfCustomers <= numOfSeats;
    }

    public int getTableId() {
        return tableId;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    /**
     * Compare this table size with another table size. If equal, then compare their table ID.
     * @param other other table to compare to
     * @return positive integer if this table size is bigger than the other table being compared to, or if equal size,
     * positive integer if this table ID is bigger than the other
     */
    public int compareTableSize(Table other) {
        return numOfSeats - other.numOfSeats != 0
                ? numOfSeats - other.numOfSeats
                : tableId - other.tableId;
    }

    public static void resetTableCount() {
        numberOfTables = 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Table // instanceof handles nulls
                && tableId == (((Table) other).tableId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("TableId: %1$s, Table size: %2$s", tableId, numOfSeats);
    }
}