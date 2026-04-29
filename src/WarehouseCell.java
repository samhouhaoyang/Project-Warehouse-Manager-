public class WarehouseCell {
    public static int count = 0;
    private int row;
    private int col;
    private char symbol;
    public WarehouseCell(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
        count++;
    }
    WarehouseCell(int row, int col) {
        count++;
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
    public void getRow(int row) {
        this.row = row;
    }
    public void getCol(int col) {
        this.col = col;
    }
    public void getSymbol(char symbol) {
        this.symbol = symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }


}
