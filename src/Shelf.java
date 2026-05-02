public class Shelf {
    private Item[] items;
    private int row;
    private int col;
    private int itemCount = 0;
    private boolean visited;

    public Shelf(int row, int col, int size) {
        this.row = row;
        this.col = col;
        this.items = new Item[size];
        this.itemCount = 0;
        this.visited = false;
    }

    // Items on a shelf can be viewed and picked up

    public void addItem(Item item){
        if(itemCount < items.length){
            items[itemCount] = item;
            itemCount++;
        }
    }

    public void markVisited() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }
    public boolean isEmpty(){
        return itemCount == 0;
    }

    public void printItems(){
        if(isEmpty()){
            System.out.println("No items on this shelf.");
            return;
        }

        for(int i = 0; i < itemCount; i++){
            viewItem(i);
        }
    }

    public int getItemCount() {
        return itemCount;
    }


    public void viewItem(int index){
        if (index < 0 || index >= itemCount) {
            System.out.println("No items on this shelf.");
            return;
        }
        // Here the item view list starts from 1 instead of index 0
            // TODO: make sure the at (row, col) is removed after debugging
        System.out.println((index + 1) + ". " + items[index].getName());
    }

    public Item pickItem(int index){

        if (index < 0 || index >= itemCount || items[index] == null){
            System.out.println("No items at position " + index + " in shelf");
            return null;
        }
        Item picked = items[index];

        for (int i = index; i < itemCount - 1; i++){
            items[i] = items[i+1];
        }

        itemCount--;
        items[itemCount] = null;

        return picked;
    }
}
