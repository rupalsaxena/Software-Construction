import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class utils {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputDecision() {
        System.out.println("If you want to play type Yes \nIf you want to leave with the money type No");
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean validDecisionInput(String string){
        if(string.equals("Yes") || string.equals("yes") || string.equals("No") || string.equals("no")){
            return true;
        }
        else{
            return false;
        }

    }

    public static String inputBet(){
        System.out.println("\nPlease make a bet: ");
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean validBetInput(String string, Player player){
        if(string.matches("[0-9]+")){
            Integer input = Integer.valueOf(string);
            if(player.getBalance() >= input){
                return true;
            }
        }

        return false;

    }

    public static String inputCardDecision(){
        System.out.println("\nDo you want to stay or hit?");
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean validCardDecisionInput(String string){
        if(string.equals("Hit") || string.equals("hit") || string.equals("Stay") || string.equals("stay")){
            return true;
        }
        else{
            return false;
        }
    }

    public static String inputName(){
        System.out.println("Please enter your name with less than 8 characters: ");
        return scanner.nextLine();
    }

    public static void printTable(String player, String dealer, ArrayList<Card> playerCards, ArrayList<Card> dealerCards, int playerPoints, int dealerPoints){
        int max = Math.max(playerCards.size(), dealerCards.size());
        ArrayList<String> pCardsString = new ArrayList<>();
        ArrayList<String> dCardsString = new ArrayList<>();
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

        if(Objects.equals(dCardsString.get(1), "  ")){
            dCardsString.set(1, "Hidden");
        }
        System.out.format("%7s | %8s | %8s\n ---------------------------------\n"," ", player, dealer);
        for(int i=0; i<max; i++){
            System.out.format("%7s | %8s | %8s\n","Cards", pCardsString.get(i), dCardsString.get(i));
        }
        System.out.format("---------------------------------\n%7s | %8d | %8d\n ", "Points", playerPoints, dealerPoints);
        try {
            Thread.sleep(800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printAndWait(String message){
        System.out.println(message);
        try {
            Thread.sleep(800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer cardValues(ArrayList<Card> cards){
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

        // Count all aces as value 1
        sum += numAces;

        // Increase value of one ace to 11 if possible
        // Having two aces with value 11 is always > 21, so at most one is 11
        if(numAces > 0 && sum+10 <= 21){
            return sum+10;
        }
        return sum;
    }

    // public static Integer cardValues(ArrayList<Card> cards, Integer points){
    //     Integer sum = points;
    //     for(int i=0; i<cards.size(); i++){
    //         Integer value = cards.get(i).getValue();
    //         if(value == 11 && sum + value > 21){
    //            value = 1;
    //         }
    //         sum += value;
    //     }
        
    //     return sum;
    // }

    public static ArrayList<Card> initialCards(Deck deck){
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card1 = deck.draw();
        Card card2 = deck.draw();
        cards.add(card1);
        cards.add(card2);
        return cards;
    }

}
