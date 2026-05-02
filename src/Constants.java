/**
 Student Name -
 Student Id -
 Student email -
 */

public final class Constants {

    public static final String[] DEFAULT_ITEM_NAMES = {
            "Box", "Pallet", "Monitor", "Keyboard", "Chair",
            "Cable", "Book", "Toolkit", "Printer", "Router"
    };


    public enum direction {UP, DOWN, LEFT, RIGHT};
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
}
