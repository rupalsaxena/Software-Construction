import java.util.Arrays;

public class Validation {
    public static boolean is_valid_input(String input) {
        Validation validate = new Validation();

        Boolean validity = validate.check_input_format(input);
        if (validity == false) {
            return validity;
        }

        // Mapper m = new Mapper();
        // String col_rows = validate.get_current_future_positions(input);
        // here map the coordinates to board values
        // get values from board using the coordinates
        // once board value for each current and final is available
        // validate the move
        // Mapper m = new Mapper();
        // int mapped_row = m.map_rows(1); // input row from input here to get mapped row for 2d board array
        // int mapped_col = m.map_columns("b"); // input col from input here to get mapped col for 2d board array
        // int board_value = board[mapped_row][mapped_col]; // getting values of board
        // String[] mapped_board_value = m.map_board_values(board_value); // mapping those values to white, pawn;
        return validity;
    }

    public static boolean check_input_format(String input) {
        String[] possible_cols = {"a", "b", "c", "d", "e", "f", "g", "h"};
        String[] possible_rows = {"1", "2", "3", "4", "5", "6","7", "8"};

        if (input.length() != 9) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            String character = Character.toString(input.charAt(i));
            if (i == 0 && (!character.equals("["))) return false;
            if (i == 1 && (!Arrays.asList(possible_cols).contains(character))) return false;
            if (i == 2 && (!Arrays.asList(possible_rows).contains(character))) return false;
            if (i == 3 && (!character.equals("]"))) return false;
            if (i == 4 && (!character.equals("X"))) return false;
            if (i == 5 && (!character.equals("["))) return false;
            if (i == 6 && (!Arrays.asList(possible_cols).contains(character))) return false;
            if (i == 7 && (!Arrays.asList(possible_rows).contains(character))) return false;
            if (i == 8 && (!character.equals("]"))) return false;
        }
        return true;
    }

    public static String[] get_current_future_positions(String input) {
        String[] col_rows = new String[2];
        return col_rows;
    }
}
