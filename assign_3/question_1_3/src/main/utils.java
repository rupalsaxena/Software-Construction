import java.util.*;
import java.util.Scanner;

public class utils {
    /*
    Class responsibility:
    To provide some utility functions to support the code.
    To provide mapping for rows, cols, and 2d int array board decodings.
     */

    private static Scanner scanner = new Scanner(System.in);

    public static String[] get_current_future_positions(String input) {
        // return {"current col", "current row", "future col", "future row"}

        String[] col_rows = new String[4];
        for (int i = 0; i < input.length(); i++) {
            String character = Character.toString(input.charAt(i));
            if (i == 1 || i == 2) {
                col_rows[i-1] = character;
            }
            else if (i == 6) {
                col_rows[2] = character;
            }
            else if (i == 7) {
                col_rows[3] = character;
            }
        }
        return col_rows;
    }

    public static boolean is_valid_input_format_hint(String input){
        /*
        Checks if the input is a valid hint
         */
        String[] possible_cols = {"a", "b", "c", "d", "e", "f", "g", "h"};
        String[] possible_rows = {"1", "2", "3", "4", "5", "6","7", "8"};
        if(input.length() == 7){
            String hint = input.substring(0, 5);
            if(hint.equals("hint@") || hint.equals("Hint@")){
                String column = Character.toString(input.charAt(5));
                String row = Character.toString(input.charAt(6));
                if(Arrays.asList(possible_cols).contains(column) && Arrays.asList(possible_rows).contains(row)){
                    return true;
                }
            }
        }
        return false;

    }

    public static String input_move(Player player) {
        // inputs move using scanner

        System.out.println("Input your move " + player.get_player_name() + ":");
        String input = scanner.nextLine();
        return input;
    }

    public static String input_game_mode() {
        // inputs game mode as Single or Double

        System.out.println("Input game mode as ''Single'' or ''Double'' player : ");
        String mode = scanner.nextLine();
        return mode;
    }

    public static String input_player_name(Color player_color) {
        // inputs player name

        System.out.println("Player handling " + player_color + " pieces, please input your name: ");
        String player_name = scanner.nextLine();
        return player_name;
    }

    public static int map_rows(int row) {
        int mapped_row = -1;
        if (row == 8) mapped_row = 0;
        else if (row == 7) mapped_row = 1;
        else if (row == 6) mapped_row = 2;
        else if (row == 5) mapped_row = 3;
        else if (row == 4) mapped_row = 4;
        else if (row == 3) mapped_row = 5;
        else if (row == 2) mapped_row = 6;
        else if (row == 1) mapped_row = 7;
        return mapped_row;
    }

    public static int reverse_map_rows(int row) {
        int reverse_mapped_row = -1;
        if (row == 0) reverse_mapped_row = 8;
        else if (row == 1) reverse_mapped_row = 7;
        else if (row == 2) reverse_mapped_row = 6;
        else if (row == 3) reverse_mapped_row = 5;
        else if (row == 4) reverse_mapped_row = 4;
        else if (row == 5) reverse_mapped_row = 3;
        else if (row == 6) reverse_mapped_row = 2;
        else if (row == 7) reverse_mapped_row = 1;
        return reverse_mapped_row;
    }

    public static int map_columns(String column) {
        int mapped_column = -1;
        if (column.equals("a")) mapped_column = 0;
        else if (column.equals("b")) mapped_column = 1;
        else if (column.equals("c")) mapped_column = 2;
        else if (column.equals("d")) mapped_column = 3;
        else if (column.equals("e")) mapped_column = 4;
        else if (column.equals("f")) mapped_column = 5;
        else if (column.equals("g")) mapped_column = 6;
        else if (column.equals("h")) mapped_column = 7;
        return mapped_column;
    }

    public static String reverse_map_columns(int column){
        String reverse_mapped_column = "" ;
        if(column == 0) reverse_mapped_column = "a";
        else if (column == 1) reverse_mapped_column = "b";
        else if (column == 2) reverse_mapped_column = "c";
        else if (column == 3) reverse_mapped_column = "d";
        else if (column == 4) reverse_mapped_column = "e";
        else if (column == 5) reverse_mapped_column = "f";
        else if (column == 6) reverse_mapped_column = "g";
        else if (column == 7) reverse_mapped_column = "h";
        return reverse_mapped_column;
    }

    public static String[] map_board_values(int board_value){
        String[] map_board_values = new String[3];
        if (board_value == -1) {
            map_board_values[0] = "white";
            map_board_values[1] = "empty";
            map_board_values[2] = "   ";
        }
        else if (board_value == 0) {
            map_board_values[0] = "black";
            map_board_values[1] = "empty";
            map_board_values[2] = "   ";
        }
        else if (board_value == 2) {
            map_board_values[0] = "Red";
            map_board_values[1] = "pawn";
            map_board_values[2] = "R_P";
        }
        else if (board_value == 4) {
            map_board_values[0] = "White";
            map_board_values[1] = "pawn";
            map_board_values[2] = "W_P";
        }
        else if (board_value == 1) {
            map_board_values[0] = "Red";
            map_board_values[1] = "king";
            map_board_values[2] = "R_K";
        }
        else if (board_value == 3) {
            map_board_values[0] = "White";
            map_board_values[1] = "king";
            map_board_values[2] = "W_K";
        }
        return map_board_values;
    }
}
