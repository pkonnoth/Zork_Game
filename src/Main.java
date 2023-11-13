/*
Pettrus Konnoth
AP CSA
Zork Game
October 19 2023
Period 7
Main class
*/

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean gameRunning = true;
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        // Main game loop
        while (gameRunning) {
            // Print the player's location at game start
            System.out.println("You are at: (" + game.getPlayerX() + ", " + game.getPlayerY() + ")");
            System.out.print("Enter a command: ");
            String userInput = scanner.nextLine();
            Direction direction = game.parseCommandLine(userInput);

            // Check if the user entered an invalid command

            // Check if the user wants to quit
            if (direction == Direction.QUIT) {
                System.out.println("Thanks for playing, sad to see you leave 😭");
                gameRunning = false; // Set gameRunning to false to exit the loop
            } else {
                if (direction == Direction.PickUP) {
                    // Check if there's an item at the player's location
                    game.pickUpItem(game.getPlayerX(), game.getPlayerY());
                } else if (direction == Direction.DROP) {
                    game.dropItem(game.getPlayerX(), game.getPlayerY());
                    // Check if there's an item at the player's location
                } else if (direction == Direction.HELP) {
                    System.out.println("Commands: ");
                    System.out.println("NORTH, SOUTH, EAST, WEST, QUIT, PickUP, DROP, HELP, BACKPACK");
                    // Check if there's an item at the player's location
                } else if (direction == Direction.SCAN) {
                    int itemCount = game.countItemsOnMap();
                    System.out.println("Number of items on the map: " + itemCount);
                } else if (direction == Direction.INFO) {
                    // Check if there's an item at the player's location
                    Item item = game.getItemAtLocation(game.getPlayerX(), game.getPlayerY());
                    if (item != null) {
                        System.out.println("Item: " + item.getName());
                        System.out.println("Description: " + item.getDescription());
                    } else {
                        System.out.println("There is no item at your current location.");
                        // Check if the backpack is not empty
                    }


                } else if (direction == Direction.BACKPACK) {
                    // Check if the backpack is not empty
                    if (!game.getBackPack().isEmpty()) {
                        System.out.println("\nItems in your backpack: ");
                        for (int i = 0; i < game.getBackPack().size(); i++) {
                            Item item = game.getBackPack().get(i);
                            System.out.println((i + 1) + ". " + item.getName());
                            System.out.println("\n");
                            // System.out.println("Description: " + item.getDescription());
                        }
                    } else {
                        System.out.println("Your backpack is empty.");
                    }
                } else {
                    // Move the player
                    System.out.println("Parsed command: " + direction);
                    game.movePlayer(direction);
                }
            }
        }

        // Close the scanner at the end
        scanner.close();
    }
}
