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
        System.out.println("\nPleas make a bet: ");
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean validBetInput(String string, Player player){
        if(string.matches("[0-9]+")){
            Integer input = Integer.valueOf(string);
            if(player.get_balance() >= input){
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
}