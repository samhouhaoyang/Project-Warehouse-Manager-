/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au
 */
/**
 * Represents a single recorded warehouse operation.
 */
public class OperationRecord {
    private final OperationType type;
    private final int warehouseId;
    private final int row;
    private final int col;
    private final String itemName;
    private final int movesCount;
    private final int hitCount;

    /**
     * Creates an operation record without an associated item.
     *
     * @param type operation type
     * @param warehouseId warehouse identifier
     * @param row row position when the operation occurred
     * @param col column position when the operation occurred
     * @param movesCount successful move count at the time
     * @param hitCount hit count at the time
     */
    public OperationRecord(OperationType type, int warehouseId, int row, int col, int movesCount, int hitCount) {
        this.type = type;
        this.warehouseId = warehouseId;
        this.row = row;
        this.col = col;
        this.hitCount = hitCount;
        this.movesCount = movesCount;
        this.itemName = "-";
    }

    /**
     * Creates an operation record with an associated item.
     *
     * @param type operation type
     * @param warehouseId warehouse identifier
     * @param row row position when the operation occurred
     * @param col column position when the operation occurred
     * @param itemName name of the related item
     * @param movesCount successful move count at the time
     * @param hitCount hit count at the time
     */
    public OperationRecord(OperationType type, int warehouseId, int row, int col, String itemName,
                           int movesCount, int hitCount) {
        this.type = type;
        this.warehouseId = warehouseId;
        this.row = row;
        this.col = col;
        this.itemName = itemName;
        this.hitCount = hitCount;
        this.movesCount = movesCount;
    }

    /**
     * Gets the operation type.
     *
     * @return operation type
     */
    public OperationType getType() {
        return type;
    }

    /**
     * Gets the warehouse identifier.
     *
     * @return warehouse id
     */
    public int getWarehouseId() {
        return warehouseId;
    }

    /**
     * Gets the move count recorded for this operation.
     *
     * @return move count
     */
    public int getMovesCount() {
        return movesCount;
    }

    /**
     * Gets the hit count recorded for this operation.
     *
     * @return hit count
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * Gets the position of the operation in row-column format.
     *
     * @return formatted position
     */
    public String getPosition(){
        return "(" + row + "," + col +")";
    }

    /**
     * Gets the related item name, or "-" if no item is associated.
     *
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Returns a readable string representation of this operation record.
     *
     * @return operation record as text
     */
    public String toString() {
        if (itemName == null) {
            return type + " at (" + row + "," + col + ") in warehouse " + warehouseId;
        }

        return type + " " + itemName + " at (" + row + "," + col + ") in warehouse " + warehouseId;
    }
}
