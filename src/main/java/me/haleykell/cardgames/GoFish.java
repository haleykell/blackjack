package me.haleykell.cardgames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GoFish extends CardGame {
    private ArrayList<Integer> computerPile = new ArrayList<>();
    private ArrayList<Integer> playerPile = new ArrayList<>();

    public GoFish(ArrayList<Integer> deck, Scanner input, int size) {
        super(deck, input, size);
    }

    @Override
    public void playGame() {
        Collections.shuffle(deck);
        dealHands();
        showCards(player, true);

        // Play the game
        while (computerPile.size() + playerPile.size() < 49 || !deck.isEmpty()) {
            System.out.println();
            Collections.shuffle(deck);
            // Let the player play first
            // show the player their cards
            if (!player.isEmpty()) {
                System.out.println("What card do you want?");
                int card = input.nextInt();

                playOneTurn(card, player, computer, playerPile);
            } else {
                player.add(deck.get(0));
                deck.remove(0);
            }

            showGameState();

            // Now it is the computer's turn
            // Randomly choose a card
            if (!computer.isEmpty()) {
                int card = computer.get((int) (Math.random() * computer.size()));
                System.out.println("Do you have any " + card + "'s ?");

                // Play one turn with the computer doing the choosing
                playOneTurn(card, computer, player, computerPile);
            } else if (!deck.isEmpty()) {
                // Let the computer draw from the deck
                computer.add(deck.get(0));
                deck.remove(0);
            }

            System.out.println();
            showGameState();
            if (deck.size() == 0) break;
        }

        checkWin();
    }

    @Override
    protected void showGameState() {
        System.out.println("Here are your cards.");
        showCards(player, true);
        if (playerPile.size() != 0) {
            System.out.println("Here is your pile.");
            showCards(playerPile, false);
        }
        if (computerPile.size() != 0) {
            System.out.println("Here is my pile.");
            showCards(computerPile, false);
        }
    }

    @Override
    protected void checkWin() {
        // Determine the winner and tell the user--remember ties are possible
        if (playerPile.size() > computerPile.size()) System.out.print("You won!");
        else if (playerPile.size() < computerPile.size()) System.out.println("You lost.");
        else System.out.println("You tied.");
    }

    private void playOneTurn(int card, ArrayList<Integer> chooser, ArrayList<Integer> chosen,
                             ArrayList<Integer> chooserPile) {
        if (chosen.contains(card)) {
            // Chosen gives cards to Chooser
            transferCards(card, chooser, chosen);
            // If there is a set of four matching cards, put them up on the table
            checkFourCards(card, chooser, chooserPile);
        } else {
            System.out.println("Go fish!");

            // Draw a card by removing it from the deck and putting it in the chooser's hand
            int card2 = 0;
            if (!deck.isEmpty()) {
                chooser.add(deck.get(0));
                card2 = deck.get(0);
                deck.remove(0);
            }

            // If there is a set of four matching cards, put them on the table
            checkFourCards(card2, chooser, chooserPile);
        }
    }

    private void checkFourCards(int card, ArrayList<Integer> chooser, ArrayList<Integer> chooserPile) {
        int count = 0;
        for (Integer chooserCard : chooser) {
            if (chooserCard == card) count++;
        }
        if (count == 4) transferCards(card, chooserPile, chooser);
    }

    private void transferCards(int card, ArrayList<Integer> destination, ArrayList<Integer> source) {
        while (source.contains(card)) {
            destination.add(card);
            source.remove(new Integer(card));
        }
    }
}