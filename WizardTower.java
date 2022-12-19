import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException; // Import statements

public class WizardTower {

    private User person;
    Scanner input = new Scanner(System.in); // Creates a scanner for typing inputs into the command line

    /**
     * Creates a WizardTower that has a User
     */
    public WizardTower(User p) {
        person = p;
    }

    /**
     * Begins user interaction with the WizardTower which allows the player to pick the minigame they want to play
     */
    public void start() {
        while(true) {
            System.out.println("Welcome to the Wizard Tower!\nThere are several minigames to play here including -\n1. Tic Tac Toe\n2. Leave\nWhat would you like to play?");
            int gameNumber = 0;
            while(gameNumber < 1 || gameNumber > 2) {
                gameNumber = checker();
            }
            if(gameNumber == 1) {
                System.out.println("Tic Tac Toe Selected!");
                TicTacToe tacGame = new TicTacToe(checkWager());
                person.setMoney(tacGame.start());
                System.out.println("\nCurrent CSCs: " + person.getMoney());
            }
            else if(gameNumber == 2) {
                break; // Leaves the WizardTower
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
     * Checks to make sure the user's wager is within their monetary range
     * @return an integer of the user's wager
     */
    public int checkWager() {
        System.out.println("How much would you like to wager?");
        System.out.println("Avaliable CSCs: " + person.getMoney());
        int wager = -1;
        while(wager < 0 || wager > person.getMoney())
        {
            wager = checker();
            if(wager > person.getMoney()) { // If the user doesn't have enough money they need to change their wager
                System.out.println("Please enter a smaller wager!");
            }
        }
        return wager;
    }

    /**
     * Checks to make sure the user's input is a integer (not a string, boolean, etc)
     * @return an integer based on the user's valid or invalid response
     */
    public int secondChecker() {
        try {
            int number = input.nextInt();
            return number;
        }
        catch(InputMismatchException e) {
            System.out.println("Please enter a different input!");
            input.next();
            return -1;
        }
    }

    /**
     * Main method for testing
     * @param args[] An empty array of Strings
     */
    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<Item>();
        User player = new User("Sydney", inventory, 10, 10, 100);
        WizardTower tower = new WizardTower(player);
        tower.start();
    }
}
