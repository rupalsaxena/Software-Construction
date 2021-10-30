import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Board {
    /*
    Class responsibility: To initialize, display, update the board, provide piece count to change game state to game over, check input validity.
    This class has a class variable board.
    This class variable is a 2d int array which stores encoded values of pieces and checkers.
     */

    private static int[][] board = new int[8][8];

    public Board() {
        // initialize and display the initial board

        init_board();
        display_board();
    }

    private static void init_board() {
        /*
        This method is initializing the board. Board is a 2d array storing encoded values of pieces and checkers.
        Following is the encoding:
        white checks = -1
        black empty = 0
        red pawn = 2
        white pawn = 4
        red king = 1
        white king = 3
        */

        //For the Red player
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 9; j++) {
                if ((i % 2 == 1) && (j % 2 == 0)) {
                    board[i - 1][j - 1] = 2;
                } else if ((i % 2 == 0) && (j % 2 == 1)) {
                    board[i - 1][j - 1] = 2;
                } else if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 == 1) && (j % 2 == 1))) {
                    board[i - 1][j - 1] = -1;
                }
            }
        }
        //For the empty rows in between the players
        for (int i = 4; i < 6; i++) {
            for (int j = 1; j < 9; j++) {
                if ((i == 4) && (j % 2 == 1)) {
                    board[i - 1][j - 1] = 0;
                }
                else if((i == 4) && (j % 2 == 0)) {
                    board[i-1][j-1] = -1;
                }
                else if((i == 5) && (j % 2 == 1)) {
                    board[i-1][j-1] = -1;
                }
                else if ((i == 5) && (j % 2 == 0)) {
                    board[i - 1][j - 1] = -0;
                }
            }
        }
        //For the White player
        for (int i = 6; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if ((i % 2 == 0) && (j % 2 == 1)) {
                    board[i - 1][j - 1] = 4;
                } else if ((i % 2 == 1) && (j % 2 == 0)) {
                    board[i - 1][j - 1] = 4;
                } else if (((i % 2 == 0) && (j % 2 == 0)) || ((i % 2 == 1) && (j % 2 == 1))) {
                    board[i - 1][j - 1] = -1;
                }
            }
        }
    }

    private static void display_board() {
        /*
        This method takes 2d array board value convert it to visually understandable form and display it.
         */

        System.out.print("      a     b     c     d     e     f     g     h\n");
        System.out.print("  +-------------------------------------------------+\n");
        for (int i = 0; i < 8; i++) {
            int mapped_row = Mapper.reverse_map_rows(i);
            System.out.print(mapped_row +" |");
            for (int j = 0; j < 8; j ++) {
                int board_value = board[i][j];
                String[] mapped_board_value = Mapper.map_board_values(board_value);
                System.out.print(" [" + mapped_board_value[2] + "]" );
            }
            System.out.print(" | " + mapped_row +"\n");
        }
        System.out.print("  +-------------------------------------------------+\n");
        System.out.print("      a     b     c     d     e     f     g     h\n");
    }

    public static void update_board(String input, String player) {
        /*
        This method is updating the board in case of knocks out and when pieces are moving.
         */

        List<Point> knock_out_positions = Moves.knock_out_positions;
        String[] col_rows = utils.get_current_future_positions(input);

        int current_mapped_row = Mapper.map_rows(Integer.valueOf(col_rows[1]));
        int future_mapped_row = Mapper.map_rows(Integer.valueOf(col_rows[3]));

        int current_mapped_col = Mapper.map_columns(col_rows[0]);
        int future_mapped_col = Mapper.map_columns(col_rows[2]);

        int current_board_value = board[current_mapped_row][current_mapped_col];
        int future_board_value = current_board_value;

        if (player.equals("Red") && future_mapped_row == 7 && current_board_value%2 == 0) {
            future_board_value = 1;
        }
        if (player.equals("White") && future_mapped_row == 0 && current_board_value%2 == 0) {
            future_board_value = 3;
        }

        board[current_mapped_row][current_mapped_col] = 0;
        board[future_mapped_row][future_mapped_col] = future_board_value;

        for (int i = 0; i < knock_out_positions.size(); i++) {
            int row = (int) knock_out_positions.get(i).getX();
            int col = (int) knock_out_positions.get(i).getY();
            board[row][col] = 0;
        }
        display_board();
    }

    public static int count_pieces(String player) {
        /*
        count and return pieces of given player
        */

        List<Integer> player_pieces = new ArrayList<Integer>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int board_value = board[i][j];

                if (player.equals("Red") && (board_value == 1 || board_value == 2)) {
                    player_pieces.add(board_value);
                }
                if (player.equals("White") && (board_value == 3 || board_value == 4)) {
                    player_pieces.add(board_value);
                }

            }
        }
        int counter = player_pieces.size();

        return counter;
    }

    public static boolean check_input_validity(String input, String player) {
        /*
        input: input received from the player and player
        return: if input is valid or not by checking input format and moves
        */

        Boolean format_validity = Validation.is_valid_input_format(input);
        if (!format_validity) {
            return format_validity;
        }

        String[] col_rows = utils.get_current_future_positions(input);
        Boolean move_validity = Validation.is_valid_move(col_rows, player, board);
        if (!move_validity) {
            return move_validity;
        }
        return true;
    }
}