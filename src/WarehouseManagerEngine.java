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
    private OperationHistory history;

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


    private void run(String[] args) {

        if (args.length != 3) {
            System.out.println("Invalid number of Command Line Arguments. Usage: java WarehouseManagerEngine <rows> <cols> <seed>");
            return;
        }
        if (Integer.parseInt(args[0]) < 4 || Integer.parseInt(args[1]) < 4) {
            System.out.println("Error: Rows and columns must be at least 4 to allow proper map layout.");
        } else {
            int rows = Integer.parseInt(args[0]);
            int cols = Integer.parseInt(args[1]);
            long seed = Integer.parseInt(args[2]);

            map = new WarehouseMap(rows, cols, seed);
            forklift = new Forklift();
            history = new OperationHistory();
            running = true;
            shiftPaused = false;

            Messages.printWelcome();
            while (running) {
                Messages.printMainMenuCommands();
                String input = SCANNER.nextLine();
                input = input.trim();
                int option = 0;
                if (isPositiveInteger(input)) {
                    option = Integer.parseInt(input);
                }
                switch (option) {
                    case 1 -> startWarehouseShift();
                    case 2 -> resumeLastShift();
                    case 3 -> viewOperationHistory();
                    case 4 -> resetShiftAndWarehouse();
                    case 5 -> abandonAndExit();
                    default -> System.out.println(Constants.INVALID_INPUT);
                }
            }
        }
    }

    private void startWarehouseShift () {
        shiftPaused = false;
        runMovementSubMenu();
    }

    private void resumeLastShift () {
        if (!shiftPaused) {
            System.out.println("No shift to resume.");
            return;
        }

        shiftPaused = false;
        runMovementSubMenu();
    }

    private void viewOperationHistory () {
        history.printHistory();
    }

    private void resetShiftAndWarehouse () {
        map.reset();
        forklift.reset();
        shiftPaused = false;
        System.out.println("Shift and warehouse reset.");
    }

    private void abandonAndExit () {
        running = false;
        System.out.println("Session abandoned. Goodbye!");
    }

    private Movement parseMovement (String input){
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

    private void runMovementSubMenu() {
        boolean isRunning = true;

        while (isRunning) {
            map.printMap(forklift);
            Messages.printMovementMessage();

            String input = SCANNER.nextLine();
            Movement move = parseMovement(input);

            switch (move) {
                case UP, DOWN, LEFT, RIGHT -> makeMove(move);

                case QUIT -> {
                    // Q returns to the main menu without resetting the current warehouse state.
                    shiftPaused = true;
                    isRunning = false;
                    System.out.println("Shift paused.");
                }

                case DELIVER -> handleDelivery();

                case INVALID -> {
                    System.out.println(Constants.INVALID_INPUT);

                    // EdDiscussion clarification: invalid movement input is recorded as HIT_WALL,
                    // but it is not a real wall collision, so hitCount is not incremented.
                    history.addRecord(new OperationRecord(
                            OperationType.HIT_WALL,
                            map.getWarehouseId(),
                            forklift.getRow(),
                            forklift.getCol(),
                            forklift.getSuccessCount(),
                            forklift.getHitCount()
                    ));
                }
            }

            // Completion is checked after each command because a successful delivery may finish the shift.
            if (isRunning && isShiftCompleted()) {
                map.printMap(forklift);
                completeShift();
                isRunning = false;
            }
        }
    }

    private void handleDelivery() {
        // Check whether an item is carried first so the correct message is printed
        // even if the forklift is not currently on the START cell.
        if (!forklift.hasItem()) {
            System.out.println("You are not carrying any item.");
            return;
        }

        // A carried item can only be delivered from the START cell.
        if (!forklift.isAtStart()) {
            System.out.println("You must stand on the START cell (O) to deliver.");
            return;
        }

        Item deliveredItem = forklift.deliverItem();
        System.out.println(Constants.ITEM_DELIVERED_SUCCESSFULLY);

        // Record the successful delivery using the delivered item's name.
        history.addRecord(new OperationRecord(
                OperationType.PLACE_ITEM,
                map.getWarehouseId(),
                forklift.getRow(),
                forklift.getCol(),
                deliveredItem.getName(),
                forklift.getSuccessCount(),
                forklift.getHitCount()
        ));
    }
    private void makeMove(Movement move) {
        // Calculate the destination first; the forklift position is not changed yet.
        int[] destination = forklift.findDestination(move);
        int targetRow = destination[0];
        int targetCol = destination[1];
        WarehouseCell targetCell = map.getCell(targetRow, targetCol);

        if (map.isLegalMove(targetCell)) {
            // Only legal moves update the forklift position and successful move counter.
            forklift.moveTo(targetRow, targetCol);

            history.addRecord(new OperationRecord(
                    OperationType.MOVE,
                    map.getWarehouseId(),
                    forklift.getRow(),
                    forklift.getCol(),
                    forklift.getSuccessCount(),
                    forklift.getHitCount()
            ));

            // A shelf cell is enterable, but it also opens the shelf submenu.
            if (targetCell.getSymbol() == Constants.SHELF) {
                map.printMap(forklift);
                runShelfSubMenu(targetCell.getShelf());
            }
        } else {
            System.out.println("You cannot enter that area.");

            // Wall and restricted-cell collisions are real hits, so hitCount increases here.
            forklift.recordHit();

            // Classify restricted cells and walls as records to the history table.
            if (targetCell.getSymbol() == Constants.RESTRICTED) {
                history.addRecord(new OperationRecord(
                        OperationType.HIT_RESTRICTED,
                        map.getWarehouseId(),
                        forklift.getRow(),
                        forklift.getCol(),
                        forklift.getSuccessCount(),
                        forklift.getHitCount()
                ));
            } else {
                history.addRecord(new OperationRecord(
                        OperationType.HIT_WALL,
                        map.getWarehouseId(),
                        forklift.getRow(),
                        forklift.getCol(),
                        forklift.getSuccessCount(),
                        forklift.getHitCount()
                ));
            }
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
        shelf.markVisited();
        boolean inShelfMenu = true;
        while(inShelfMenu){
            Messages.printShelfMessage();
            String input = SCANNER.nextLine();
            ShelfCommand command = parseShelfCommand(input);

            switch (command) {
                case VIEW -> {
                    shelf.printItems();
                    history.addRecord(new OperationRecord(
                            OperationType.VIEW_SHELF,
                            map.getWarehouseId(),
                            forklift.getRow(),
                            forklift.getCol(),
                            forklift.getSuccessCount(),
                            forklift.getHitCount()
                    ));
                }
                case PICK -> handlePickItem(shelf);
                case QUIT -> inShelfMenu = false;
                case INVALID -> System.out.println(Constants.INVALID_INPUT);
            }
        }
    }

    /**
     * Handles the shelf item-picking workflow.
     *
     * @param shelf shelf currently being accessed
     */
    private void handlePickItem(Shelf shelf) {
        // A forklift can carry at most one item at a time.
        if (forklift.hasItem()) {
            System.out.println("You are already carrying an item. Place it before picking another.");
            return;
        }

        // Avoid asking for an item number when there is nothing to pick.
        if (shelf.isEmpty()) {
            System.out.println(Constants.NO_ITEMS_ON_SHELF);
            return;
        }

        Messages.printPickItemMessage();
        String itemInput = SCANNER.nextLine().trim();

        // Item numbers must be positive because the menu shown to the user starts from 1.
        if (!isPositiveInteger(itemInput)) {
            System.out.println(Constants.INVALID_INPUT);
            return;
        }

        // Convert the user's 1-based item number to Java's 0-based array index.
        int itemIndex = Integer.parseInt(itemInput) - 1;
        Item pickedItem = shelf.pickItem(itemIndex);

        // pickItem returns null when the converted index is outside the filled item range.
        if (pickedItem == null) {
            System.out.println(Constants.INVALID_INPUT);
            return;
        }

        forklift.pickUpItem(pickedItem);

        System.out.println(Constants.ITEM_PICKED_SUCCESSFULLY);
        // Use the local pickedItem reference instead of exposing the forklift's carried item.
        history.addRecord(new OperationRecord(
                OperationType.PICK_ITEM,
                map.getWarehouseId(),
                forklift.getRow(),
                forklift.getCol(),
                pickedItem.getName(),
                forklift.getSuccessCount(),
                forklift.getHitCount()
        ));
    }

    private boolean isShiftCompleted() {
        return map.allShelvesVisitedAndEmpty() && !forklift.hasItem();
    }

    private void completeShift() {
        System.out.println("Shift completed: all shelves visited and all items processed.");

        map.reset();
        forklift.reset();
        shiftPaused = false;
    }

    private boolean isPositiveInteger(String input) {
        if (input == null) {
            return false;
        }

        input = input.trim();

        if (input.isEmpty()) {
            return false;
        }

        // Check every character before parsing to avoid invalid numeric input.
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (!Character.isDigit(currentChar)) {
                return false;
            }
        }

        // Zero is not a valid positive menu option or item number.
        return Integer.parseInt(input) > 0;
    }
}
