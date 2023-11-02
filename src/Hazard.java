public class Hazard {
    private String name;
    private String description;
    private int x;
    private int y;
    private String requiredItem;

    public Hazard(String name, String description, int x, int y, String requiredItem) {
        // Constructor for Hazard
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
        this.requiredItem = requiredItem;
    }

    public String getRequiredItem() {
        return requiredItem;
    }



    // Getters for name, description, x, and y


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
}
