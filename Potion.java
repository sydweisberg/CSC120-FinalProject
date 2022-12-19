/**
 * Represents a Potion that is an Item
 */
public class Potion extends Item {

    int health;

    /**
     * Creates a Potion that has a name, description, buyAmount, and health
     */
    public Potion(String n, String d, int b, int h) {
        super(n, d, b);
        health = h;
    }

    /* Accessors */
    public int getHealth() {
        return this.health;
    }

    /**
     * Main method for testing
     * @param args[] An empty array of Strings
     */
    public static void main(String[] args) {

    }
}
