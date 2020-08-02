package me.haleykell.cardgames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack extends CardGame {

    private static int DEALER_MIN = 17;
    private static int MAX = 21;
    private static int PLAYER_WIN = 1;
    private static int DEALER_WIN = 2;

    private int dealerPoints = 0;
    private int playerPoints = 0;


    public Blackjack(ArrayList<Integer> deck, Scanner input, int size) {
        super(deck, input, size);
    }

    @Override
    public void playGame() {
        int winner;

        while (deck.size() > 4) {
            System.out.println("New hand.");
            Collections.shuffle(deck);
            this.computer = new ArrayList<>();
            this.player = new ArrayList<>();

            winner = playOneTurn();

            if (winner == PLAYER_WIN) ++playerPoints;
            else if (winner == DEALER_WIN) ++dealerPoints;

            showGameState();
        }

        checkWin();
    }

    @Override
    protected void checkWin() {
        // Calculate winner
        if (playerPoints > dealerPoints) System.out.println("You won!");
        else if (playerPoints < dealerPoints) System.out.println("You lost.");
        else System.out.println("You tied.");
    }

    private int playOneTurn() {
        // Deal hands
        dealHands();

        // Shows player their cards
        System.out.println("Your cards:");
        showCards(player, false);
        System.out.println("Sum: " + sumCards(player));

        // Shows dealer's top card
        System.out.println("Dealer's top card:");
        showCardsDealer(computer);

        System.out.println("Would you like to hit?");
        String response = input.nextLine();

        while (response.equalsIgnoreCase("yes")) {
            hit(player);

            System.out.println("Your cards:");
            showCards(player, false);
            System.out.println("Sum: " + sumCards(player));

            if (sumCards(player) > MAX) {
                System.out.println("Dealer gets a point.\n");
                return 2;
            }

            System.out.println("Would you like to hit?");
            response = input.nextLine();
        }

        System.out.println("Dealer's cards:");

        showCards(computer, false);
        int sum = sumCards(computer);

        while (sum <= DEALER_MIN) {
            if (!deck.isEmpty()) {
                computer.add(deck.get(0));
                deck.remove(0);
            } else break;

            showCards(computer, false);
            sum = sumCards(computer);
        }

        System.out.println("Dealer's sum: " + sum);

        if (sumCards(player) > MAX) {
            System.out.println("Dealer gets a point.\n");
            return DEALER_WIN;
        }

        if (sumCards(computer) > MAX) {
            System.out.println("You get a point.\n");
            return PLAYER_WIN;
        }

        if (sumCards(player) > sumCards(computer)) {
            System.out.println("You get a point.\n");
            return PLAYER_WIN;
        }

        if (sumCards(computer) > sumCards(player)) {
            System.out.println("Dealer gets a point.\n");
            return DEALER_WIN;
        }

        if (sumCards(computer) == sumCards(player)) {
            System.out.println("No points.\n");
            return 0;
        }

        System.out.println();
        return 0;
    }


    private int sumCards(ArrayList<Integer> cards) {
        int sum = 0;

        for (Integer card : cards) {
            sum = sum + card;
        }

        return sum;
    }

    @Override
    protected void showGameState() {
        // Show number of points for the player and the dealer
        System.out.println("The dealer has " + dealerPoints + " points.");
        System.out.println("You have " + playerPoints + " points.");
        System.out.println();
    }

    private void showCardsDealer(ArrayList<Integer> cards) {
        System.out.println(cards.get(0));
    }

    private void hit(ArrayList<Integer> cards) {
        cards.add(deck.get(0));
        deck.remove(0);
    }
}