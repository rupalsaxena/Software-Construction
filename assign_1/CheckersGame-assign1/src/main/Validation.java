import java.util.Arrays;

public class Validation {
    public static boolean is_valid_input(String input) {
        Validation validate = new Validation();

        Boolean format_validity = validate.is_valid_input_format(input);
        if (!format_validity) {
            return format_validity;
        }

        String[] col_rows = utils.get_current_future_positions(input);
        Boolean move_validity = validate.is_valid_move(col_rows);
        if (!move_validity) {
            return move_validity;
        }
        return true;
    }

    public static boolean is_valid_input_format(String input) {
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
    public static boolean is_valid_move(String[] positions) {
        Mapper m = new Mapper();

        int current_mapped_row = m.map_rows(Integer.valueOf(positions[1]));
        int future_mapped_row = m.map_rows(Integer.valueOf(positions[3]));

        int current_mapped_col = m.map_columns(positions[0]);
        int future_mapped_col = m.map_columns(positions[2]);

        int current_board_value = Board.board[current_mapped_row][current_mapped_col];
        int future_board_value = Board.board[future_mapped_row][future_mapped_col];

        String[] mapped_current_board_values = m.map_board_values(current_board_value);
        String[] mapped_future_board_values = m.map_board_values(future_board_value);

        String player = Checkers.player;

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
            System.out.print("Future move already has a piece! ");
            return false;
        }
        if (!mapped_current_board_values[1].equals("empty") && !mapped_current_board_values[0].equals(player)) {
            System.out.print("This is not your piece! ");
            return false;
        }
        return true;
        // todo:
        // if pawn then move should be in one direction
        // if king move can be in any direction
        // check single, simple, multiple jump move
    }

}
