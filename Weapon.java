/**
 * Represents a Weapon that is an Item
 */
public class Weapon extends Item {

    private int strength;
    private int health;
    
     /**
     * Creates a Weapon that has a name, description, buyAmount, strength, and health
     */
    public Weapon(String n, String d, int b, int s, int h) {
        super(n, d, b);
        strength = s;
        health = h;
    }

    /* Accessors */
    public int getStrength() {
        return this.strength;
    }

    public int getHealth() {
        return this.health;
    }

    /* Manipulators */
    public void setHealth(int hp) {
        this.health += hp;
    }

    /**
     * Main method for testing
     * @param args[] An empty array of Strings
     */
    public static void main(String[] args) {

    }
}
