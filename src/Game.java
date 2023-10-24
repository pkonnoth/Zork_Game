/*
Pettrus Konnoth
AP CSA
Zork Game
October 19 2023
Period 7
 */
public class Game {

    //declare the game variables

    private int playerX; // Player's x-coordinate
    private int playerY; // Player's y-coordinate
    private int wallHitCounter;

    public Game() {
        //initialize the game variables
        initializeGameMap();

    }

    // Method to initialize the game map
    private void initializeGameMap() {
        playerX = 12;
        playerY = 12;

    }

    public void printPlayerPosition() {
        System.out.printf("(%d,%d) \n",playerX,playerY);
    }
    // Method to parse the user input and return a Direction enum

    public Direction parseDirection(String userInput) {
        userInput = userInput.toLowerCase();

        //creating the switch statement to parse the direction with 2 arguments
        return switch (userInput) {
            case "north", "n" -> Direction.NORTH;
            case "south", "s" -> Direction.SOUTH;
            case "east", "e" -> Direction.EAST;
            case "west", "w" -> Direction.WEST;
            case "quit", "q" -> Direction.QUIT;
            default -> Direction.INVALID;
        };
    }

    public void movePlayer(Direction direction) {
        int newX = playerX;
        int newY = playerY;

        switch (direction) {
            case NORTH:
                newY--;
                break;
            case SOUTH:
                newY++;
                break;
            case EAST:
                newX++;
                break;
            case WEST:
                newX--;
                break;
        }

        // Check if the new position is valid within the game map bounds
        if (isValidPosition(newX, newY)) {
            playerX = newX;
            playerY = newY;
            printPlayerPosition();
        } else {
            // Print an error message if the new position is invalid
            System.out.println("You can't move in that direction. You've hit a wall.");

            // Increment the wall hit counter
            wallHitCounter++;

            // Check if the counter has reached the threshold (e.g., 5 times)
            if (wallHitCounter >= 5) {
                //trigger the action to show that the player has hit the wall 5 times
                triggerAction(); // Execute the action when the threshold is reached
            }
        }
    }

    private void triggerAction() {

        //print the action
        System.out.println("you hit the wall 5 time you get a knife");
        wallHitCounter = 0;

    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x <= 12 && y >= 0 && y <= 12;
    }


}
