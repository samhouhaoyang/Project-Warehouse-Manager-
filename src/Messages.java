/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au
 */

/**
 * Class for user-facing messages.
 */
public final class Messages {

    public static void printWelcome() {
        System.out.println("Welcome to Warehouse Manager Console.");
    }

    public static void printMainMenuCommands() {
        System.out.println("\n=== Warehouse Manager Menu ===");
        System.out.println("1. Start warehouse shift.");
        System.out.println("2. Resume last shift.");
        System.out.println("3. View operation history.");
        System.out.println("4. Reset shift and warehouse.");
        System.out.println("5. Abandon the shift and exit.");
        System.out.print("> ");
    }

    public static void hitMessage(){
        System.out.println("Error move, you cannot move into a blocked cell");
    }

    public static void hasItemMessage(){
        System.out.println("Current fork has an item already!");
    }


}
