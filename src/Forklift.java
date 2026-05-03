public class Forklift {
    private int hitCount;
    private int successCount;
    private int col;
    private int row;
    private Item item;

    public Forklift(){
        reset();
    }
    public void reset() {
        this.col = Constants.START_COL;
        this.row = Constants.START_ROW;
        this.item = null;
        this.hitCount = 0;
        this.successCount = 0;
    }
    public int getCol(){
        return col;
    }

    public int getRow(){
        return row;
    }
    public int getHitCount(){
        return hitCount;
    }

    public int getSuccessCount() {
        return successCount;
    }


    public boolean hasItem() {
        return item != null;
    }


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


    public void moveTo(int newRow, int newCol){
        row = newRow;
        col = newCol;
        successCount++;
    }

    public void recordHit(){
        hitCount++;
    }

    public boolean pickUpItem(Item item){
        if(this.item != null || item == null){
            return false;
        }

        this.item = item;
        return true;
    }

    public Item deliverItem(){
        Item deliveredItem = item;
        item = null;
        return deliveredItem;
    }

    public boolean isAtStart(){
        return row == Constants.START_ROW && col == Constants.START_COL;
    }

}
