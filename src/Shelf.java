/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au
 */

public class Shelf {
    private Item[] items;
    private final int row;
    private final int col;
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
            System.out.println(Constants.NO_ITEMS_ON_SHELF);
            return;
        }

        for(int i = 0; i < itemCount; i++){
            viewItem(i);
        }
    }

    public void viewItem(int index){
        if (index < 0 || index >= itemCount) {
            System.out.println(Constants.NO_ITEMS_ON_SHELF);
            return;
        }

        System.out.println((index + 1) + ". " + items[index].getName());
    }

    public Item pickItem(int index){

        if (index < 0 || index >= itemCount || items[index] == null){
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
