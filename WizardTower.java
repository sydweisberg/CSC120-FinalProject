import java.util.ArrayList;
import java.util.Scanner;

public class WizardTower {

    private User person;
    Scanner input = new Scanner(System.in);

    public WizardTower(User p) {
        person = p;
    }

    public void start() {

        System.out.println("Welcome to the Wizard Tower!\nThere are several minigames to play here including -\n1. Tic Tac Toe\nWhat would you like to play?\nType the game's number to play or back to exit the tower.");
        while(true) {
            String gameNumber = input.next();
            if(gameNumber.toLowerCase().equals("back")) {
                break;
            }
            else if(gameNumber.equals("1") || gameNumber.toLowerCase().equals("tic tac toe") || gameNumber.toLowerCase().equals("tictactoe")) {
                System.out.println("Tic Tac Toe Selected!");
                int wager = checkWager();
                TicTacToe tacGame = new TicTacToe(wager); // FIX THIS
                person.setMoney(tacGame.start());
                System.out.println("\nNew Money: " + person.getMoney());
            }
            else {
                System.out.println("Please enter a valid input");
            }
        }
        input.close();
    }

    public int checkWager() {
        System.out.println("How much would you like to wager?");
        Boolean validity = true;
        int wager = input.nextInt();
        while(validity) {
            if(wager <= person.getMoney()) {
                validity = false;
                return wager;
            }
            else {
                System.out.println("Please enter a different wager!");
                wager = input.nextInt();
            }
        }
        return wager;
    }

    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<Item>();
        User player = new User("Sydney", inventory, 10, 10, 100);
        WizardTower tower = new WizardTower(player);
        tower.start();
    }
}
