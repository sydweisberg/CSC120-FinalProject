import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Arena {

    private User player;
    private ArrayList<Weapon> weaponList;
    Scanner input = new Scanner(System.in);

    public Arena(User p, ArrayList<Weapon> w) {
        player = p;
        weaponList = w;
    }

    public ArrayList<Weapon> start() {
        System.out.println(player.getName() + ", welcome to the Arena!");
        int totalMoneyEarned = 0;
        while(true) {
            if(player.getHealth() <= 10) {
                player.setHealthTemporary(100); // CHANGE THIS TO player.setHealth(10) WHEN POTIONS ARE CREATED
            }
            System.out.println("What would you like to do?\n1. Fight\n2. Leave");
            int toDo = input.nextInt();
            while(toDo < 1 || toDo > 2) {
                System.out.println("Please enter a different number.");
                toDo = input.nextInt();
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
                return weaponList;
            }
        }
    }

    public int battle() {
        Random random = new Random();
        String[] enemyNames = {"Goblin", "Mage", "Orc", "Elf", "Demon", "Gremlin", "Ghoul"};
        String enemyName = enemyNames[random.nextInt(enemyNames.length)];
        int enemyStrength = random.nextInt(7) + 2;
        int enemyHealth = random.nextInt(40) + 10;
        System.out.println("Now Fighting...\n" + enemyName + "\n> Strength: " + enemyStrength + "\n> Health: " + enemyHealth + "\n" + player.getName() + "'s Stats\n> Health: " + player.getHealth());
        Weapon currentWeapon = new Weapon("null", "null", 0, 0, 0);
        Boolean weaponEquipped = false;
        while(true) {
            System.out.println("What would you like to do?\n1. Select Weapon\n2. Fight\n3. Flee");
            int toDo = input.nextInt();
            while(toDo < 1 || toDo > 3) {
                System.out.println("Please enter a different number.");
                toDo = input.nextInt();
            }
            if(toDo == 1) {
                if(weaponList.size() > 0) {
                    currentWeapon = selectWeapon();
                    weaponEquipped = true;
                }
                else {
                    System.out.println("You do not have a Weapon in your inventory!");
                }
            }
            else if(toDo == 2) {
                if(weaponEquipped == true) {
                    enemyHealth -= currentWeapon.getStrength();
                    currentWeapon.setHealth(-1);
                    if(currentWeapon.getHealth() <= 0) {
                        weaponEquipped = false;
                        weaponList.remove(currentWeapon);
                        System.out.println("Your weapon has broken!");
                    }
                }
                else {
                    enemyHealth--;
                    System.out.println("You have dealt 1 damage to the enemy!");
                }
                if(enemyHealth <= 0) {
                    return random.nextInt(100) + 25;
                }
                player.setHealth(-enemyStrength);
                if(player.getHealth() <= 0) {
                    System.out.println("You are out of health!");
                    return 0;
                }
                System.out.println(enemyName + "\n> Strength: " + enemyStrength + "\n> Health: " + enemyHealth + "\n" + player.getName() + "'s Stats\n> Health: " + player.getHealth());
                if(weaponEquipped == true) {
                    System.out.println("> Weapon Strength: " + currentWeapon.getStrength() + "\n> Weapon Uses: " + currentWeapon.getHealth());
                }
            }
            else if(toDo == 3) {
                return 0;
            }
        }
    }

    public Weapon selectWeapon() {
        for(int i = 0; i < weaponList.size(); i++) {
            System.out.println(i+1 + ". " + weaponList.get(i).getName());
        }
        int toDo = input.nextInt() - 1;
        while(toDo < 0 || toDo > weaponList.size()-1) {
            System.out.println("Please enter a different number.");
            toDo = input.nextInt() - 1;
        }
        return weaponList.get(toDo);
    }

    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<Item>();
        ArrayList<Weapon> weaponInventory = new ArrayList<Weapon>();
        User player = new User("Player", inventory, 100, 0, 0);
        Arena arena = new Arena(player, weaponInventory);
        arena.start();
    }
}
