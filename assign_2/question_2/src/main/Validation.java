import java.util.*;


public class Validation {
    /*
    Class responsibility is to validate the input. Input validity has two parts.
    1. to validate input format
    2. to validate the move
     */

    public static boolean is_valid_input_format(String input) {
        /*
        input: input received from the player
        return: if the format of the input is valid
        for eg.: a valid input format should look like this [a1]X[b2]
        */

        String[] possible_cols = {"a", "b", "c", "d", "e", "f", "g", "h"};
        String[] possible_rows = {"1", "2", "3", "4", "5", "6","7", "8"};
        if (input.length() != 9) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            String character = Character.toString(input.charAt(i));
            if (i == 0 && (!character.equals("["))) return false;
            else if (i == 1 && (!Arrays.asList(possible_cols).contains(character))) return false;
            else if (i == 2 && (!Arrays.asList(possible_rows).contains(character))) return false;
            else if (i == 3 && (!character.equals("]"))) return false;
            else if (i == 4 && (!character.equals("X"))) return false;
            else if (i == 5 && (!character.equals("["))) return false;
            else if (i == 6 && (!Arrays.asList(possible_cols).contains(character))) return false;
            else if (i == 7 && (!Arrays.asList(possible_rows).contains(character))) return false;
            else if (i == 8 && (!character.equals("]"))) return false;
        }
        return true;
    }

    public static boolean is_valid_input_format_hint(String input){
        String[] possible_cols = {"a", "b", "c", "d", "e", "f", "g", "h"};
        String[] possible_rows = {"1", "2", "3", "4", "5", "6","7", "8"};
        String hint = input.substring(0, 5);
        if(input.length() == 7){
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

    public static boolean is_valid_position(int mapped_row, int mapped_column, String player, int[][] board){
        new Moves(board, player);
        int current_board_value = board[mapped_row][mapped_column];
        String[] mapped_current_board_values = utils.map_board_values(current_board_value);

        if (!mapped_current_board_values[1].equals("empty") && !mapped_current_board_values[0].equals(player)) {
            System.out.print("This is not your piece! \n");
            return false;
            }
        if (mapped_current_board_values[1].equals("empty")) {
            System.out.print("There is no piece on your current move! \n");
            return false;
            }
        return true;
    }
        

    public static boolean is_valid_move(String[] positions, String player, int[][] board) {
        /*
        input array of positions with current col, current row, future col, future row
        input player, board
        Validates if the current and final moves are correct
        Collaborates with Moves class
        return: if a move is valid
        */

        new Moves(board, player);
        
        int current_mapped_row = utils.map_rows(Integer.valueOf(positions[1]));
        int future_mapped_row = utils.map_rows(Integer.valueOf(positions[3]));

        int current_mapped_col = utils.map_columns(positions[0]);
        int future_mapped_col = utils.map_columns(positions[2]);

        int current_board_value = board[current_mapped_row][current_mapped_col];
        int future_board_value = board[future_mapped_row][future_mapped_col];

        String[] mapped_current_board_values = utils.map_board_values(current_board_value);
        String[] mapped_future_board_values = utils.map_board_values(future_board_value);

        if (current_board_value == 2) { // red pawn
            if (future_mapped_row < current_mapped_row) {
                System.out.print("Pawn cannot move in opposite direction! \n");
                return false;
            }
        }
        if (current_board_value == 4) { // white pawn
            if (future_mapped_row > current_mapped_row) {
                System.out.print("Pawn cannot move in opposite direction! \n");
                return false;
            }
        }
        if (current_board_value == -1) {
            System.out.print("Your move is from White checkers! ");
            return false;
        }
        if (future_board_value == -1) {
            System.out.print("Your move is to White checkers! ");
            return false;
        }
        if (mapped_current_board_values[1].equals("empty")) {
            System.out.print("There is no piece on your current move! ");
            return false;
        }
        if (!mapped_future_board_values[1].equals("empty")) {
            if (player.equals(mapped_future_board_values[0])) {
                System.out.print("Future move already has your piece! ");
                return false;
            }
        }
        if (!mapped_current_board_values[1].equals("empty") && !mapped_current_board_values[0].equals(player)) {
            System.out.print("This is not your piece! ");
            return false;
        }

        boolean validate_move = Moves.check_move(current_mapped_row, current_mapped_col, future_mapped_row, future_mapped_col);

        if (!validate_move) return false;

        return true;
    }

}
