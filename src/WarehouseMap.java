import java.util.Scanner;

/**
 * WarehouseMap represents a 2D warehouse grid that can be navigated by a forklift.
 */
public class WarehouseMap {

    private final int rows;
    private final int cols;
    private int warehouseId;
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
        this.warehouseId = 0;
        this.generator = new WarehouseGenerator(seed);

        //TODO: set other variables here

        initialiseNewWarehouse();
    }
    public int getWarehouseId() {
        return warehouseId;
    }
    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public WarehouseCell[][] getGrid(){
        return grid;
    }

    public WarehouseCell getCell(int row, int col){
        return grid[row][col];
    }
    //DO NOT MODIFY THIS METHOD
    private void generateMap() {
        initialiseGrid();
        fillSpecialCells();
    }
    private void initialiseNewWarehouse() { // this method is used to update the id everytime the constructor is called
        warehouseId++;
        generateMap();
    }
    public void reset() {
        initialiseNewWarehouse();
    }
    private void initialiseGrid() {
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

        // TODO: add items to the shelf
        Shelf shelf = new Shelf(cell.getRow(), cell.getCol(), itemCount);

        for (int i = 0; i < itemCount; i++) {
            String name = generator.randomItemName();
            shelf.addItem(new Item(name));

        }
        cell.setShelf(shelf);

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

    public void printMap(Forklift forklift) {
        System.out.println("Warehouse ID: " + warehouseId);
        Messages.printLegend();
        System.out.printf("Forklift at: (%d,%d)\n", forklift.getRow(), forklift.getCol());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (i == forklift.getRow() && j == forklift.getCol()){
                    System.out.print(Constants.FORKLIFT);
                }else{
                    System.out.print(grid[i][j].getSymbol());
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public boolean isLegalMove(WarehouseCell cell) {
        // check for wall cell
        return cell.getSymbol() != Constants.WALL && cell.getSymbol() != Constants.RESTRICTED;
    }


    public boolean allShelvesVisitedAndEmpty() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                WarehouseCell cell = grid[i][j];

                if (cell.getSymbol() == Constants.SHELF) {
                    Shelf shelf = cell.getShelf();

                    if (!shelf.isVisited() || !shelf.isEmpty()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


}