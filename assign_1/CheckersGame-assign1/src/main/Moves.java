public class Moves {
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
