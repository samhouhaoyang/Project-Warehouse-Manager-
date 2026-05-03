/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au
 */

/**
 * Class for user-facing messages.
 */
public final class Messages {
    /**
     * Prints the welcome message.
     */
    public static void printWelcome() {
        System.out.println("Welcome to Warehouse Manager Console.");
    }

    /**
     * Prints the main warehouse manager menu.
     */
    public static void printMainMenuCommands() {
        System.out.println("\n=== Warehouse Manager Menu ===");
        System.out.println("1. Start warehouse shift.");
        System.out.println("2. Resume last shift.");
        System.out.println("3. View operation history.");
        System.out.println("4. Reset shift and warehouse.");
        System.out.println("5. Abandon the shift and exit.");
        System.out.print("> ");
    }

    /**
     * Prints the movement command menu.
     */
    public static void printMovementMessage(){
        System.out.println("Enter direction:\n" +
                "U - Up.\n" +
                "D - Down.\n" +
                "L - Left.\n" +
                "R - Right.\n" +
                "T - Deliver carried item at START (O).\n" +
                "Q - Quit to main menu.");
        System.out.print("> ");
    }

    /**
     * Prints the shelf interaction menu.
     */
    public static void printShelfMessage(){
        System.out.println("Shelf Menu:");
        System.out.println("Press V to view items.");
        System.out.println("Press P to pick an item.");
        System.out.println("Press Q to exit shelf menu.");
        System.out.print("> ");
    }

    /**
     * Prompts the user to enter an item number for picking.
     */
    public static void printPickItemMessage(){
        System.out.print("Enter item number to pick (e.g., 1): ");
    }

    /**
     * Prints the legend explaining map symbols.
     */
    public static void printLegend(){
        System.out.println("Legend: # Wall | . Aisle | X Restricted | S Shelf | O Start | F Forklift");
    }

}
