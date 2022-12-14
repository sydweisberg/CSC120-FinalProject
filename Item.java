public class Item {

    private String name;
    private String description;
    private int buyAmount;

    public Item(String n, String d, int b) {
        name = n;
        description = d;
        buyAmount = b;
    }

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

    public static void main(String[] args) {
        //new Item();
    }
}
