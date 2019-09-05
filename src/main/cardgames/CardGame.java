package main.cardgames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CardGame implements Game {

    protected ArrayList<Integer> deck;
    protected Scanner input;
    protected ArrayList<Integer> computer = new ArrayList<>();
    protected ArrayList<Integer> player = new ArrayList<>();
    private static int DECK_SIZE = 50;
    private int startingHandSize;

    public CardGame(ArrayList<Integer> deck, Scanner input, int size) {
        this.deck = deck;
        this.input = input;
        this.startingHandSize = size;
    }

    public static ArrayList<Integer> createDeck() {
        // Create a deck of cards
        ArrayList<Integer> pool = new ArrayList<>();
        int index = 0;
        while (index < DECK_SIZE) {
            pool.add(index % 10 + 1);
            ++index;
        }
        return pool;
    }

    public void playGame() {
        int count = 0;
        while (deck.size() > 0) {
            Collections.shuffle(deck);
            player.add(deck.get(count));
            deck.remove(count);
            count++;
            if (count >= deck.size()) break;
            computer.add(deck.get(count));
            deck.remove(count);
            count++;
        }
        checkWin();
    }

    protected void dealHands() {
        // Deal the cards
        for (int index = 0; index < startingHandSize; ++index) {
            computer.add(deck.get(index));
            deck.remove(index);
        }

        for (int index = 0; index < startingHandSize; ++index) {
            player.add(deck.get(index));
            deck.remove(index);
        }
    }

    protected void checkWin() {
        // Determine the winner and tell the user--remember ties are possible
        int sumPlayer = 0;
        int sumComputer = 0;
        for (Integer card : player) {
            sumPlayer += card;
        }
        for (Integer card : computer) {
            sumComputer += card;
        }
        if (sumPlayer > sumComputer) System.out.print("You won!");
        else if (sumPlayer < sumComputer) System.out.println("You lost.");
        else System.out.println("You tied.");
    }

    protected void showGameState() {
        for (Integer card : player) {
            System.out.print(card);
        }
        for (Integer card : computer) {
            System.out.println(card);
        }
    }

    protected void showCards(ArrayList<Integer> cards, boolean sort) {
        if (sort) Collections.sort(cards);
        for (Integer i: cards) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
