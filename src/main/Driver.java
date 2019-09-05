package main;

import main.cardgames.Blackjack;
import main.boardgames.TicTacToe;
import main.cardgames.CardGame;
import main.cardgames.GoFish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        ArrayList<Integer> deck = CardGame.createDeck();
        Collections.shuffle(deck);

        System.out.println("Choose a game:");
        System.out.println("[1] Blackjack");
        System.out.println("[2] Tic Tac Toe");
        System.out.println("[3] Go Fish");
        System.out.println("[4] Basic Card Game");
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
            case 4:
                CardGame basic = new CardGame(deck, input, 0);
                basic.playGame();
                break;
        }

        input.close();
    }



}