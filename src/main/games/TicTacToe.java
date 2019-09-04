package main.games;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private char playerA = 'X';
    private char comp = 'O';
    private char[] board = new char[9];
    private Scanner input;

    public TicTacToe(Scanner input) {
        this.input = input;

        for (int i = 0; i < 9; i++) {
            board[i] = '-';
        }
    }

    // playGame allows both players to take turns until a winner is detected or the board is full
    // playGame then returns the symbol of the winner
    // and '-' if there is a tie
    public void playGame() {
        printBoard();

        while (!isFull() && detectWinner() == '-') {

            // One turn per player
            System.out.println("Your turn:");
            takeTurn(playerA);

            // Check if playerA's last turn resulted in a win or full board
            if (isFull() || detectWinner() != '-') break;

            System.out.println("Computer's turn.\n");
            takeCompTurn(comp);
        }

        if (detectWinner() == 'X') System.out.println("You win!");
        else if (detectWinner() == 'O') System.out.println("You lose!");
        else System.out.println("Tie game!");
    }

    private void takeCompTurn(char player) {
        if (isFull()) return;
        Random rand = new Random();
        int pos;

        do {
            pos = rand.nextInt(8);
        } while (board[pos] != '-');

        board[pos] = player;
    }

    // takeTurn allows a player to add a piece to the board
    private void takeTurn(char player) {
        // Print current game board pre-turn
        printBoard();

        System.out.println("Choose a position based on the number system printed at the beginning of the game.");
        int pos = input.nextInt();

        // addPiece returns false until the player chooses a position that is not already taken
        if (isFull()) return;
        addPiece(player, pos);
    }

    // Prints current game board
    private void printBoard() {
        System.out.println(board[0] + " " + board[1] + " " + board[2]);
        System.out.println(board[3] + " " + board[4] + " " + board[5]);
        System.out.println(board[6] + " " + board[7] + " " + board[8]);
    }

    // Checks if the board is full
    // If so, game is over
    private boolean isFull() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == '-') return false;
        }
        return true;
    }

    /* Detects winner
     * returns player who won
     * or returns '-' in the event of a tie
     */
    private char detectWinner() {
        /* Win combinations
         * 0, 1, 2
         * 3, 4, 5
         * 6, 7, 8
         * 0, 3, 6
         * 1, 4, 7
         * 2, 5, 8
         * 0, 4, 8
         * 2, 4, 6
         */

        if (board[0] == board[1] && board[1] == board[2]) return board[0];
        else if (board[3] == board[4] && board[4] == board[5]) return board[3];
        else if (board[6] == board[7] && board[7] == board[8]) return board[6];
        else if (board[0] == board[3] && board[3] == board[6]) return board[0];
        else if (board[1] == board[4] && board[4] == board[7]) return board[1];
        else if (board[2] == board[5] && board[5] == board[8]) return board[2];
        else if (board[0] == board[4] && board[4] == board[8]) return board[0];
        else if (board[2] == board[4] && board[4] == board[6]) return board[2];
        else return '-';
    }

    // Adds a piece to the board as long as the position isn't taken
    private void addPiece(char player, int position) {
        if (position > 8 || position < 0) {
            System.out.println("Choose a number between 0 and 8.");
            position = input.nextInt();
        }

        while (board[position] != '-') {
            System.out.println("That position is taken! Please choose another.");
            position = input.nextInt();
        }

        board[position] = player;
    }
}
