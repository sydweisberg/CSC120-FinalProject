import java.util.ArrayList; // Import statements

/**
 * Represents a Pair of Arrays
 */
public class ArrayListPair {

    private ArrayList<Weapon> weaponList;
    private ArrayList<Potion> potionList;

    /**
     * Creates an ArrayListPair that has an ArrayList of Weapons and an ArrayList of Potions
     */
    public ArrayListPair(ArrayList<Weapon> w, ArrayList<Potion> p) {
        weaponList = w;
        potionList = p;
    }

    /* Accessors */
    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public ArrayList<Potion> getPotionList() {
        return potionList;
    }

    /**
     * Main method for testing
     * @param args[] An empty array of Strings
     */
    public static void main(String[] args) {

    }
}
