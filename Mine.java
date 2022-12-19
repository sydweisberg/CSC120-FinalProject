import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.InputMismatchException; // Import statements

public class Mine {

    User player;
    Scanner input = new Scanner(System.in); // Creates a scanner for typing inputs into the command line
    public String[] words = {"abstraction", "accessor", "aggregation", "algorithm", "argument", "arrays", "association", "bit", "boolean", "byte", "class", "coding", "composition", "computer", "conditional", "constructor", "declaration", "dictionary", "double", "else", "encapsulation", "exception", "float", "functions", "if", "inheritance", "initialize", "integer", "interface", "java", "jupyter", "linux", "list", "loops", "manipulator", "networks", "object", "polymorphism", "python", "software", "statement", "static", "string", "variable", "while"}; // List of words to use in the typing game

    /**
     * Creates a Mine that has a User
     */
    public Mine(User p) {
        player = p;
    }

    /**
     * Asks the user if they want to mine, and if yes, takes them to a minigame
     */
    public void start() {
        System.out.println("Welcome to the Mine!");
        while(true) {
            System.out.println("What would you like to do?\n1. Mine\n2. Leave");
            int toDo = 0;
            while(toDo < 1 || toDo > 2) {
                toDo = checker();
            }
            if(toDo == 1) {
                mineMaterials();
            }
            else if(toDo == 2) {
                //input.close();
                break;
            }
            
        }
    }

    /**
     * Starts a typing minigame that can make the user money depending on if they type the phrase correctly
     */
    public void mineMaterials() {
        String wordList = "";
        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            wordList = wordList + words[random.nextInt(words.length - 0) + 0]; // Chooses five random words and adds them to a list
            if(i < 4) {
                wordList = wordList + " ";
            }
        }
        System.out.println("Please type...\n" + wordList);
        String userInput = input.next().toLowerCase(); 
        userInput = userInput + input.nextLine();
        if(userInput.equals(wordList)) { // Compares the user's input to the list of words and checks if they match up
            int oreWorth = random.nextInt(60) + 10;
            System.out.println("Correct! You have mined an ore worth " + oreWorth + "!");
            player.setMoney(oreWorth);
            System.out.println("Current CSCs: " + player.getMoney());
        }
        else {
            System.out.println("Your answer is incorrect!");
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
     * Main method for testing
     * @param args[] An empty array of Strings
     */
    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<Item>();
        User player = new User("Player", inventory, 100, 0, 0);
        Mine mine = new Mine(player);
        mine.start();
    }
}
