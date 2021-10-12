import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Arrays;


public class Checkers {
    static Scanner scanner = new Scanner(System.in);
    static String currentPlayer = "Red";
    static String playerTurn = "Red";
    public static void main() {
        Checkers checkers = new Checkers();
        System.out.print("Welcome to Checkers - a game by Shantam and Rupal\n");

    };
//    public String input_moves(String player) {
//            System.out.println("Input your move player " + player + ":");
//            BufferedReader reader = new BufferedReader(
//                new InputStreamReader(System.in));
//        return reader.readLine();
//    }

    public static String input_move(String player) {
        System.out.println("Input your move player " + player + ":");
        String input = scanner.nextLine();
        int isCorrectPlayer = Checkers.playerTurnValidate(input);
        if (isCorrectPlayer == 1) {
            if (Checkers.currentPlayer == "Red") {
                Checkers.playerTurn = "White";
                Checkers.currentPlayer = "White";
            } else {
                Checkers.playerTurn = "Red";
                Checkers.currentPlayer = "Red";
            }
        }
        else {
            Checkers.playerTurn = Checkers.currentPlayer;
        }
//        scanner.close();
        return input;
    }
    public static int[] processInputMove(String move){
        //use the mapper to map alphabets to numbers and numbers are numbers
        //return an int array
        //[a1]x[b2]
        //we need to check with a regex if the input follows this pattern, else we cannot split it
        //Once the regex match is a success, we split it
        final String pattern = "\\[[a-h][1-8]\\]x\\[[a-h][1-8]\\]"; //[a-h][1-8]:[a-h][1-8]
        boolean regex_full_match = move.matches(pattern);
        if (regex_full_match==true){
            //split and map the input and return them
            System.out.print(regex_full_match+"\n");
//            String[] split_move = move.split("x");
//            System.out.print(Arrays.deepToString(split_move));
            System.out.print("\n");
            System.out.print(move.split(""));
            System.out.print("\n");
            char[] char_input = move.replaceAll("[\\[\\]x]","").toCharArray();
            System.out.print(char_input[0]+" "+char_input[1]+" "+char_input[2]+" "+char_input[3]);
            System.out.print("\n");
            System.out.print(move.split("(?<=.)"));
            System.out.print("\n");
            //map characters to integers
            return new int[] {1,2,3,4};
        }
        else{
            System.out.print(regex_full_match+"\n");
            return new int[] {0};
            //Not a valid input
            //How to show the prompt once again?
        }
    }
    public static int playerTurnValidate(String inputMove){
        //use input move and current player info to return 1 or 0
        return 0;
    }
//    public static void makePawns() {
//        for (i=0;i<30;i++){
//            new Pawn();
//        }
//    };
//    public static void makePlayers() {
//
//    };
}
