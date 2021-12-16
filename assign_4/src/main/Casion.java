import java.util.ArrayList;


public class Casion {


    public static String playBlackjack(Integer bet, Player player, Player dealer){
        Deck deck = new Deck();
        String gameState = "Hit";
        Integer pointsUser = 0;
        Integer pointsDealer = 0;
        ArrayList<Card> userCards = new ArrayList<Card>();
        ArrayList<Card> dealerCards = new ArrayList<Card>();
        String result = new String();

        // get two cards for player
        System.out.println("\nYou bet CHF: " + bet + "\n");
//        System.out.println("-----------------------------");
//        System.out.println("Your cards: ");
        userCards = initialCards(deck);
//        for(int i=0; i < userCards.size(); i++){
//            userCards.get(i).print();
//        }
        // get value of cards
        pointsUser = cardValues(userCards, pointsUser);
//        System.out.println("Your points: " + pointsUser);

        // get two cards for dealer
//        System.out.println("\nDealer cards: ");
        dealerCards = initialCards(deck);
//        dealerCards.get(0).print();
        // get value of cards
        ArrayList<Card> modifiedDealerCards = new ArrayList<Card>();
        modifiedDealerCards.add(dealerCards.get(0));
        pointsDealer = cardValues(modifiedDealerCards, pointsDealer);
//        System.out.println("Dealer Points: " + pointsDealer);

        utils.printTable(player.name, dealer.name, userCards, modifiedDealerCards, pointsUser, pointsDealer);
        // Decide wether to stay or not
        while(gameState.equals("Hit") || gameState.equals("hit")){

            while(true){  
                String cardDecision = utils.inputCardDecision();
                boolean newGameState = utils.validCardDecisionInput(cardDecision);
                if(newGameState){
                    gameState = cardDecision;
                    break;
                }
                else{
                    System.out.println("Invalid input!");
                }
    
            }
            if (gameState.equals("Stay") || gameState.equals("stay")){
                break;
            }
            else{ // hit
                Card card3 = deck.draw();
                userCards.add(card3);
//                System.out.println("\n-----------------------------");
//                System.out.println("Your cards:");
//                for(int i=0; i < userCards.size(); i++){
//                    userCards.get(i).print();
//                }
                ArrayList<Card> newCard = new ArrayList<Card>();
                newCard.add(card3);
                Integer pointNewCard = cardValues(newCard, pointsUser);
                pointsUser += pointNewCard;

                if(pointsUser > 21){
                    pointsDealer = cardValues(dealerCards, pointsDealer);
                    utils.printTable(player.name, dealer.name, userCards, dealerCards, pointsUser, pointsDealer);
                    result = "Loose";
                    break;
                }
                else if(pointsUser.equals(21)){
                    utils.printTable(player.name, dealer.name, userCards, dealerCards, pointsUser, pointsDealer);
                    break;
                }   
                else{
                    utils.printTable(player.name, dealer.name, userCards, modifiedDealerCards, pointsUser, pointsDealer);
                }
            }

        }



        if(!result.equals("Loose")){
            pointsDealer = cardValues(dealerCards, pointsDealer);
            utils.printAndWait("Dealers hidden card was " + dealerCards.get(1));
        }
     
        // Dealer draws cards
        while(pointsDealer < pointsUser && pointsUser <= 21){
            Card card4 = deck.draw();
            dealerCards.add(card4);
            /*System.out.println("\nDealer Cards: ");
            for(int i=0; i < dealerCards.size(); i++){
                dealerCards.get(i).print();
            }*/
            ArrayList<Card> newCardDealer = new ArrayList<Card>();
            newCardDealer.add(card4);
            Integer pointNewCardDealer = cardValues(newCardDealer, pointsUser);
            pointsDealer += pointNewCardDealer;

            utils.printAndWait("Dealer drew " + card4.toString());
            if(pointsDealer > 21){
                // System.out.println("Dealer points: " + pointsDealer + " / Your points: " + pointsUser + "\n");
                // System.out.println("-----------------------------");
                result = "Win";
                utils.printAndWait("Dealer busted!");
                break;
            }
        }

        if(result.isEmpty()){
            // Player looses
            if(pointsUser < pointsDealer){
                player.remove_balance(bet);
                dealer.add_balance(bet);
                result = "Loose";
                utils.printTable(player.name, dealer.name, userCards, dealerCards, pointsUser, pointsDealer);
                return result;
            }

            // Tie
            else if(pointsUser.equals(pointsDealer)){

                result = "Tie";
                utils.printTable(player.name, dealer.name, userCards, dealerCards, pointsUser, pointsDealer);
                return result;
            }


            // Player wins
            else{
                player.add_balance(bet);
                dealer.remove_balance(bet);
                result = "Win";
                utils.printTable(player.name, dealer.name, userCards, dealerCards, pointsUser, pointsDealer);
                return result;
            }
        }
        else if(result.equals("Loose")){
            player.remove_balance(bet);
            dealer.add_balance(bet);
            return result;
        }
        else if(result.equals("Win")){
            player.add_balance(bet);
            dealer.remove_balance(bet);
            utils.printTable(player.name, dealer.name, userCards, dealerCards, pointsUser, pointsDealer);
            return result;
        }

        return result;
        

    }

    private static ArrayList<Card> initialCards(Deck deck){
        ArrayList<Card> cards = new ArrayList<Card>();
        Card card1 = deck.draw();
        Card card2 = deck.draw();
        cards.add(card1);
        cards.add(card2);
        return cards;

    }

    private static Integer cardValues(ArrayList<Card> cards, Integer points){
        Integer sum = 0;
        for(int i=0; i<cards.size(); i++){
            Integer value = cards.get(i).getValue(points);
            sum += value;
        }
        
        return sum;
    }

    
    
}
