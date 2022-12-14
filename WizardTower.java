import java.util.ArrayList;
import java.util.Scanner;

public class WizardTower {

    private User person;
    Scanner input = new Scanner(System.in);

    public WizardTower(User p) {
        person = p;
    }

    public void start() {
        while(true) {
            System.out.println("Welcome to the Wizard Tower!\nThere are several minigames to play here including -\n1. Tic Tac Toe\n2. Leave\nWhat would you like to play?");
            int gameNumber = input.nextInt();
            if(gameNumber == 1) {
                System.out.println("Tic Tac Toe Selected!");
                TicTacToe tacGame = new TicTacToe(checkWager());
                person.setMoney(tacGame.start());
                System.out.println("\nCurrent CSCs: " + person.getMoney());
            }
            else if(gameNumber == 2) {
                break;
            }
            else {
                System.out.println("Please enter a valid input");
            }
        }
    }

    public int checkWager() {
        System.out.println("How much would you like to wager?");
        System.out.println(person.getMoney());
        int wager = input.nextInt();
        if(!(wager <= person.getMoney())) {
            System.out.println("Please enter a different wager!");
            checkWager();
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
