import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Mine {

    User player;
    Scanner input = new Scanner(System.in);
    public String[] words = {"abstraction", "accessor", "aggregation", "algorithm", "argument", "arrays", "association", "bit", "boolean", "byte", "class", "coding", "composition", "computer", "conditional", "constructor", "declaration", "dictionary", "double", "else", "encapsulation", "exception", "float", "functions", "if", "inheritance", "initialize", "integer", "interface", "java", "jupyter", "linux", "list", "loops", "manipulator", "networks", "object", "polymorphism", "python", "software", "statement", "static", "string", "variable", "while"};

    public Mine(User p) {
        player = p;
    }

    public void start() {
        System.out.println("Welcome to the Mine!");
        while(true) {
            System.out.println("What would you like to do?\n1. Mine\n2. Leave");
            int toDo = input.nextInt();
            if(toDo < 1 || toDo > 2) {
                System.out.println("Please enter a different input!");
            }
            else {
                if(toDo == 1) {
                    mineMaterials();
                }
                else if(toDo == 2) {
                    //input.close();
                    break;
                }
            }
            
        }
    }

    public void mineMaterials() {
        String wordList = "";
        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            wordList = wordList + words[random.nextInt(words.length - 0) + 0];
            if(i < 4) {
                wordList = wordList + " ";
            }
        }
        System.out.println("Please type...\n" + wordList);
        String userInput = input.next().toLowerCase(); 
        userInput = userInput + input.nextLine();
        if(userInput.equals(wordList)) {
            int oreWorth = random.nextInt(40) + 10;
            System.out.println("Correct! You have mined an ore worth " + oreWorth + "!");
            player.setMoney(oreWorth);
            System.out.println("Current CSCs: " + player.getMoney());
        }
        else {
            System.out.println("Your answer is incorrect!");
        }
    }

    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<Item>();
        User player = new User("Player", inventory, 100, 0, 0);
        Mine mine = new Mine(player);
        mine.start();
    }
}
