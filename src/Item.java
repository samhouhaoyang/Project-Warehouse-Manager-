/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au
 */
/**
 * Represents an item stored on a warehouse shelf.
 */
public class Item {
    private final String name;

    /**
     * Creates an item with the specified name.
     *
     * @param name item name
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the item.
     *
     * @return item name
     */
    public String getName() {
        return name;
    }

}
