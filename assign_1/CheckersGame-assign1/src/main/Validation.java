import java.util.*;

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
        Moves moves = new Moves();

        int current_mapped_row = m.map_rows(Integer.valueOf(positions[1]));
        int future_mapped_row = m.map_rows(Integer.valueOf(positions[3]));

        int current_mapped_col = m.map_columns(positions[0]);
        int future_mapped_col = m.map_columns(positions[2]);

        int current_board_value = Board.board[current_mapped_row][current_mapped_col];
        int future_board_value = Board.board[future_mapped_row][future_mapped_col];

        String[] mapped_current_board_values = m.map_board_values(current_board_value);
        String[] mapped_future_board_values = m.map_board_values(future_board_value);

        String player = Game.player;
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

    public static String update_game_state() {
        String game_state = Game.game_state;
        List<Integer> white_pieces = new ArrayList<Integer>();
        List<Integer> red_pieces = new ArrayList<Integer>();
        int[][] board = Board.board;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int board_value = board[i][j];
                if (board_value == 2 || board_value == 1) {
                    red_pieces.add(board_value);
                }
                if (board_value == 3 || board_value == 4) {
                    white_pieces.add(board_value);
                }
            }
        }

        int len_white_pieces = white_pieces.size();
        int len_red_pieces = red_pieces.size();

        if (len_white_pieces == 0 || len_red_pieces == 0) {
            game_state = "GameOver";
        }
        return game_state;
    }
}
