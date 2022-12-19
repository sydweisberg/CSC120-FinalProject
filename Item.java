/**
 * Represents a Item
 */
public class Item {

    private String name;
    private String description;
    private int buyAmount;

    /**
     * Creates a Item that has a name, description, and buyAmount
     */
    public Item(String n, String d, int b) {
        name = n;
        description = d;
        buyAmount = b;
    }

    /* Accessors */
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getBuyAmount() {
        return this.buyAmount;
    }

    public int getSellAmount() {
        int sellAmount = buyAmount / 2;
        return sellAmount;
    }

    /**
     * Main method for testing
     * @param args[] An empty array of Strings
     */
    public static void main(String[] args) {
        
    }
}
