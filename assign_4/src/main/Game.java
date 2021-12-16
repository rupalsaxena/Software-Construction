import java.util.Iterator;


public class Game {
    public static void main(String[] args) {

        play();
       
    }

    private static void play() {
        System.out.println("Welcome to Blackjack! \n");
        Deck deck = new Deck();
        Card card = deck.draw();
        String gameState;
        Integer bet;
        // card.print();
        // System.out.println(card.getRank());
        // System.out.println(card.getSuit());
        Player user = new Player("user", 100);
        Player bank = new Player("bank", 100000);
        while(true){
            System.out.println("You current amount: CHF " + user.get_balance());
            // boolean money_left = user.remove_balance(120);
            // System.out.println(money_left);
            while(true){
                if(user.get_balance() < 0){
                    gameState = "No";
                    break;
                }
                else{
                    String newGameState = utils.inputDecision();
                    boolean validInput = utils.validDecisionInput(newGameState);
                    if(validInput){
                        gameState = newGameState;
                        break;
                        
                    }
                    else{
                        System.out.println("Invalid input!");
                    }
                }
            }

            if(gameState.equals("No") || gameState.equals("no")) {
                if(user.get_balance() > 0){
                System.out.print("Game is done. You win CHF " + user.get_balance() + "\n");
                break;
                }
                else{
                    System.out.print("Game over. No money left\n");
                    break; 
                }
            }

            while(true){
                String newBet = utils.inputBet();
                boolean newBetValid = utils.validBetInput(newBet, user);
                if(newBetValid){
                    bet = Integer.valueOf(newBet);
                    break;
                }
                else{
                    System.out.println("Invalid input!");
                }
                
            }
            String result = Casion.playBlackjack(bet, user, bank);
            utils.printAndWait("\nYou " + result + " the round \n");
            System.out.println("-----------------------------");

        }

    }

}
