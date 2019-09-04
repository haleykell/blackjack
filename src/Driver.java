import games.Blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver
{

    private static int DECK_SIZE = 50;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        ArrayList<Integer> deck = createDeck();
        Collections.shuffle(deck);

        Blackjack blackjack = new Blackjack(deck, input);

        blackjack.playOneGame();

        input.close();
    }

    private static ArrayList<Integer> createDeck()
    {
        // Create a deck of cards
        ArrayList<Integer> pool = new ArrayList<>();
        int index = 0;
        while (index < DECK_SIZE)
        {
            pool.add(index % 10 + 1);
            ++index;
        }
        return pool;
    }



}