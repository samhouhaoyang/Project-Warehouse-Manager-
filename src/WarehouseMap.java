import java.util.Scanner;

/**
 * WarehouseMap represents a 2D warehouse grid that can be navigated by a forklift.
 */
public class WarehouseMap {

    private final int rows;
    private final int cols;

    // add the map variable here
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
        this.generator = new WarehouseGenerator(seed);

        //TODO: set other variables here

        generateMap();
        //printMap();
        //debugShelves();
    }

    //DO NOT MODIFY THIS METHOD
    private void generateMap() {
        initialiseGrid();
        fillSpecialCells();
    }

    private void initialiseGrid() {
        // TODO: initialise map by looping through Array
        // TODO: set the boundary, start position and mark everything else as open position
        this.grid = new WarehouseCell[rows][cols];
        char symbol;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(i == 0 || i == rows - 1 || j == 0 || j == cols - 1){
                    symbol = Constants.WALL;
                } else if(i == 1 && j == 1) {
                    symbol = Constants.START;
                }else{
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
        //TODO: use the generator to generate random position for rows/cols that are open spaces to fill restricted places.
        // The maximum number of restricted places are defined by the count parameter in this method.
        for (int i = 0; i < count; i++) {
            WarehouseCell cell = findRandomEmptyCell();
            if(cell != null){
                cell.setSymbol(Constants.RESTRICTED);
            }

        }
    }

    private void placeShelves(int count) {
        //TODO: the total shelves to be created defined by the count parameter
        //TODO: based on number of shelves to be created, generate random row/col positions and fill up with Shelves.
        //TODO: for each shelf generated you need add items to the shelf
        for (int i = 0; i < count; i++) {
            WarehouseCell cell = findRandomEmptyCell();
            if(cell != null){
                cell.setSymbol(Constants.SHELF);
                populateShelf(cell); // can modify this method to add parameters required to place items to shelf.
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

        // TODO: add items to the shelf
        Shelf shelf = new Shelf(cell.getRow(), cell.getCol(), itemCount);

        for (int i = 0; i < itemCount; i++) {
            String name = generator.randomItemName();
            shelf.addItem(new Item(name));

        }
        cell.setShelf(shelf);

        // shelf.printItems();

    }


    private void printMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].getSymbol());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void debugCounts() {
        int wall = 0;
        int start = 0;
        int aisle = 0;
        int shelf = 0;
        int restricted = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char symbol = grid[r][c].getSymbol();

                if (symbol == Constants.WALL) {
                    wall++;
                } else if (symbol == Constants.START) {
                    start++;
                } else if (symbol == Constants.AISLE) {
                    aisle++;
                } else if (symbol == Constants.SHELF) {
                    shelf++;
                } else if (symbol == Constants.RESTRICTED) {
                    restricted++;
                }
            }
        }

        System.out.println("Wall: " + wall);
        System.out.println("Start: " + start);
        System.out.println("Aisle: " + aisle);
        System.out.println("Shelf: " + shelf);
        System.out.println("Restricted: " + restricted);
    }
    public void debugShelves() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                WarehouseCell cell = grid[r][c];

                if (cell.getSymbol() == Constants.SHELF) {
                    System.out.println("Shelf at (" + r + ", " + c + "):");
                    cell.getShelf().printItems();
                }
            }
        }
    }

}