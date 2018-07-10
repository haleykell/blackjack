import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver
{

    public static int STARTING_HAND_SIZE = 2;
    public static int DEALER_MIN = 17;
    public static int MAX = 21;
    public static int PLAYER_WIN = 1;
    public static int DEALER_WIN = 2;
    public static int DECK_SIZE = 50;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        ArrayList<Integer> deck = createDeck();
        Collections.shuffle(deck);

        playOneGame(deck, input);

        input.close();
    }

    public static ArrayList<Integer> createDeck()
    {
        //TODO: Create a deck of cards
        ArrayList<Integer> pool = new ArrayList<Integer>();
        int index = 0;
        while (index < DECK_SIZE)
        {
            pool.add(index % 10 + 1);
            ++index;
        }
        return pool;
    }

    public static void playOneGame(ArrayList<Integer> deck, Scanner input)
    {
        ArrayList<Integer> dealer = new ArrayList<Integer>();
        ArrayList<Integer> player = new ArrayList<Integer>();
        int playerPoints = 0;
        int dealerPoints = 0;
        int winner;

        while (deck.size() > 4)
        {
            System.out.println("New hand.");
            dealer = new ArrayList<Integer>();
            player = new ArrayList<Integer>();
            winner = playOneTurn(deck, dealer, player, input);
            if (winner == PLAYER_WIN)
            {
                ++playerPoints;
            }
            else if (winner == DEALER_WIN)
            {
                ++dealerPoints;
            }
            showGameState(dealerPoints, playerPoints);
        }

        // Calculate winner
        if (playerPoints > dealerPoints)
        {
            System.out.println("You won!");
        }
        else if (playerPoints < dealerPoints)
        {
            System.out.println("You lost.");
        }
        else
        {
            System.out.println("You tied.");
        }
    }

    public static int playOneTurn(ArrayList<Integer> deck, ArrayList<Integer> dealer, ArrayList<Integer> player, Scanner input)
    {
        // Deal hands
        dealHands(deck, dealer, player);

        // Shows player their cards
        System.out.println("Your cards:");
        showCardsPlayer(player);
        System.out.println("Sum: " + sumCards(player));

        // Shows dealer's top card
        System.out.println("Dealer's top card:");
        showCardsDealer(dealer);

        System.out.println("Would you like to hit?");
        String response = input.nextLine();

        while (response.equalsIgnoreCase("yes"))
        {
            hit(deck, player);
            System.out.println("Your cards:");
            showCardsPlayer(player);
            System.out.println("Sum: " + sumCards(player));
            if (sumCards(player) > MAX)
            {
                System.out.println("Dealer gets a point.");
                System.out.println();
                return 2;
            }
            System.out.println("Would you like to hit?");
            response = input.nextLine();
        }

        System.out.println("Dealer's cards:");
        showCardsPlayer(dealer);
        int sum = sumCards(dealer);
        while (sum <= DEALER_MIN)
        {
            if (!deck.isEmpty())
            {
                dealer.add(deck.get(0));
                deck.remove(0);
            }
            else
            {
                break;
            }
            showCardsPlayer(dealer);
            sum = sumCards(dealer);
        }
        System.out.println("Dealer's sum: " + sum);

        if (sumCards(player) > MAX)
        {
            System.out.println("Dealer gets a point.");
            System.out.println();
            return DEALER_WIN;
        }
        if (sumCards(dealer) > MAX)
        {
            System.out.println("You get a point.");
            System.out.println();
            return PLAYER_WIN;
        }
        if (sumCards(player) > sumCards(dealer))
        {
            System.out.println("You get a point.");
            System.out.println();
            return PLAYER_WIN;
        }
        if (sumCards(dealer) > sumCards(player))
        {
            System.out.println("Dealer gets a point.");
            System.out.println();
            return DEALER_WIN;
        }
        if (sumCards(dealer) == sumCards(player))
        {
            System.out.println("No points.");
            System.out.println();
            return 0;
        }

        System.out.println();
        return 0;
    }

    public static int sumCards(ArrayList<Integer> cards)
    {
        int sum = 0;
        for (int index = 0; index < cards.size(); ++index)
        {
            sum = sum + cards.get(index);
        }

        return sum;
    }

    public static void showGameState(int dealerPoints, int playerPoints)
    {
        // Show number of points for the player and the dealer
        System.out.println("The dealer has " + dealerPoints + " points.");
        System.out.println("You have " + playerPoints + " points.");
        System.out.println();
    }

    public static void showCardsPlayer(ArrayList<Integer> cards)
    {
        // TODO: Sort the cards to make it easier for the user to know what they have
        Collections.sort(cards);
        for (Integer i: cards)
        {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void showCardsDealer(ArrayList<Integer> cards)
    {
        // TODO: Sort the cards to make it easier for the user to know what they have
        Collections.sort(cards);
        System.out.println(cards.get(0));
    }

    public static void dealHands(ArrayList<Integer> deck, ArrayList<Integer> hand1, ArrayList<Integer> hand2)
    {
        //TODO: Deal the cards
        for (int index = 0; index < STARTING_HAND_SIZE; ++index)
        {
            hand1.add(deck.get(index));
            deck.remove(index);
        }
        for (int index = 0; index < STARTING_HAND_SIZE; ++index)
        {
            hand2.add(deck.get(index));
            deck.remove(index);
        }
    }

    public static void hit(ArrayList<Integer> deck, ArrayList<Integer> cards)
    {
        cards.add(deck.get(0));
        deck.remove(0);
    }

}