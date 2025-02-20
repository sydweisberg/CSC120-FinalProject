import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException; // Import statements

public class TicTacToe {
    
    private int wager;

    static Scanner input = new Scanner(System.in); // Creates a scanner for typing inputs into the command line
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m"; // Colors to use in the game

    /**
     * Creates a TicTacToe game that has a wager
     */
    public TicTacToe(int w) {
        wager = w;
    }

    /**
     * Starts an interactive game of Tic Tac Toe
     * @return an integer of the user's wager based on if they won or lost the game
     */
    public int start() {
        System.out.println(ANSI_RED + " 0 | 1 | 2 ");
        System.out.println("-----------");
        System.out.println(" 3 | 4 | 5 "); // Initial gameboard
        System.out.println("-----------");
        System.out.println(" 6 | 7 | 8 " + ANSI_RESET);
        Boolean[] squareInUse = {false, false, false, false, false, false, false, false, false};
        Boolean[] opponentSpots = {false, false, false, false, false, false, false, false, false};
        Boolean[] userSpots = {false, false, false, false, false, false, false, false, false};
        boolean gameOver = false;
        Random r = new Random();
        while(gameOver == false) {
            System.out.println("");
            System.out.println("Please Enter Your Number");
            int space = -2;
            while(space > 8 || space < 0) {
                space = checker();
                if(space > 8 || space < 0) {
                    System.out.println("Please enter a different number!");
                }
            }
                for(int i = 0; i < squareInUse.length; i++) {
                    if(squareInUse[space] == true) {
                        System.out.println("Please Enter A Different Number");
                        space = -2;
                        while(space > 8 || space < 0) {
                            space = checker();
                            if(space > 8 || space < 0) {
                                System.out.println("Please enter a different number!");
                            }
                        }
                        i = 0;
                    }
                }
            squareInUse[space] = true;
            userSpots[space] = true;
            gameBoard(opponentSpots, userSpots);
            if(gameWon(userSpots, true).equals("You Win!")) {
                gameOver = true;
                String yw = "You Win!";
                for (int i = 0; i < yw.length(); i++) { // Fun for loop to display results in multiple colors
                    if(i % 2 == 1) {
                        System.out.print(ANSI_CYAN + yw.charAt(i));
                    }
                    else {
                        System.out.print(ANSI_YELLOW + yw.charAt(i));
                    }
                }
                System.out.print(ANSI_RESET);
                return wager;
            }
            boolean checker = false;
            while(checker == false) {
                int value = r.nextInt(squareInUse.length);
                if(squareInUse[value] == false) {
                    squareInUse[value] = true;
                    opponentSpots[value] = true;
                    checker = true;
                }
            }

            System.out.println("\nOpponent's Turn");
            gameBoard(opponentSpots, userSpots);
            if(gameWon(opponentSpots, false).equals("You Lose!")) {
                gameOver = true;
                String ow = "Opponent Wins!";
                for (int i = 0; i < ow.length(); i++) { // Fun for loop to display results in multiple colors
                    if(i % 2 == 1) {
                        System.out.print(ANSI_CYAN + ow.charAt(i));
                    }
                    else {
                        System.out.print(ANSI_YELLOW + ow.charAt(i));
                    }
                }
                System.out.print(ANSI_RESET);
                return wager * (-1);
            }
        }
        if(gameOver == false) {
            System.out.println("It's a tie!");
            return 0;
        }
        return 0;
    }

    /**
     * Displays the current gameboard based off of the squares already in use
     * @param Boolean[] a list of the opponent's spaces
     * @param Boolean[] a list of the user's spaces
     */
    public static void gameBoard(Boolean[] opponentSpots, Boolean[] userSpots) {
        System.out.println("");
        String[] UI = {" ", " | ", " | ", " ", " | ", " | ", " ", " | ", " | "};
        for(int i = 0; i < UI.length; i++) {
            System.out.print(UI[i]);
            if(opponentSpots[i] == true) {
                System.out.print(ANSI_YELLOW + "O" + ANSI_RESET);
            }
            else if(userSpots[i] == true) {
                System.out.print(ANSI_CYAN + "X" + ANSI_RESET);
            }
            else {
                System.out.print(" ");
            }
            if(i == 2 || i == 5) {
                System.out.println("");
                System.out.println("-----------");
            }
        }  
        System.out.println(""); // Finishes printing out the gameboard
    }

    /**
     * Checks to see if the user won or lost TicTacToe
     * @return a string saying if the user won or lost
     */
    public static String gameWon(Boolean[] os, Boolean x) {
        boolean win = false;
        if(os[0] == true && os[1] == true && os[2] == true)
        {
            win = true;
        }
        else if(os[3] == true && os[4] == true && os[5] == true)
        {
            win = true;
        }
        else if(os[6] == true && os[7] == true && os[8] == true)
        {
            win = true;
        }
        else if(os[0] == true && os[3] == true && os[6] == true)
        {
            win = true;
        }
        else if(os[1] == true && os[4] == true && os[7] == true)
        {
            win = true;
        }
        else if(os[2] == true && os[5] == true && os[8] == true)
        {
            win = true;
        }
        else if(os[0] == true && os[4] == true && os[8] == true)
        {
            win = true;
        }
        else if(os[2] == true && os[4] == true && os[6] == true)
        {
            win = true;
        }
        if(win == true && x == true) {
            return("You Win!");
        }
        else if(win == true && x == false) {
            return("You Lose!");
        }
        else {
        return("Nothing Yet");
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
            return -1;
        }
    }

    /**
     * Main method for testing
     * @param args[] An empty array of Strings
     */
    public static void main(String[] args) {
        TicTacToe tictacky = new TicTacToe(100);
        tictacky.start();
    }
}
