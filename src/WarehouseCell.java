public class WarehouseCell {
    private int row;
    private int col;
    private char symbol;
    private Shelf shelf;

    public WarehouseCell(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
        this.shelf = null;

    }
    public WarehouseCell(int row, int col, char symbol, Shelf shelf) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
        this.shelf = shelf;

    }


    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public char getSymbol() {
        return symbol;
    }
    public Shelf getShelf() {
        return shelf;
    }

}
