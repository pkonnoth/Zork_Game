/*
Pettrus Konnoth
AP CSA
Zork Game
October 19 2023
Period 7
 */


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean gameRunning = true;

        Game game = new Game();


        Scanner scanner = new Scanner(System.in);

        while (gameRunning) {
            System.out.print("Enter a direction: ");
            String userInput = scanner.nextLine();
            Direction direction = game.parseDirection(userInput);

            // Check if the user wants to quit
            if (direction == Direction.QUIT) {
                System.out.println("Thanks for playing sad to see you leave 😭");
                gameRunning = false; // Set gameRunning to false to exit the loop
            } else {
                // Print the parsed direction
                System.out.println("Parsed direction: " + direction);
                game.movePlayer(direction); // Pass the parsed direction to movePlayer
            }
        }

        // Close the scanner at the end
        scanner.close();
    }
}
