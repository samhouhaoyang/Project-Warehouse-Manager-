/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au
 */

/**
 * Stores constant values used throughout the warehouse manager program.
 */

public final class Constants {

    public static final String[] DEFAULT_ITEM_NAMES = {
            "Box", "Pallet", "Monitor", "Keyboard", "Chair",
            "Cable", "Book", "Toolkit", "Printer", "Router"
    };

    public static final String HISTORY_HEADER_FORMATTER =
            "| %9s | %-15s | %-10s | %-5s | %-5s | %-19s |\n";

    public static final String HISTORY_ROW_FORMATTER =
            "| %9d | %-15s | %-10s | %-5d | %-5d | %-19s |\n";

    public static final String HISTORY_DIVIDER =
            "|===========|=================|============|=======|=======|=====================|";

    public static final int MIN_SHELVES = 1;
    public static final int MIN_RESTRICTED = 1;

    public static final int MIN_ITEMS_PER_SHELF = 1;
    public static final int MAX_ITEMS_PER_SHELF = 4;

    public static final char WALL = '#';
    public static final char AISLE = '.';
    public static final char RESTRICTED = 'X';
    public static final char SHELF = 'S';
    public static final char START = 'O';
    public static final char FORKLIFT = 'F';

    public static final int BOUNDARY_THICKNESS = 2;
    public static final int START_OFFSET = 1;

    public static final int START_COL = 1;
    public static  final int START_ROW = 1;

    public static final int INITIAL_CAPACITY = 10;

    public static final String INVALID_INPUT = "Invalid input.";
    public static final String NO_ITEMS_ON_SHELF = "No items on this shelf.";
    public static final String ITEM_PICKED_SUCCESSFULLY = "Item picked successfully.";
    public static final String ITEM_DELIVERED_SUCCESSFULLY = "Item delivered successfully.";
}
