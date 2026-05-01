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

    private WarehouseMap map;
    private Forklift forklift;

    private boolean running;
    private boolean shiftPaused;

    /**
     * Main method.
     *
     * @param args command line args to the program
     */
    public static void main(String[] args) {
        WarehouseManagerEngine engine = new WarehouseManagerEngine();
        engine.run(args);





    }


    private void run(String[] args){

        if (args.length != 3) {
            System.out.println("Invalid number of Command Line Arguments. Usage: java WarehouseManagerEngine <rows> <cols> <seed>");
            return;
        }
        if (Integer.parseInt(args[0]) < 4 || Integer.parseInt(args[1]) < 4) {
            System.out.println("Error: Rows and columns must be at least 4 to allow proper map layout. ");
        } else {
            int rows = Integer.parseInt(args[0]);
            int cols = Integer.parseInt(args[1]);
            long seed = Integer.parseInt(args[2]);

            map = new WarehouseMap(rows, cols, seed);
            forklift = new Forklift();

            running = true;
            shiftPaused = false;

            Messages.printWelcome();
            while (running) {
                Messages.printMainMenuCommands();
                String input = SCANNER.nextLine();
                int option = Integer.parseInt(input);
                // we need to generate a map based on the inputs
                // [here]


                switch (option) {
                    case 1 -> startWarehouseShift();
                    case 2 -> resumeLastShift();
                    case 3 -> viewOperationHistory();
                    case 4 -> resetShiftAndWarehouse();
                    case 5 -> abandonAndExit();
                    default -> System.out.println("Error: Invalid option. Please try again.");
                }
            }


        }

    }
    private void startWarehouseShift(){
        shiftPaused = false;
        runMovementSubMenu();
    }
    private void resumeLastShift() {
        if (!shiftPaused) {
            System.out.println("No paused shift to resume.");
            return;
        }

        shiftPaused = false;
        runMovementSubMenu();
    }

    private void viewOperationHistory() {
    }

    private void resetShiftAndWarehouse() {
    }

    private void abandonAndExit() {
        running = false;
    }

    private Movement parseMovement(String input) {
        input = input.trim().toUpperCase();

        return switch (input) {
            case "U" -> Movement.UP;
            case "D" -> Movement.DOWN;
            case "L" -> Movement.LEFT;
            case "R" -> Movement.RIGHT;
            case "T" -> Movement.DELIVER;
            case "Q" -> Movement.QUIT;
            default -> Movement.INVALID;
        };
    }

    private void runMovementSubMenu(){
        boolean isRunning = true;

        while(isRunning){
            map.printMap(forklift);

            Messages.printMovementMessage();

            String input = SCANNER.nextLine();
            Movement move = parseMovement(input);

            switch (move){
                case UP, DOWN, LEFT, RIGHT -> makeMove(move);
                case QUIT -> {
                    shiftPaused = true;
                    isRunning = false;
                    System.out.println("Shift paused.");
                }
                case DELIVER -> handleDelivery();
                case INVALID -> {
                    System.out.println("Invalid input.");
                }
            }
        }


    }
    private void handleDelivery(){
        if (!forklift.isAtStart()){
            System.out.println("Items can only be delivered at the start position.");
            return;
        }

        if (!forklift.hasItem()) {
            System.out.println("Forklift is not carrying any item.");
            return;
        }

        Item deliveredItem = forklift.deliverItem();
        System.out.println("Delivered: " + deliveredItem.getName());

        // TODO: add operation history required
    }
    private void makeMove(Movement move){
        int[] destination = forklift.findDestination(move);
        int targetRow = destination[0];
        int targetCol = destination[1];
        WarehouseCell targetCell = map.getCell(targetRow, targetCol);

        if (map.isLegalMove(targetCell)){
           forklift.moveTo(targetRow,targetCol);
           // TODO:
           // add operation history
            if (targetCell.getSymbol() == Constants.SHELF){
                runShelfSubMenu(targetCell.getShelf());
            }
        } else {
            System.out.println("You cannot move there.");
            forklift.recordHit();
        }
    }
    private ShelfCommand parseShelfCommand(String input) {
        input = input.trim().toUpperCase();

        return switch (input) {
            case "V" -> ShelfCommand.VIEW;
            case "P" -> ShelfCommand.PICK;
            case "Q" -> ShelfCommand.QUIT;
            default -> ShelfCommand.INVALID;
        };
    }
    private void runShelfSubMenu(Shelf shelf){

        boolean inShelfMenu = true;
        while(inShelfMenu){
            Messages.printShelfMessage();
            String input = SCANNER.nextLine();
            ShelfCommand command = parseShelfCommand(input);

            switch (command) {
                case VIEW -> shelf.printItems();
                case PICK -> {
                    // 1. PICK BUT FAIL
                    if (forklift.hasItem()) {
                        System.out.println("Forklift is already carrying an item.");
                    } else {
                        Messages.printPickItemMessage();
                        int itemIndex = SCANNER.nextInt();
                        Item pickedItem = shelf.pickItem(itemIndex);

                        if (pickedItem == null) {
                            System.out.println("Invalid item index.");
                        } else {
                            forklift.pickUpItem(pickedItem);
                            System.out.println("Picked up: " + pickedItem.getName()); // this is a debug line
                            System.out.println("Item picked successfully.");
                            // TODO: update operation history if success

                        }
                        // 2. SELECT PARTICULAR ITEM TO PICK
                    }
                }
                case QUIT -> inShelfMenu = false;
                case INVALID -> System.out.println("Invalid input.");
            }
        }

    }



}
