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
 * Represents the forklift controlled by the user during a warehouse shift.
 * Tracks its position, carried item, successful moves, and real collision hits.
 */
public class Forklift {
    private int hitCount;
    private int successCount;
    private int col;
    private int row;
    private Item item;

    /**
     * Creates a forklift at the start position with no carried item.
     */
    public Forklift(){
        reset();
    }

    /**
     * Resets the forklift to the start position and clears all shift counters.
     */
    public void reset() {
        this.col = Constants.START_COL;
        this.row = Constants.START_ROW;
        this.item = null;
        this.hitCount = 0;
        this.successCount = 0;
    }

    /**
     * Gets the current column of the forklift.
     *
     * @return forklift column
     */
    public int getCol(){
        return col;
    }

    /**
     * Gets the current row of the forklift.
     *
     * @return forklift row
     */
    public int getRow(){
        return row;
    }

    /**
     * Gets the number of real hits made by the forklift.
     *
     * @return hit counter
     */
    public int getHitCount(){
        return hitCount;
    }

    /**
     * Gets the number of successful movements made by the forklift.
     *
     * @return successful move counter
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * Checks whether the forklift is currently carrying an item.
     *
     * @return true if the forklift has an item, otherwise false
     */
    public boolean hasItem() {
        return item != null;
    }

    /**
     * Calculates the destination position for a movement command.
     *
     * @param move movement direction
     * @return array containing target row at index 0 and target column at index 1
     */
    public int[] findDestination(Movement move){
        int targetRow = row;
        int targetCol = col;

        switch (move){
            case UP -> targetRow--;
            case DOWN -> targetRow++;
            case LEFT -> targetCol--;
            case RIGHT -> targetCol++;
            default -> {
            }
        }

        return new int[]{targetRow, targetCol};
    }

    /**
     * Moves the forklift to the specified position and records a successful move.
     *
     * @param newRow new row position
     * @param newCol new column position
     */
    public void moveTo(int newRow, int newCol){
        row = newRow;
        col = newCol;
        successCount++;
    }

    /**
     * Records one real hit caused by entering a blocked cell.
     */
    public void recordHit(){
        hitCount++;
    }

    /**
     * Stores the picked item on the forklift.
     * This method assumes the caller has already checked that the forklift
     * is not carrying another item and that the given item is not null.
     *
     * @param item item to be carried by the forklift
     */
    public void pickUpItem(Item item) {
        this.item = item;
    }

    /**
     * Delivers the currently carried item and clears the forklift's carried item.
     *
     * @return delivered item
     */
    public Item deliverItem(){
        Item deliveredItem = item;
        item = null;
        return deliveredItem;
    }

    /**
     * Checks whether the forklift is currently on the start cell.
     *
     * @return true if the forklift is at the start cell, otherwise false
     */
    public boolean isAtStart(){
        return row == Constants.START_ROW && col == Constants.START_COL;
    }

}
