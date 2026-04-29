/**
 Student Name -
 Student Id -
 Student email -
 AI Usage Declaration -
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
        //WarehouseManagerEngine engine = new WarehouseManagerEngine();

        //Add your code here

        if(args.length != 3){
            System.out.println("Invalid number of Command Line Arguments. Usage: java WarehouseManagerEngine <rows> <cols> <seed>");
        }
        if (Integer.parseInt(args[0]) < 4 || Integer.parseInt(args[1]) < 4){
            System.out.println("Error: Rows and columns must be at least 4 to allow proper map layout. ");
        }
        else{
            int rows = Integer.parseInt(args[0]);
            int cols = Integer.parseInt(args[1]);
            long seed = Integer.parseInt(args[2]);
            WarehouseMap map = new WarehouseMap(rows, cols, seed);

            boolean running = true;

            Messages.printWelcome();
            while(running){
                Messages.printMainMenuCommands();
                String input = SCANNER.nextLine();
                int option = Integer.parseInt(input);
                // we need to generate a map based on the inputs
                // [here]



                switch (option){
                    case 1:System.out.println("option 1"); break;
                    case 2:System.out.println("option 2");  break;
                    case 3:System.out.println("option 3");  break;
                    case 4:System.out.println("option 4");  break;
                    case 5:System.out.println("option 5");   break;
                    default: System.out.println("Error: Invalid option. Please try again.");
                }
            }


        }


    }

}
