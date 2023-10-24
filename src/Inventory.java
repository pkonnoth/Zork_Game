/*
Pettrus Konnoth
AP CSA
Zork Game
October 19 2023
Period 7
 */
import java.util.ArrayList;

public class Inventory {

    //creating the arraylist for the items
    private ArrayList<Item> items;

    //constructor for the inventory
    public Inventory() {
        items = new ArrayList<>();
    }


    //method to add and remove items
    public void addItem(Item item) {
        items.add(item);
    }

    //method to remove items
    public void removeItem(Item item) {
        items.remove(item);
    }


    public ArrayList<Item> getItems() {
        return items;
    }
}
