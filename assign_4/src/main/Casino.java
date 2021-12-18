import java.util.ArrayList;


public class Casino {


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
        userCards = utils.initialCards(deck);

        // get value of cards
        pointsUser = utils.cardValues(userCards);

        // get two cards for dealer
        dealerCards = utils.initialCards(deck);

        // get value of cards
        ArrayList<Card> modifiedDealerCards = new ArrayList<Card>();
        modifiedDealerCards.add(dealerCards.get(0));
        pointsDealer = utils.cardValues(modifiedDealerCards);

        utils.printTable(player.name, dealer.name, userCards, modifiedDealerCards, pointsUser, pointsDealer);
        // Decide wether to stay or not
        if(!pointsUser.equals(21)){
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
                    pointsUser = utils.cardValues(userCards);
                    if(pointsUser > 21){
                        pointsDealer = utils.cardValues(dealerCards);
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
        }
        



        if(!result.equals("Loose")){
            pointsDealer = utils.cardValues(dealerCards);
            utils.printAndWait("Dealers hidden card was " + dealerCards.get(1));
        }
     
        // Dealer draws cards.
        // TODO: Maybe move loop in previous if-clause
        while(pointsUser < 21 && pointsDealer < 17){
            Card card4 = deck.draw();
            dealerCards.add(card4);
            pointsDealer = utils.cardValues(dealerCards);
            utils.printAndWait("Dealer drew " + card4.toString());
            if(pointsDealer > 21){
                result = "Win";
                utils.printAndWait("Dealer busted!");
                break;
            }
        }

        if(result.isEmpty()){
            // Player looses
            if(pointsUser < pointsDealer){
                player.removeBalance(bet);
                dealer.addBalance(bet);
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
                player.addBalance(bet);
                dealer.removeBalance(bet);
                result = "Win";
                utils.printTable(player.name, dealer.name, userCards, dealerCards, pointsUser, pointsDealer);
                return result;
            }
        }
        else if(result.equals("Loose")){
            player.removeBalance(bet);
            dealer.addBalance(bet);
            return result;
        }
        else if(result.equals("Win")){
            player.addBalance(bet);
            dealer.removeBalance(bet);
            utils.printTable(player.name, dealer.name, userCards, dealerCards, pointsUser, pointsDealer);
            return result;
        }

        return result;
        

    }

}