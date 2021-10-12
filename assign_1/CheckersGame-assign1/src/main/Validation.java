import java.util.Arrays;

public class Validation {
    public static boolean is_valid_input(String input) {
        Validation validate = new Validation();

        Boolean format_validity = validate.is_valid_input_format(input);
        if (!format_validity) {
            return format_validity;
        }

        String[] col_rows = utils.get_current_future_positions(input);
        Boolean move_validity = Moves.is_valid_move(col_rows);
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

}
