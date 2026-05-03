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
 * Stores the chronological operation history for warehouse shifts.
 */
public class OperationHistory {

    private OperationRecord[] records;
    private int recordCount;

    /**
     * Creates an empty operation history with an initial fixed capacity.
     */
    public OperationHistory() {
        records = new OperationRecord[Constants.INITIAL_CAPACITY];
        this.recordCount = 0;
    }

    /**
     * Adds a new operation record to the history, resizing the internal array if needed.
     *
     * @param record operation record to add
     */
    public void addRecord(OperationRecord record) {
        // Resize before inserting when the internal array has reached full capacity.
        if (recordCount == records.length) {
            realloc();
        }

        // recordCount points to the next unused position in the partially filled array.
        records[recordCount] = record;
        recordCount++;
    }

    private void realloc() {
        // Double the capacity to reduce the number of future reallocations.
        OperationRecord[] newRecords = new OperationRecord[records.length * 2];

        // Copy existing records into the new larger array.
        for (int i = 0; i < recordCount; i++) {
            newRecords[i] = records[i];
        }

        records = newRecords;
    }

    private boolean recordsIsEmpty(){
        return recordCount == 0;
    }

    /**
     * Prints all operation records in chronological order.
     */
    public void printHistory(){
        if (recordsIsEmpty()){
            System.out.println("No operation history available.");
        } else{
            System.out.printf(Constants.HISTORY_HEADER_FORMATTER,
                    "Warehouse", "Type", "Item", "Moves", "Hits", "Position");
            System.out.println(Constants.HISTORY_DIVIDER);

            for (int i = 0; i < recordCount; i++){
                OperationRecord currRecord = records[i];
                System.out.printf(Constants.HISTORY_ROW_FORMATTER,
                        currRecord.getWarehouseId(),
                        currRecord.getType(),
                        currRecord.getItemName(),
                        currRecord.getMovesCount(),
                        currRecord.getHitCount(),
                        currRecord.getPosition()
                        );
            }
        }
    }
}
