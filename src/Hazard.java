/*
Pettrus Konnoth
AP CSA
Zork Game
October 19 2023
Period 7
Game class
 */
public class Hazard {

    // Instance variables
    private String name;
    private String description;
    private int x;
    private int y;

    // Constructor for Hazard
    private String requiredItem;


    //full constructor
    public Hazard(String name, String description, int x, int y, String requiredItem) {
        // Constructor for Hazard
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
        this.requiredItem = requiredItem;
    }

    //partial default constructor
    public Hazard() {
        // Constructor for Hazard
        this.name = "Hazard";
        this.description = "Hazard";
        this.x = 10;
        this.y = 20;
        this.requiredItem = null;
    }

    //partial constructor

    public Hazard(String name, String description, int x, int y) {
        // Constructor for Hazard
        this.name = "Hazard";
        this.description = description;
        this.x = 21;
        this.y = 21;
        this.requiredItem = null;
    }
    // Constructor for Hazard

    public String getRequiredItem() {
        return requiredItem;
    }



    // Getters for name, description, x, and y


    // Setters for name, description, x, and y
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    // Getters for name, description, x, and y
    public void setDescription(String description) {
        this.description = description;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // Setters for name, description, x, and y
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
