/**
 Student Name - Haoyang Hou
 Student Id - 1462169
 Student email - houhh@student.unimelb.edu.au
 AI Usage Declaration - Haoyang Hou
 */

import java.util.Scanner;

/**
 * Main engine for the Warehouse Manager console application.
 */
public class WarehouseManagerEngine {

    private static final Scanner SCANNER = new Scanner(System.in);


    /**
     * Main method.
     *
     * @param args command line args to the program
     */
    public static void main(String[] args) {
        WarehouseManagerEngine engine = new WarehouseManagerEngine();

        //Add your code here
        // 4.1 Command Line Arguments
            // read the three exact input variables

            // 4.1.1 check for invalid arguments
        if (args.length != Constants.NUM_INPUT_ARGS){
            // Case 1: Incorrect number of arguments
            System.out.println("Invalid number of Command Line Arguments. Usage: java WarehouseManagerEngine <rows> <cols> <seed>");
            System.exit(0);
        }
        if (Integer.parseInt(args[0]) < Constants.MIN_MAP_LENGTH | Integer.parseInt(args[1]) < Constants.MIN_MAP_LENGTH){
            // Case 2: Rows or columns less than 4
            System.out.println("Error: Rows and columns must be at least 4 to allow proper map layout.");
            System.exit(0);
        }else{
            // 4.1.2 Valid input arguments
            int rows = Integer.parseInt(args[0]);
            int cols = Integer.parseInt(args[1]);
            int seed = Integer.parseInt(args[2]);

            // 1. Print the welcome message
            System.out.println("Welcome to Warehouse Manager Console.\n");

            // ***2. Initialise the warehouse map,
            WarehouseMap map = new WarehouseMap(rows, cols, seed);

            // 3. Display the main menu.
            do{
                System.out.println("=== Warehouse Manager Menu ===\n" +
                        "1. Start warehouse shift.\n" +
                        "2. Resume last shift.\n" +
                        "3. View operation history.\n" +
                        "4. Reset shift and warehouse.\n" +
                        "5. Abandon the shift and exit.");

                // ***check intput type
                int option = SCANNER.nextInt();

                //switch (option){




                }
            }while(1);

        }






    }

}
