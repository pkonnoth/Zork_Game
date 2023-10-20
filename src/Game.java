/*
Pettrus Konnoth
AP CSA
Zork Game
October 19 2023
Period 7
 */
public class Game {
    private int[][] gameMap; // Declare a fixed 2D array
    private int playerX; // Player's x-coordinate
    private int playerY; // Player's y-coordinate
    private int wallHitCounter;

    public Game() {
        initializeGameMap();
        playerX = gameMap.length / 2;
        playerY = gameMap[0].length / 2;
    }

    // Method to initialize the game map
    private void initializeGameMap() {
        gameMap = new int[24][24];

    }

    public void printPlayerPosition() {
        System.out.println("Player's Position: X=" + playerX + ", Y=" + playerY);
    }

    public Direction parseDirection(String userInput) {
        userInput = userInput.toLowerCase();
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
                newX--;
                break;
            case SOUTH:
                newX++;
                break;
            case EAST:
                newY++;
                break;
            case WEST:
                newY--;
                break;
        }

        // Check if the new position is valid within the game map bounds
        if (isValidPosition(newX, newY)) {
            playerX = newX;
            playerY = newY;
            printPlayerPosition();
        } else {
            System.out.println("You can't move in that direction. You've hit a wall.");

            // Increment the wall hit counter
            wallHitCounter++;

            // Check if the counter has reached the threshold (e.g., 5 times)
            if (wallHitCounter >= 5) {
                triggerAction(); // Execute the action when the threshold is reached
            }
        }
    }

    private void triggerAction() {
        System.out.println("you hit the wall 5 time you get a knife");
        // Implement your desired action here
    }

    private boolean isValidPosition(int x, int y) {
        // Add logic to check if (x, y) is a valid position in the game map
        // This may involve checking bounds and other game-specific conditions
        // Return true if valid, false otherwise
        return x >= 0 && x < gameMap.length && y >= 0 && y < gameMap[0].length;
    }

    // Add other game-related methods and fields here
}
