import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Item> userInventory;
    private int health;
    private int defense;
    private int money;

    public User(String n, ArrayList<Item> i, int h, int d, int m) {
        name = n;
        userInventory = i;
        health = h;
        defense = d;
        money = m;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Item> getInventory() {
        return this.userInventory;
    }

    public void removeItemInInventory(int item) {
        this.userInventory.remove(item);
    }

    public void addItemToInventory(Item item) {
        this.userInventory.add(item);
    }

    public int getHealth() {
        return this.health;
    }

    public int getDefense() {
        return this.defense;
    }
    
    public int getMoney() {
        return this.money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health += health;
    }

    public void setHealthTemporary(int health) {
        this.health = health;
    }

    public void setMoney(int amount) {
        this.money += amount;
    }

    public static void main(String[] args) {
        //new User();
    }
}