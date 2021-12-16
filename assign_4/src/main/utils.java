import java.util.Scanner;

public class utils {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputDecision() {
        System.out.println("If you want to play type Yes \nIf you want to leave with the money type No");
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean validDecisionInput(String string){
     return true;
        
    }

    public static String inputBet(){
        System.out.println("Pleas make a bet: ");
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
        System.out.println("Do you want to stay or hit?");
        String input = scanner.nextLine();
        return input;
    }

    public static Boolean validCardDecisionInput(String string){
        return true;
    }
}