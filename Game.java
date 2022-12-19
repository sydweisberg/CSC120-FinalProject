import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException; // Import statements

public class Game {

    private User player;

    private ArrayList<String> commands = new ArrayList<String>(Arrays.asList("Market Town", "The Mines", "Arena"));
    private Boolean[] beenThere = {false, false, false, false}; // Array of booleans to determine whether or not a User has been to a location alredy
    Scanner input = new Scanner(System.in); // Creates a scanner for typing inputs into the command line
    private ArrayList<Item> unusualItems = new ArrayList<Item>();
    private ArrayList<Weapon> weaponItems = new ArrayList<Weapon>();
    private ArrayList<Potion> potionItems = new ArrayList<Potion>();
    private ArrayList<Weapon> weaponInventory = new ArrayList<Weapon>();
    private ArrayList<Potion> potionInventory = new ArrayList<Potion>();
    private boolean gliderObtained = false; // Variable to determine if the glider has been purchased

    /**
     * Creates a Game that has a User
     */
    public Game(User p) {
        player = p;
    }

    /**
     * Provides a brief introduction to the game and asks the User for their name
     */
    public void welcome() {
        System.out.println("What is your name, adventurer?");
        player.setName(input.nextLine()); // Sets the player's name to the input
        System.out.println(player.getName() + ", welcome to the realm of Casus!");
        System.out.println("Casus is a text-based adventure game. The goal of the game is up to the player.");
        System.out.println("Whether you want to collect money (CSC), buy items, or battle enemies, your destiny lies in your choices.");
        travelMessage();
    }

    /**
     * Displays a list of locations and allows the user to repeatedly visit different places based on their input
     */
    public void travelMessage() {
        while(true) {
            System.out.println("Where would you like to go?");
            for(int i = 0; i < commands.size(); i++) {
                System.out.println(i+1 + ". " + commands.get(i));
            }
            int location = 0;
            while(location < 1 || location > 4) 
            {
                location = checker();
            }
            if(location == 1) {
                marketTown();
            }
            else if(location == 2) {
                Mine mine = new Mine(player);
                mine.start();
            }
            else if(location == 3) {
                Arena arena = new Arena(player, weaponInventory, potionInventory);
                ArrayListPair lists = arena.start();
                weaponInventory = lists.getWeaponList(); // Sets the weaponInventory to the updated inventory from the Arena results
                potionInventory = lists.getPotionList(); // Sets the potionInventory to the updated inventory from the Arena results
            }
            else if(location == 4 && gliderObtained == true) { // Allows the user to go to the WizardTower if they have purchased the glider at the shop
                WizardTower tower = new WizardTower(player);
                tower.start();
            }
            else {
                System.out.println("Please enter a different input.");
            }
        }
    }

    /**
     * Displays a list of shops and allows the user to repeatedly return to shops
     */
    public void marketTown() {
        if(beenThere[1] == false) { // Checks if the user has been to the location already, and displays a message if they haven't
            System.out.println("Welcome to Market Town!\nHere you can buy goods such as Weapons, Gear, and other Items.");
            beenThere[1] = true;
        }
        else {
            System.out.println("Welcome to Market Town!");
        }
        while(true) {
            int currentShop = 0;
            System.out.println("Where would you like to shop?");
            System.out.println("1. Fighting Shop\n2. Healing Shop\n3. Unusual Shop\n4. Leave");
            while(currentShop < 1 || currentShop > 4) {
                currentShop = checker();
            }
            if(currentShop == 1) {
                fightingShop();
            }
            else if(currentShop == 2) {
                healingShop();
            }
            else if(currentShop == 3) {
                unusualShop();
            }
            else {
                travelMessage();
            }
        }
    }

    /**
     * Allows the user to purchase weapons
     */
    public void fightingShop() {
        System.out.println("Welcome to the Fighting Shop!");
        while(true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Buy Weapons\n2. Leave");
            int toDo  = 0;
            while(toDo < 1 || toDo > 2) {
                toDo = checker();
            }
            if(toDo == 1) {
                System.out.println("Current CSCs: " + player.getMoney()); // Displays the player's money
                System.out.println("Which item would you like to purchase?");
                while(weaponItems.size() < 3) {
                    weaponItems.add(weaponCreator()); // Adds items to the shop if there are less than three currently purchasable
                }
                for(int i = 0; i < weaponItems.size(); i++) {
                    System.out.println(i+1 +". " + weaponItems.get(i).getName() + "\n   > Description: " + weaponItems.get(i).getDescription() + "\n   > Strength: " + weaponItems.get(i).getStrength() + "\n   > Uses: " + weaponItems.get(i).getHealth() + "\n   > Cost: " + weaponItems.get(i).getBuyAmount()); // Displays the list of three weapons
                    if(i == weaponItems.size()-1) {
                        System.out.println(i+2 + ". None");
                    }
                }
                int itemID = -1;
                while(itemID < 0 || itemID > weaponItems.size()) {
                    itemID = checker() - 1;
                }
                if(itemID == weaponItems.size()) {
                    System.out.print("");
                }
                else if(player.getMoney() >= weaponItems.get(itemID).getBuyAmount()) {
                    player.setMoney(-weaponItems.get(itemID).getBuyAmount()); 
                    System.out.println("You have bought: " + weaponItems.get(itemID).getName());
                    player.addItemToInventory(weaponItems.get(itemID));
                    weaponInventory.add(weaponItems.get(itemID));
                    weaponItems.remove(itemID);
                    System.out.println("Current CSCs: " + player.getMoney());
                }
                else {
                    System.out.println("You cannot afford this item!");
                }    
            }
            else if(toDo == 2) {
                break; // Exits the shop
            }
        }
    }

    /**
     * Allows the user to purchase potions
     */
    public void healingShop() {
        System.out.println("Welcome to the Healing Shop!");
        while(true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Buy Potions\n2. Leave");
            int toDo  = 0;
            while(toDo < 1 || toDo > 2) {
                toDo = checker();
            }
            if(toDo == 1) {
                System.out.println("Current CSCs: " + player.getMoney()); // Displays the player's money
                System.out.println("Which item would you like to purchase?");
                while(potionItems.size() < 3) {
                    potionItems.add(potionCreator()); // Adds items to the shop if there are less than three currently purchasable
                }
                for(int i = 0; i < potionItems.size(); i++) {
                    System.out.println(i+1 +". " + potionItems.get(i).getName() + "\n   > Description: " + potionItems.get(i).getDescription() + "\n   > Healing Points: " + potionItems.get(i).getHealth() + "\n   > Cost: " + potionItems.get(i).getBuyAmount()); // Displays the list of potions
                    if(i == potionItems.size()-1) {
                        System.out.println(i+2 + ". None");
                    }
                }
                int itemID = -1;
                while(itemID < 0 || itemID > potionItems.size()) {
                    itemID = checker() - 1;
                }
                if(itemID == potionItems.size()) {
                    System.out.print("");
                }
                else if(player.getMoney() >= potionItems.get(itemID).getBuyAmount()) {
                    player.setMoney(-potionItems.get(itemID).getBuyAmount());
                    System.out.println("You have bought: " + potionItems.get(itemID).getName());
                    player.addItemToInventory(potionItems.get(itemID));
                    potionInventory.add(potionItems.get(itemID));
                    potionItems.remove(itemID);
                    System.out.println("Current CSCs: " + player.getMoney());
                }
                else {
                    System.out.println("You cannot afford this item!");
                }    
            }
            else if(toDo == 2) {
                break; // Exits the shop
            }
        }
    }

    /**
     * Creates a weapon with unique stats
     * @return A weapon with unique stats
     */
    public Weapon weaponCreator() {
        String[][] weaponList = {{"Sword", "An object with a blade and a hilt."}, {"Lance", "A long staff with a pointed end."}, {"Greatsword", "A bulky object with a blade and a hilt."}, {"Staff", "A magical stick-like object."}, {"Bow", "A curved weapon joined by a string."}, {"Axe", "An object with a steel blade attatched."}};
        int[][] weaponInfo = {{100, 5, 10}, {50, 4, 12}, {150, 10, 5}, {25, 2, 20}, {75, 3, 15}, {100, 5, 10}};
        Random random = new Random();
        int index = random.nextInt(5); // Randomly chooses a weapon name
        int strength = random.nextInt(weaponInfo[index][1] + 5) + weaponInfo[index][1]; // Randomly chooses a weapon's strength
        int health = random.nextInt(weaponInfo[index][2] + 8) + weaponInfo[index][2]; // Randomly chooses a weapon's uses
        Weapon createdWeapon = new Weapon(weaponList[index][0], weaponList[index][1], weaponInfo[index][0], strength, health);
        return createdWeapon;
    }
    
    /**
     * Creates a potion with unique stats
     * @return A potion with unique stats
     */
    public Potion potionCreator() {
        Random random = new Random();
        int health = random.nextInt(25) + 10; // Randomly chooses a potion's heal points
        int buyPrice = health * 2;
        Potion createdPotion = new Potion("Potion", "An item that heals the user's health.", buyPrice, health);
        return createdPotion;
    }

    /**
     * Allows the user to purchase unique items
     */
    public void unusualShop() {
        System.out.println("Welcome to the Unusual Shop!");
        while(true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Buy Items\n2. Sell Items\n3. Leave");
            int toDo  = 0;
            while(toDo < 1 || toDo > 3) {
                toDo = checker();
            }
            if(toDo == 1) {
                if(unusualItems.size() > 0) {
                    System.out.println("Current CSCs: " + player.getMoney());
                    System.out.println("Which item would you like to purchase?");
                    for(int i = 0; i < unusualItems.size(); i++) {
                        System.out.println(i+1 +". " + unusualItems.get(i).getName() + "\n   > Description: " + unusualItems.get(i).getDescription() + "\n   > Cost: " + unusualItems.get(i).getBuyAmount());
                        if(i == unusualItems.size()-1) {
                            System.out.println(i+2 + ". None");
                        }
                    }
                    int itemID = -1;
                    while(itemID < 0 || itemID > unusualItems.size()) {
                        System.out.println("Please enter a different number.");
                        itemID = checker() - 1;
                    }
                    if(itemID == unusualItems.size()) {
                        System.out.print("");
                    }
                    else if(player.getMoney() >= unusualItems.get(itemID).getBuyAmount()) {
                        player.setMoney(-unusualItems.get(itemID).getBuyAmount());
                        if(unusualItems.get(itemID).getName().equals("Glider")) {
                            gliderObtained = true;
                            commands.add("Wizard Tower");
                        }
                        if(unusualItems.get(itemID).getName().equals("Crown")) {
                            System.out.println("Congratulations on obtaining the crown! You've won the game, but you can keep playing if you would like!");
                        }
                        System.out.println("You have bought: " + unusualItems.get(itemID).getName());
                        player.addItemToInventory(unusualItems.get(itemID));
                        unusualItems.remove(itemID);
                        System.out.println("Current CSCs: " + player.getMoney());
                    }
                    else {
                        System.out.println("You cannot afford this item!");
                    }
                }
            }
            if(toDo == 2) {
                System.out.println("Which item would you like to sell?");
                if(player.getInventory().size() > 0) {
                    for(int i = 0; i < player.getInventory().size(); i++) {
                        System.out.println(i+1 +". " + player.getInventory().get(i).getName());
                    }
                    System.out.println(player.getInventory().size()+1 + ". " + "None");
                    int itemID = -1;
                    while(itemID < 0 || itemID > unusualItems.size()) {
                        itemID = checker() - 1;
                    }
                    if(itemID == player.getInventory().size()) {
                        break;
                    }
                    else if((itemID <= player.getInventory().size()) && !player.getInventory().get(itemID).getName().equals("Glider") && !player.getInventory().get(itemID).getName().equals("Crown")) {
                        player.setMoney(player.getInventory().get(itemID).getSellAmount());
                        System.out.println("You have sold: " + player.getInventory().get(itemID).getName());
                        weaponInventory.remove(player.getInventory().get(itemID));
                        player.removeItemInInventory(itemID);
                        System.out.println("Current CSCs: " + player.getMoney());
                    }
                }
                else {
                    System.out.println("You do not have any items or cannot sell the item chosen!");
                }
            }
            if(toDo == 3) {
                break; // Exits the shop
            }
        }
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
     * Adds unique items to an ArrayList that will be purchasable at the Unusual Shop
     */
    public void addItems() {
        unusualItems.add(new Item("Glider", "Allows you to move around the realm from the sky. Perhaps you can access new areas.", 500));
        unusualItems.add(new Item("Crown", "A stunning headpiece.", 100000));
    }

    /**
     * Main method which creates a game
     * @param args[] An empty array of Strings
     */
    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<Item>();
        User player = new User("Player", inventory, 100, 0, 0);
        Game game = new Game(player);
        game.addItems();
        game.welcome();
    }
}
