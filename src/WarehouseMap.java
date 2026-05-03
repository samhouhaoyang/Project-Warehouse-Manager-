/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au

 AI Usage Declaration:
 AI tools were used to support debugging, code review, explanation of Java/OOP concepts,
 and refinement of comments/style. The final code was reviewed, tested, and understood
 by the student.
 */

/**
 * WarehouseMap represents a 2D warehouse grid that can be navigated by a forklift.
 */
public class WarehouseMap {

    private final int rows;
    private final int cols;
    private int warehouseId;

    private final WarehouseGenerator generator;
    private WarehouseCell[][] grid;
    /**
     * Constructs a new WarehouseMap.
     *
     * @param rows number of rows
     * @param cols number of cols
     * @param seed seed for random generation
     */
    public WarehouseMap(int rows, int cols, long seed) {
        this.rows = rows;
        this.cols = cols;
        this.warehouseId = 0;
        this.generator = new WarehouseGenerator(seed);

        initialiseNewWarehouse();
    }

    /**
     * Gets the current warehouse identifier.
     *
     * @return warehouse id
     */
    public int getWarehouseId() {
        return warehouseId;
    }

    /**
     * Gets the cell at the specified position.
     *
     * @param row target row
     * @param col target column
     * @return warehouse cell at the position
     */
    WarehouseCell getCell(int row, int col){
        return grid[row][col];
    }

    //DO NOT MODIFY THIS METHOD
    private void generateMap() {
        initialiseGrid();
        fillSpecialCells();
    }

    private void initialiseNewWarehouse() {
        // this method is used to update the id everytime the constructor is called
        warehouseId++;
        generateMap();
    }

    /**
     * Resets the warehouse by generating a new warehouse layout.
     */
    public void reset() {
        initialiseNewWarehouse();
    }

    private void initialiseGrid() {
        this.grid = new WarehouseCell[rows][cols];
        char symbol;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Border cells are always walls.
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    symbol = Constants.WALL;

                    // The START cell is fixed at the configured start position.
                } else if (i == Constants.START_ROW && j == Constants.START_COL) {
                    symbol = Constants.START;

                    // All other inner cells start as aisles before shelves/restricted cells are placed.
                } else {
                    symbol = Constants.AISLE;
                }

                grid[i][j] = new WarehouseCell(i, j, symbol);
            }
        }
    }

    //DO NOT MODIFY THIS METHOD
    private void fillSpecialCells() {
        int inner = availableInnerCells();

        // shelves: between MIN_SHELVES and inner (inclusive)
        int shelfCount = generator.generateInt(Constants.MIN_SHELVES, inner + 1);

        // remaining cells after shelves
        int remaining = inner - shelfCount;

        // restricted: allow 0 if no space remains, otherwise at least MIN_RESTRICTED
        int restrictedMin = (remaining > 2) ? Constants.MIN_RESTRICTED : 0;
        int restrictedCount = generator.generateInt(restrictedMin, remaining + 1);

        placeRestrictedCells(restrictedCount);
        placeShelves(shelfCount);
    }

    private int availableInnerCells() {
        return (rows - Constants.BOUNDARY_THICKNESS)
                 * (cols - Constants.BOUNDARY_THICKNESS) - Constants.START_OFFSET;

    }

    private void placeRestrictedCells(int count) {
        for (int i = 0; i < count; i++) {
            WarehouseCell cell = findRandomEmptyCell();
            if(cell != null){
                cell.setSymbol(Constants.RESTRICTED);
            }

        }
    }

    private void placeShelves(int count) {
        for (int i = 0; i < count; i++) {
            WarehouseCell cell = findRandomEmptyCell();
            if(cell != null){
                cell.setSymbol(Constants.SHELF);
                populateShelf(cell);
            }
        }
    }

    private WarehouseCell findRandomEmptyCell() {
        int attempts = 0;
        int maxAttempts = rows * cols * 10;

        while (attempts < maxAttempts) {
            int r = generator.generateInt(1, rows - 1);
            int c = generator.generateInt(1,  cols - 1);

            WarehouseCell cell = grid[r][c];
            if (cell.getSymbol() == Constants.AISLE) {
                return cell;
            }
            attempts++;
        }

        System.out.println("Error: No empty AISLE cell available to place an object.");
        return null;
    }

    private void populateShelf(WarehouseCell cell) {
        int itemCount = generator.generateInt(Constants.MIN_ITEMS_PER_SHELF, Constants.MAX_ITEMS_PER_SHELF + 1);

        Shelf shelf = new Shelf(itemCount);

        for (int i = 0; i < itemCount; i++) {
            String name = generator.randomItemName();
            shelf.addItem(new Item(name));

        }
        cell.setShelf(shelf);

    }

    /**
     * Prints the current warehouse map with the forklift position shown.
     *
     * @param forklift forklift to display on the map
     */
    public void printMap(Forklift forklift) {
        System.out.println("Warehouse ID: " + warehouseId);
        Messages.printLegend();
        System.out.printf("Forklift at: (%d,%d)\n", forklift.getRow(), forklift.getCol());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Display F at the forklift's current position without changing the actual map cell.
                if (i == forklift.getRow() && j == forklift.getCol()) {
                    System.out.print(Constants.FORKLIFT);
                } else {
                    System.out.print(grid[i][j].getSymbol());
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Checks whether the forklift can legally enter the specified cell.
     *
     * @param cell target cell
     * @return true if the cell can be entered, otherwise false
     */
    public boolean isLegalMove(WarehouseCell cell) {
        // The forklift cannot enter wall or restricted cells.
        return cell.getSymbol() != Constants.WALL && cell.getSymbol() != Constants.RESTRICTED;
    }

    /**
     * Checks whether all shelves have been visited and emptied.
     *
     * @return true if all shelves are visited and empty, otherwise false
     */
    public boolean allShelvesVisitedAndEmpty() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                WarehouseCell cell = grid[i][j];

                if (cell.getSymbol() == Constants.SHELF) {
                    Shelf shelf = cell.getShelf();

                    // A shelf only counts as complete after it has been visited and emptied.
                    if (!shelf.isVisited() || !shelf.isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}