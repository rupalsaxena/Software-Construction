public class Game {
    public static void main(String[] args) {

        play();
       
    }

    private static void play() {
        System.out.println("Welcome to Blackjack! \n");
        String gameState;
        Integer bet;

        String userName = String.format("%.8s", utils.inputName());

        Player user = new Player(userName, 100);
        Player bank = new Player("bank", 100000);
        while(true){
            System.out.println("Your current balance: CHF " + user.getBalance());
            // boolean money_left = user.remove_balance(120);
            // System.out.println(money_left);
            while(true){
                if(user.getBalance() <= 0 || bank.getBalance() <= 0){
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
                if(user.getBalance() > 0 && bank.getBalance() > 0){
                    System.out.print("Game is over. You walk away with CHF " + user.getBalance() + "\n");
                    break;
                }
                else if(bank.getBalance() <= 0){
                    System.out.print("You outplayed the Casino. It has no money left.");
                    break;
                }
                else{
                    System.out.print("Game over. You have no money left to bet\n");
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
            String result = Casino.playBlackjack(bet, user, bank);
            utils.printAndWait("\nYou " + result + " this round \n");
            System.out.println("-----------------------------");

        }

    }

}
