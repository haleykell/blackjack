package me.haleykell;

import me.haleykell.boardgames.TicTacToe;
import me.haleykell.cardgames.Blackjack;
import me.haleykell.cardgames.CardGame;
import me.haleykell.cardgames.GoFish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Integer> deck = CardGame.createDeck();
        Collections.shuffle(deck);

        System.out.println("Choose a game:");
        System.out.println("[1] Blackjack");
        System.out.println("[2] Tic Tac Toe");
        System.out.println("[3] Go Fish");
        int game = input.nextInt();
        input.nextLine();

        switch (game) {
            case 1:
                Blackjack blackjack = new Blackjack(deck, input, 2);
                blackjack.playGame();
                break;
            case 2:
                TicTacToe ticTacToe = new TicTacToe(input);
                ticTacToe.playGame();
                break;
            case 3:
                GoFish gofish = new GoFish(deck, input, 7);
                gofish.playGame();
                break;
        }

        input.close();
    }


}