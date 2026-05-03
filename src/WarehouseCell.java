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
 * Represents a single cell in the warehouse grid.
 */
public class WarehouseCell {
    private final int row;
    private final int col;
    private char symbol;
    private Shelf shelf;

    /**
     * Creates a warehouse cell with a position and display symbol.
     *
     * @param row cell row
     * @param col cell column
     * @param symbol display symbol for the cell
     */
    public WarehouseCell(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
        this.shelf = null;

    }

    void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }
    void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the row of this cell.
     *
     * @return cell row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column of this cell.
     *
     * @return cell column
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the symbol of this cell.
     *
     * @return cell symbol
     */
    public char getSymbol() {
        return symbol;
    }
    Shelf getShelf() {
        return shelf;
    }

}
