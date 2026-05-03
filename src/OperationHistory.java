import jdk.dynalink.Operation;

public class OperationHistory {

    private OperationRecord[] records;
    private int recordCount;

    public OperationHistory() {
        records = new OperationRecord[Constants.INITIAL_CAPACITY];
        this.recordCount = 0;
    }


    public void addRecord(OperationRecord record){
        if (recordCount == records.length){
            realloc();
        }

        records[recordCount] = record;
        recordCount++;
    }

    public void realloc(){
        OperationRecord[] newRecords = new OperationRecord[records.length * 2];

        for(int i = 0; i < records.length; i++){
            newRecords[i] = records[i];
        }
        records = newRecords;


    }
    private boolean recordsIsEmpty(){
        return recordCount == 0;
    }

        public OperationRecord[] getRecords() {
        OperationRecord[] copy = new OperationRecord[recordCount];

        for (int i = 0; i < recordCount; i++) {
            copy[i] = records[i];
        }

        return copy;
    }

    public void printHistory(){
        if (recordsIsEmpty()){
            System.out.println("No operation history available.");
            return;
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
