public class Weapon extends Item {

    private int strength;
    private int health;
    
    public Weapon(String n, String d, int b, int s, int h) {
        super(n, d, b);
        strength = s;
        health = h;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int hp) {
        this.health += hp;
    }
    public static void main(String[] args) {

    }
}
