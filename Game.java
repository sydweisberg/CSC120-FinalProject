import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Game {

    private User player;

    private ArrayList<String> commands = new ArrayList<String>(Arrays.asList("Market Town", "The Mines", "Arena"));
    private Boolean[] beenThere = {false, false, false, false};
    Scanner input = new Scanner(System.in);
    private ArrayList<Item> unusualItems = new ArrayList<Item>();
    private ArrayList<Weapon> weaponItems = new ArrayList<Weapon>();
    private ArrayList<Weapon> weaponInventory = new ArrayList<Weapon>();
    private boolean gliderObtained = false;

    public Game(User p) {
        player = p;
    }

    public void welcome() {
        System.out.println("What is your name, adventurer?");
        player.setName(input.nextLine());
        System.out.println(player.getName() + ", welcome to the realm of Casus!");
        System.out.println("Casus is a text-based adventure game. The goal of the game is up to the player.");
        System.out.println("Whether you want to collect money (CSC), buy items, or battle enemies, your destiny lies in your choices.");
        travelMessage();
    }

    public void travelMessage() {
        while(true) {
            System.out.println("Where would you like to go?");
            for(int i = 0; i < commands.size(); i++) {
                System.out.println(i+1 + ". " + commands.get(i));
            }
            int location = input.nextInt();
            if(location == 1) {
                marketTown();
            }
            else if(location == 2) {
                Mine mine = new Mine(player);
                mine.start();
            }
            else if(location == 3) {
                Arena arena = new Arena(player, weaponInventory);
                weaponInventory = arena.start();
            }
            else if(location == 4 && gliderObtained == true) {
                WizardTower tower = new WizardTower(player);
                tower.start();
            }
            else {
                System.out.println("Please enter a different input.");
            }
        }
    }

    public void marketTown() {
        if(beenThere[1] == false) {
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
            currentShop = input.nextInt();
            while(currentShop < 1 || currentShop > 4) {
                System.out.println("Please enter a different number.");
                currentShop = input.nextInt();
            }
            if(currentShop == 1) {
                fightingShop();
            }
            else if(currentShop == 2) {
                System.out.println("This is currently in progress.");
            }
            else if(currentShop == 3) {
                unusualShop();
            }
            else {
                travelMessage();
            }
        }
    }

    public void fightingShop() {
        System.out.println("Welcome to the Fighting Shop!");
        while(true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Buy Weapons\n2. Leave");
            int toDo  = input.nextInt();
            while(toDo < 1 || toDo > 2) {
                System.out.println("Please enter a different number.");
                toDo = input.nextInt();
            }
            if(toDo == 1) {
                System.out.println("Current CSCs: " + player.getMoney());
                System.out.println("Which item would you like to purchase?");
                while(weaponItems.size() < 3) {
                    weaponItems.add(weaponCreator());
                }
                for(int i = 0; i < weaponItems.size(); i++) {
                    System.out.println(i+1 +". " + weaponItems.get(i).getName() + "\n   > Description: " + weaponItems.get(i).getDescription() + "\n   > Strength: " + weaponItems.get(i).getStrength() + "\n   > Uses: " + weaponItems.get(i).getHealth() + "\n   > Cost: " + weaponItems.get(i).getBuyAmount());
                    if(i == weaponItems.size()-1) {
                        System.out.println(i+2 + ". None");
                    }
                }
                int itemID = input.nextInt() - 1;
                while(itemID < 0 || itemID > weaponItems.size()) {
                    System.out.println("Please enter a different number.");
                    itemID = input.nextInt() - 1;
                }
                if(itemID == weaponItems.size()) {
                    System.out.print("");
                }
                else if(player.getMoney() >= weaponItems.get(itemID).getBuyAmount()) {
                    player.setMoney(-weaponItems.get(itemID).getBuyAmount());
                    System.out.println("You have bought: " + weaponItems.get(itemID).getName());
                    player.addItemToInventory(weaponItems.get(itemID));
                    weaponInventory.add(weaponItems.get(itemID)); // remember to remove this later on
                    weaponItems.remove(itemID);
                    System.out.println("Current CSCs: " + player.getMoney());
                }
                else {
                    System.out.println("You cannot afford this item!");
                }    
            }
            else if(toDo == 2) {
                break;
            }
        }
    }

    public Weapon weaponCreator() {
        String[][] weaponList = {{"Sword", "An object with a blade and a hilt."}, {"Lance", "A long staff with a pointed end."}, {"Greatsword", "A bulky object with a blade and a hilt."}, {"Staff", "A magical stick-like object."}, {"Bow", "A curved weapon joined by a string."}, {"Axe", "An object with a steel blade attatched."}};
        int[][] weaponInfo = {{100, 5, 10}, {50, 4, 12}, {150, 10, 5}, {25, 2, 20}, {75, 3, 15}, {100, 5, 10}};
        Random random = new Random();
        int index = random.nextInt(5);
        int strength = random.nextInt(weaponInfo[index][1] + 5) + weaponInfo[index][1];
        int health = random.nextInt(weaponInfo[index][2] + 8) + weaponInfo[index][2];
        Weapon createdWeapon = new Weapon(weaponList[index][0], weaponList[index][1], weaponInfo[index][0], strength, health);
        return createdWeapon;
    }

    public void unusualShop() {
        System.out.println("Welcome to the Unusual Shop!");
        while(true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Buy Items\n2. Sell Items\n3. Leave");
            int toDo  = input.nextInt();
            while(toDo < 1 || toDo > 3) {
                System.out.println("Please enter a different number.");
                toDo = input.nextInt();
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
                    int itemID = input.nextInt() - 1;
                    while(itemID < 0 || itemID > unusualItems.size()) {
                        System.out.println("Please enter a different number.");
                        itemID = input.nextInt() - 1;
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
                    int itemID = input.nextInt() - 1;
                    while(itemID < 0 || itemID > unusualItems.size()) {
                        System.out.println("Please enter a different number.");
                        itemID = input.nextInt() - 1;
                    }
                    if((itemID <= player.getInventory().size()) && !player.getInventory().get(itemID).getName().equals("Glider")) {
                        player.setMoney(player.getInventory().get(itemID).getSellAmount());
                        System.out.println("You have sold: " + player.getInventory().get(itemID).getName());
                        player.removeItemInInventory(itemID);
                        System.out.println("Current CSCs: " + player.getMoney());
                    }
                } // FIX PROBLEM WHERE U TYPE 2 AND IT NO WORK :(
                else {
                    System.out.println("You do not have any items or cannot sell the item chosen!");
                }
            }
            if(toDo == 3) {
                break;
            }
        }
    }

    public void addItems() {
        unusualItems.add(new Item("Glider", "Allows you to move around the realm from the sky. Perhaps you can access new areas.", 500));
        unusualItems.add(new Item("Crown", "A stunning headpiece.", 100000));
    }

    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<Item>();
        User player = new User("Player", inventory, 100, 0, 0);
        Game game = new Game(player);
        game.addItems();
        game.welcome();
    }
}
