import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class utils {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputDecision() {
        // Decide whether to start another round
        System.out.println("If you want to play type Yes \nIf you want to leave with the money type No");
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean validDecisionInput(String string){
        // Return True if String is valid format
        if(string.equals("Yes") || string.equals("yes") || string.equals("No") || string.equals("no")){
            return true;
        }
        else{
            return false;
        }

    }

    public static String inputBet(){
        // Method used to get user's bet amount
        System.out.println("\nPlease make a bet: ");
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean validBetInput(String string, Player player){
        // Return True if String is valid format
        if(string.matches("[0-9]+")){
            Integer input = Integer.valueOf(string);
            if(player.getBalance() >= input){
                return true;
            }
        }

        return false;

    }

    public static String inputCardDecision(){
        // Method used to decide whether to stay or hit
        System.out.println("\nDo you want to stay or hit?");
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean validCardDecisionInput(String string){
        // Return True if String is valid format
        if(string.equals("Hit") || string.equals("hit") || string.equals("Stay") || string.equals("stay")){
            return true;
        }
        else{
            return false;
        }
    }

    public static String inputName() throws IllegalArgumentException {
        System.out.println("Please enter your name with up to 8 characters: ");
        String name = scanner.nextLine();
        Integer nameLength = name.length();
        if (nameLength > 8){
            throw new IllegalArgumentException("Name length is more than 8 characters long");
        }
        else {
            return name;
        }

    }

    public static void printTable(String player, String dealer, ArrayList<Card> playerCards, ArrayList<Card> dealerCards, int playerPoints, int dealerPoints){
        // Prints "the blackjack table", meaning the names of player & bank, followed by their cards and points
        int max = Math.max(playerCards.size(), dealerCards.size());
        ArrayList<String> pCardsString = new ArrayList<>();
        ArrayList<String> dCardsString = new ArrayList<>();

        // Convert list of cards to list of strings, with empty strings at the end of list
        // if dealer & user don't have same amount of cards so that table is even
        for(int i = 0; i<max; i++){
            if(i<playerCards.size()){
                pCardsString.add(playerCards.get(i).toString());
            }
            else{pCardsString.add("  ");}
        }
        for(int i = 0; i<max; i++){
            if(i<dealerCards.size()){
                dCardsString.add(dealerCards.get(i).toString());
            }
            else{dCardsString.add("  ");}
        }

        // If dealer only has one card: Second one is hidden, not empty
        if(Objects.equals(dCardsString.get(1), "  ")){
            dCardsString.set(1, "Hidden");
        }

        // Print the table
        System.out.format("%7s | %8s | %8s\n ---------------------------------\n"," ", player, dealer);
        for(int i=0; i<max; i++){
            System.out.format("%7s | %8s | %8s\n","Cards", pCardsString.get(i), dCardsString.get(i));
        }
        System.out.format("---------------------------------\n%7s | %8d | %8d\n ", "Points", playerPoints, dealerPoints);

        // Wait for .8 seconds
        try {
            Thread.sleep(800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printAndWait(String message){
        // Used to print a message and wait .8 seconds, so that not all info is printed at once
        System.out.println(message);
        try {
            Thread.sleep(800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer cardValues(ArrayList<Card> cards){
        /*
         * returns optimal value of list of cards
         * Deciding if aces are wortho 1 or 11 is handled here
         */
        Integer sum = 0;
        int numAces = 0;
        ArrayList<Card> cardsCopy = new ArrayList<Card>(cards);
        Collections.sort(cardsCopy);

        for (Card card : cardsCopy) {
            if (card.getRank() != Rank.Ace) {
                Integer value = card.getValue();
                sum += value;
            } else {
                numAces += 1;
            }
        }

        // Initially count all aces as value 1
        sum += numAces;

        // Increase value of one ace to 11 if possible
        // Having two aces with value 11 is always > 21, so at most one is 11
        if(numAces > 0 && sum+10 <= 21){
            return sum+10;
        }
        return sum;
    }

    public static ArrayList<Card> initialCards(Deck deck){
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card1 = deck.draw();
        Card card2 = deck.draw();
        cards.add(card1);
        cards.add(card2);
        return cards;
    }

}
