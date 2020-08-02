package me.haleykell.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

    private static int STARTING_HAND_SIZE = 2;
    private static int DEALER_MIN = 17;
    private static int MAX = 21;
    private static int PLAYER_WIN = 1;
    private static int DEALER_WIN = 2;

    private ArrayList<Integer> deck;
    private Scanner input;
    private ArrayList<Integer> dealer;
    private ArrayList<Integer> player;
    private int dealerPoints = 0;
    private int playerPoints = 0;

    public Blackjack(ArrayList<Integer> deck, Scanner input) {
        this.deck = deck;
        this.input = input;
    }

    public void playOneGame() {
        int winner;

        while (deck.size() > 4) {
            System.out.println("New hand.");

            this.dealer = new ArrayList<>();
            this.player = new ArrayList<>();

            winner = playOneTurn();

            if (winner == PLAYER_WIN) ++playerPoints;
            else if (winner == DEALER_WIN) ++dealerPoints;
            showGameState();
        }

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
        showCards(player);
        System.out.println("Sum: " + sumCards(player));

        // Shows dealer's top card
        System.out.println("Dealer's top card:");
        showCardsDealer(dealer);

        System.out.println("Would you like to hit?");
        String response = input.next();

        while (response.equalsIgnoreCase("yes")) {
            hit(player);

            System.out.println("Your cards:");
            showCards(player);

            System.out.println("Sum: " + sumCards(player));

            if (sumCards(player) > MAX) {
                System.out.println("Dealer gets a point.\n");
                return 2;
            }

            System.out.println("Would you like to hit?");
            response = input.next();
        }

        System.out.println("Dealer's cards:");
        showCards(dealer);
        int sum = sumCards(dealer);

        while (sum <= DEALER_MIN) {
            if (!deck.isEmpty()) {
                dealer.add(deck.get(0));
                deck.remove(0);
            }
            else break;

            showCards(dealer);
            sum = sumCards(dealer);
        }
        System.out.println("Dealer's sum: " + sum);

        if (sumCards(player) > MAX) {
            System.out.println("Dealer gets a point.\n");
            return DEALER_WIN;
        }
        if (sumCards(dealer) > MAX) {
            System.out.println("You get a point.\n");
            return PLAYER_WIN;
        }
        if (sumCards(player) > sumCards(dealer)) {
            System.out.println("You get a point.\n");
            return PLAYER_WIN;
        }
        if (sumCards(dealer) > sumCards(player)) {
            System.out.println("Dealer gets a point.\n");
            return DEALER_WIN;
        }
        if (sumCards(dealer) == sumCards(player)) {
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

    private void showGameState() {
        // Show number of points for the player and the dealer
        System.out.println("The dealer has " + dealerPoints + " points.");
        System.out.println("You have " + playerPoints + " points.\n");
    }

    private void showCards(ArrayList<Integer> cards) {
        // Sort the cards to make it easier for the user to know what they have
        Collections.sort(cards);

        cards.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    private void showCardsDealer(ArrayList<Integer> cards) {
        // Sort the cards to make it easier for the user to know what they have
        Collections.sort(cards);
        System.out.println(cards.get(0));
    }

    private void dealHands() {
        // Deal the cards
        for (int index = 0; index < STARTING_HAND_SIZE; ++index) {
            dealer.add(deck.get(index));
            deck.remove(index);
        }

        for (int index = 0; index < STARTING_HAND_SIZE; ++index) {
            player.add(deck.get(index));
            deck.remove(index);
        }
    }

    private void hit(ArrayList<Integer> cards) {
        cards.add(deck.get(0));
        deck.remove(0);
    }
}
