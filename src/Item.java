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
