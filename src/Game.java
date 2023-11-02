/*
Pettrus Konnoth
AP CSA
Zork Game
October 19 2023
Period 7
Game class
 */

import java.util.ArrayList;
import java.sql.*;
import java.util.Scanner;



// create an extra method to scan the map and print out something like the number of hazards or like the number of items in on the map



public class Game {

    //declare the game variables


    private int playerX; // Player's x-coordinate
    private int playerY; // Player's y-coordinate

    private int wallLength = 24;
    private int wallHeight = 24;
    private int wallHitCounter;

    private ArrayList<Item> backPack;

    private Item[][] itemsOnMap;

    private ArrayList<Hazard> hazards = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);



    public Game() {
        //initialize the game variables
        initializeGameMap();
        backPack = new ArrayList<>(8);
        itemsOnMap = new Item[wallLength][wallHeight];

        //Item knife = new Item("knife", "a sharp knife", 12, 13);
        //Item gun = new Item("gun", "a loaded gun", 12, 14);

        //itemsOnMap[knife.getX()][knife.getY()] = knife;
        //itemsOnMap[gun.getX()][gun.getY()] = gun;

        loadItemsFromDatabase();

        Hazard covidVirus = new Hazard("Covid Virus", "A dangerous virus", 8, 8, "vaccine");
        hazards.add(covidVirus);

        System.out.println("You stand at the heart of a city under lockdown. The once bustling streets are now empty, the city's vibrant life subdued by the relentless pandemic. The stillness is eerie as you navigate the desolate streets, searching for signs of life. What would you like to do?\n");

    }

    public Item getItemAtLocation(int x, int y) {
        return itemsOnMap[x][y];
    }


    public void pickUpItem(int x, int y) {
        if (itemsOnMap[x][y] != null) {
            if (backPack.size() < 8) { // Check if the backpack is not full (less than the maximum limit)
                Item item = itemsOnMap[x][y];
                backPack.add(item);
                itemsOnMap[x][y] = null; // Remove the item from the map
                System.out.println("You picked up the " + item.getName());
            } else {
                System.out.println("Your backpack is full. You can't pick up more items.");
            }
        } else {
            System.out.println("There is nothing to pick up at your current location.");
        }
    }




    public void dropItem(int x, int y) {
        if (!backPack.isEmpty()) {
            Item item = backPack.remove(backPack.size() - 1);
            itemsOnMap[x][y] = item; // Place the item on the map
            System.out.println("You dropped the " + item.getName());
        } else {
            System.out.println("Your backpack is empty. There's nothing to drop.");
        }
    }

    // Method to initialize the game map
    private void initializeGameMap() {
        playerX = 12;
        playerY = 12;

    }


    public void loadItemsFromDatabase() {
        String dbURL = "jdbc:mysql://localhost:3306/Zork";
        String dbUser = "root";
        String dbPassword = "Irine2012";

        try {
            Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            String selectSQL = "SELECT * FROM items";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Loading items from database...");

            while (resultSet.next()) {
                String itemName = resultSet.getString("name");
                String itemDescription = resultSet.getString("description");
                int itemX = resultSet.getInt("x");
                int itemY = resultSet.getInt("y");
                int id = resultSet.getInt("id");

                /*
                // Print the queried values
                System.out.println("Item ID: " + id);
                System.out.println("Item name: " + itemName);
                System.out.println("Item description: " + itemDescription);
                System.out.println("Item x: " + itemX);
                System.out.println("Item y: " + itemY);
                System.out.println("\n");

                 */


                Item item = new Item(itemName, itemDescription, itemX, itemY);
                itemsOnMap[itemX][itemY] = item;

                }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void printPlayerPosition() {
        System.out.printf("(%d,%d) \n",playerX,playerY);
    }
    // Method to parse the user input and return a Direction enum

    public Direction parseCommandLine(String userInput) {
        userInput = userInput.toLowerCase();

        //creating the switch statement to parse the direction with 2 arguments
        return switch (userInput) {
            case "north", "n" -> Direction.NORTH;
            case "south", "s" -> Direction.SOUTH;
            case "east", "e" -> Direction.EAST;
            case "west", "w" -> Direction.WEST;
            case "pickup","pick up","pick" -> Direction.PickUP;
            case "drop" -> Direction.DROP;
            case "backpack","inventory" -> Direction.BACKPACK;
            case "help" -> Direction.HELP;
            case "scan" -> Direction.SCAN;
            case "info" -> Direction.INFO;
            case "use" -> Direction.USE;
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
            // Check if there's a hazard at the new position
            Hazard hazard = getHazardAtLocation(newX, newY);

            if (hazard != null) {
                System.out.println("You have encountered a hazard: " + hazard.getName());

                System.out.print("What would you like to do? (Use/Ignore): ");
                String userInput = scanner.nextLine();
                handleHazard(userInput, newX, newY);

                playerY = newY;
                playerX = newX;
            } else {
                // Check if there's an item at the new position
                Item item = itemsOnMap[newX][newY];
                if (item != null) {
                    System.out.println("You found an item: " + item.getName());
                }

                // Update the player's position
                playerX = newX;
                playerY = newY;
            }
        } else {
            // Print an error message if the new position is invalid
            System.out.println("You can't move in that direction. You've hit a wall.");

            // Increment the wall hit counter
            wallHitCounter++;

            // Check if the counter has reached the threshold (e.g., 5 times)
            if (wallHitCounter >= 5) {
                // Trigger the action to show that the player has hit the wall 5 times
                triggerAction(); // Execute the action when the threshold is reached
            }
        }
    }



    private void handleHazard(String userInput, int x, int y) {
        Hazard hazard = getHazardAtLocation(x, y);
        if (hazard != null) {
            if ("use".equalsIgnoreCase(userInput)) {
                if (hasRequiredItemToDefeatHazard(hazard)) {
                    System.out.println("You successfully used the required item to deal with the hazard: " + hazard.getName());
                    hazards.remove(hazard); // Remove the hazard from the list
                } else {
                    System.out.println("You don't have the required item to deal with the hazard: " + hazard.getName());
                }
            } else if ("ignore".equalsIgnoreCase(userInput)) {
                System.out.println("You ignored the hazard: " + hazard.getName());
            } else {
                System.out.println("Invalid action. You can either 'Use' or 'Ignore' the hazard.");
            }
        } else {
            System.out.println("There's no hazard to interact with.");
        }
    }



    private boolean hasRequiredItemToDefeatHazard(Hazard hazard) {
        // Check if the user's backpack contains the required item to defeat the hazard
        for (Item item : backPack) {
            if (item.getName().equalsIgnoreCase(hazard.getRequiredItem())) {
                return true;
            }
        }
        return false;
    }

    private Hazard getHazardAtLocation(int x, int y) {
        for (Hazard hazard : hazards) {
            if (hazard.getX() == x && hazard.getY() == y) {
                return hazard;
            }
        }
        return null; // No hazard found at the given location
    }



    public int countItemsOnMap() {
        int itemCount = 0;

        for (int x = 0; x < wallLength; x++) {
            for (int y = 0; y < wallHeight; y++) {
                if (itemsOnMap[x][y] != null) {
                    itemCount++;
                }
            }
        }

        return itemCount;
    }




    private void triggerAction() {

        //print the action
        System.out.println("you hit the wall 5 time");
        wallHitCounter = 0;

    }
    //method to check if the position is valid
    private boolean isValidPosition(int x, int y) {
        return x > 0 && x < wallLength && y > 0 && y < wallHeight;
        //return true if the position is within the wall bounds
    }


    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public ArrayList<Item> getBackPack() {
        return backPack;
    }
}
