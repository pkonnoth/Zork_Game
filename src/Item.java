/*
Pettrus Konnoth
AP CSA
Zork Game
October 19 2023
Period 7
Item Class
 */

public class Item {
    private String name;
    private String description;
    private int x;
    private int y;

    //full constructor
    public Item(String name, String description, int x, int y) {
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
    }
    //partial  constructor
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.x = 0;
        this.y = 0;
    }
    //partial default constructor
    public Item() {
        this.name = "Item";
        this.description = "Item";
        this.x = 0;
        this.y = 0;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //



    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
