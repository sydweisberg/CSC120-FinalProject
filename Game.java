import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private User player;

    private ArrayList<String> commands = new ArrayList<String>(Arrays.asList("Market Town", "The Mines", "Arena"));
    private Boolean[] beenThere = {false, false, false, false};
    Scanner input = new Scanner(System.in);

    public Game(User p) {
        player = p;
    }

    public void welcome() {
        System.out.println(player.getName() + ", welcome to the realm of Casus!");
        System.out.println("Casus is a text-based adventure game. The goal of the game is up to the player.");
        System.out.println("Whether you want to collect money, buy items, or battle enemies, your destiny lies in your choices.");
        travelMessage();
    }

    public void travelMessage() {
        System.out.println("Where would you like to go?");
        for(int i = 0; i < commands.size(); i++) {
            System.out.println("> " + commands.get(i));
        }
        String location = input.nextLine();
        if(location.toLowerCase().equals("market town")) {
            marketTown();
        }
        else {
            System.out.println("Please enter a different input!");
            travelMessage();
        }
    }

    public void marketTown() {
        if(beenThere[1] == false) {
            System.out.println("Welcome to Market Town!\nHere you can buy goods such as Weapons, Gear, and other Items.");
        }
        String currentShop = shopMessage();
        if(currentShop.equals("fighting shop")) {
            // Program here
        }
        else if(currentShop.equals("healing shop")) {
            // Program here
        }
        else if(currentShop.equals("unusual shop")) {
            // Program here
        }

    }

    public String shopMessage() {
        System.out.println("Where would you like to shop?");
        System.out.println("> Fighting Shop\n> Healing Shop\n> Unusual Shop");
        String location = input.nextLine();
        if(!location.toLowerCase().equals("fighting shop") || !location.toLowerCase().equals("healing shop") || !location.toLowerCase().equals("unusual shop")) {
            System.out.println("Please enter a different input!");
            shopMessage();
        }
        return location.toLowerCase();
    }

    public static void main(String[] args) {
        String name = "Player";
        if (args.length > 0) { // Make sure this works next time
             name = args[0];
        }
        ArrayList<Item> inventory = new ArrayList<Item>();
        User player = new User(name, inventory, 100, 0, 0);
        Game game = new Game(player);
        game.welcome();
    }
}
