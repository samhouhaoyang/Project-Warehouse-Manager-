/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au
 */

public class OperationRecord {
    private final OperationType type;
    private final int warehouseId;
    private final int row;
    private final int col;
    private final String itemName;
    private final int movesCount;
    private final int hitCount;

    public OperationRecord(OperationType type, int warehouseId, int row, int col, int movesCount, int hitCount) {
        this.type = type;
        this.warehouseId = warehouseId;
        this.row = row;
        this.col = col;
        this.hitCount = hitCount;
        this.movesCount = movesCount;
        this.itemName = "-";
    }
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

    public OperationType getType() {
        return type;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    public String getPosition(){
        return "(" + row + "," + col +")";
    }
    public String getItemName() {
        return itemName;
    }

    public String toString() {
        if (itemName == null) {
            return type + " at (" + row + "," + col + ") in warehouse " + warehouseId;
        }

        return type + " " + itemName + " at (" + row + "," + col + ") in warehouse " + warehouseId;
    }
}
