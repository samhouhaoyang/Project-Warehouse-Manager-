public class Forklift {
    public static int hitCount = 0;
    public static int sucessCount = 0;
    private Item item;
    private boolean hasItem = false;

    public Forklift(Item item) {
        if(hasItem == false){
            this.item = item;
            this.hasItem = true;
        }else{
            Messages.hasItemMessage();
        }
    }

    private boolean isLegalMove(WarehouseCell cell) {
        // check for wall cell
        return cell.getSymbol() != Constants.WALL && cell.getSymbol() != Constants.RESTRICTED;
    }


}
