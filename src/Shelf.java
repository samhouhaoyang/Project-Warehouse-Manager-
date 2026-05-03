/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au
 */

/**
 * Represents a shelf in the warehouse containing zero or more items.
 */
public class Shelf {
    private final Item[] items;
    private int itemCount;
    private boolean visited;

    /**
     * Creates a shelf with the given item capacity.
     *
     * @param size maximum number of items this shelf can store
     */
    public Shelf(int size) {
        this.items = new Item[size];
        this.itemCount = 0;
        this.visited = false;
    }

    /**
     * Adds an item to the shelf if there is available space.
     *
     * @param item item to add
     */
    public void addItem(Item item){
        if(itemCount < items.length){
            items[itemCount] = item;
            itemCount++;
        }
    }

    /**
     * Marks the shelf as visited by the forklift.
     */
    public void markVisited() {
        visited = true;
    }

    /**
     * Checks whether the shelf has been visited.
     *
     * @return true if visited, otherwise false
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Checks whether the shelf contains no items.
     *
     * @return true if the shelf is empty, otherwise false
     */
    public boolean isEmpty(){
        return itemCount == 0;
    }

    /**
     * Prints all items currently stored on the shelf.
     */
    public void printItems(){
        if(isEmpty()){
            System.out.println(Constants.NO_ITEMS_ON_SHELF);
            return;
        }

        for(int i = 0; i < itemCount; i++){
            viewItem(i);
        }
    }

    /**
     * Prints the item at the specified index.
     *
     * @param index zero-based item index
     */
    public void viewItem(int index){
        if (index < 0 || index >= itemCount) {
            System.out.println(Constants.NO_ITEMS_ON_SHELF);
            return;
        }

        System.out.println((index + 1) + ". " + items[index].getName());
    }

    /**
     * Removes and returns the item at the specified index.
     *
     * @param index zero-based item index
     * @return picked item, or null if the index is invalid
     */
    public Item pickItem(int index) {
        // Only positions inside the currently filled part of the shelf are valid.
        if (index < 0 || index >= itemCount || items[index] == null) {
            return null;
        }

        Item picked = items[index];

        // Shift later items left after removing one item.
        for (int i = index; i < itemCount - 1; i++) {
            items[i] = items[i + 1];
        }

        // Clear the duplicated final reference after shifting.
        itemCount--;
        items[itemCount] = null;

        return picked;
    }
}
