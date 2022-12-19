import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.InputMismatchException; // Import statements

public class Arena {

    private User player;
    private ArrayList<Weapon> weaponList;
    private ArrayList<Potion> potionList;
    Scanner input = new Scanner(System.in); // Creates a scanner for typing inputs into the command line

    /**
     * Creates a Arena that has a User, ArrayList of Weapons, and an ArrayList of Potions
     */
    public Arena(User p, ArrayList<Weapon> w, ArrayList<Potion> po) {
        player = p;
        weaponList = w;
        potionList = po;
    }

    /**
     * Asks the user if they want to fight, and if yes, takes them to a battle
     * @return an ArrayListPair of weapons and potions
     */
    public ArrayListPair start() {
        System.out.println(player.getName() + ", welcome to the Arena!");
        int totalMoneyEarned = 0; // Keeps track of all the money earned during the current Arena session
        while(true) {
            if(player.getHealth() <= 1) {
                player.setHealthTemporary(10);
            }
            System.out.println("What would you like to do?\n1. Fight\n2. Leave");
            int toDo = 0;
            while(toDo < 1 || toDo > 2) {
                toDo = checker();
            }
            if(toDo == 1) {
                System.out.println("Initiating battle...");
                int moneyEarned = battle();
                totalMoneyEarned += moneyEarned;
                System.out.println("CSCs Earned: " + moneyEarned);
                player.setMoney(moneyEarned);
                System.out.println("Current CSCs: " + player.getMoney());
            }
            else if(toDo == 2) {
                System.out.println("Arena Earnings this Session: " + totalMoneyEarned + " CSCs");
                return new ArrayListPair(weaponList, potionList);
            }
        }
    }

    /**
     * Creates an enemy that the user fights
     * @return an integer representing the money earned from one battle
     */
    public int battle() {
        Random random = new Random();
        String[] enemyNames = {"Goblin", "Mage", "Orc", "Elf", "Demon", "Gremlin", "Ghoul"};
        String enemyName = enemyNames[random.nextInt(enemyNames.length)];
        int enemyStrength = random.nextInt(7) + 2;
        int enemyHealth = random.nextInt(40) + 10; // Creates an enemy
        System.out.println("Now Fighting...\n" + enemyName + "\n> Strength: " + enemyStrength + "\n> Health: " + enemyHealth + "\n" + player.getName() + "'s Stats\n> Health: " + player.getHealth());
        Weapon currentWeapon = new Weapon("null", "null", 0, 0, 0);
        Boolean weaponEquipped = false;
        while(true) {
            System.out.println("What would you like to do?\n1. Select Weapon\n2. Select Potion\n3. Fight\n4. Flee");
            int toDo = 0;
            while(toDo < 1 || toDo > 4) {
                toDo = checker();;
            }
            if(toDo == 1) {
                if(weaponList.size() > 0) {
                    currentWeapon = selectWeapon(); // Makes the current weapon the user is holding the one the user picks
                    weaponEquipped = true;
                }
                else {
                    System.out.println("You do not have a Weapon in your inventory!");
                }
            }
            else if(toDo == 2) {
                if(potionList.size() > 0) {
                    Potion currentPotion = selectPotion();
                    player.setHealth(currentPotion.getHealth()); // Increases the user's health based on the potion's heal points
                    potionList.remove(currentPotion); // Removes the potion from the user's inventory
                    if(player.getHealth() > 100) {
                        player.setHealthTemporary(100);
                    }
                }
                else {
                    System.out.println("You do not have a Potion in your inventory!");
                }
            }
            else if(toDo == 3) {
                if(weaponEquipped == true) {
                    enemyHealth -= currentWeapon.getStrength(); // Lowers the enemy health based on the weapon's strength
                    currentWeapon.setHealth(-1); // Decreases one use from the weapon
                    if(currentWeapon.getHealth() <= 0) {
                        weaponEquipped = false;
                        weaponList.remove(currentWeapon); // Removes the weapon from the user's inventory if it's health reaches 0 (it breaks)
                        System.out.println("Your weapon has broken!");
                    }
                }
                else {
                    enemyHealth--; // Decreases the enemy's health by 1 if the user has no weapon equipped
                    System.out.println("You have dealt 1 damage to the enemy!");
                }
                if(enemyHealth <= 0) {
                    return random.nextInt(110) + 40; // Returns a random amount of money if the enemy is defeated
                }
                player.setHealth(-enemyStrength); // Removes the user's health each fighting round
                if(player.getHealth() <= 0) {
                    System.out.println("You are out of health!");
                    return 0; // Exits the battle if the user's health reaches 0
                }
                System.out.println(enemyName + "\n> Strength: " + enemyStrength + "\n> Health: " + enemyHealth + "\n" + player.getName() + "'s Stats\n> Health: " + player.getHealth());
                if(weaponEquipped == true) {
                    System.out.println("> Weapon Strength: " + currentWeapon.getStrength() + "\n> Weapon Uses: " + currentWeapon.getHealth());
                }
            }
            else if(toDo == 4) {
                return 0;
            }
        }
    }

    /**
     * Allows the user to choose a weapon from their inventory
     * @return a Weapon that the user can equip
     */
    public Weapon selectWeapon() {
        for(int i = 0; i < weaponList.size(); i++) {
            System.out.println(i+1 + ". " + weaponList.get(i).getName()); // Prints out the list of weapons
        }
        int toDo = -1;
        while(toDo < 0 || toDo > weaponList.size()-1) {
            toDo = checker() - 1;
        }
        return weaponList.get(toDo);
    }

    /**
     * Allows the user to choose a potion from their inventory
     * @return a Potion that the user can consume
     */
    public Potion selectPotion() {
        for(int i = 0; i < potionList.size(); i++) {
            System.out.println(i+1 + ". " + potionList.get(i).getName() + "\n   > Healing Points: " + potionList.get(i).getHealth()); // Prints out the list of potions
        }
        int toDo = -1;
        while(toDo < 0 || toDo > potionList.size()-1) {
            System.out.println("Please enter a different number.");
            toDo = checker() - 1;
        }
        return potionList.get(toDo);
    }

    /**
     * Checks to make sure the user's input is a integer (not a string, boolean, etc)
     * @return an integer based on the user's valid or invalid response
     */
    public int checker() {
        try {
            int number = input.nextInt();
            return number;
        }
        catch(InputMismatchException e) {
            System.out.println("Please enter a different input!");
            input.next();
            return 0;
        }
    }

    /**
     * Main method for testing
     * @param args[] An empty array of Strings
     */
    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<Item>();
        ArrayList<Weapon> weaponInventory = new ArrayList<Weapon>();
        ArrayList<Potion> potionInventory = new ArrayList<Potion>();
        User player = new User("Player", inventory, 100, 0, 0);
        Arena arena = new Arena(player, weaponInventory, potionInventory);
        arena.start();
    }
}
