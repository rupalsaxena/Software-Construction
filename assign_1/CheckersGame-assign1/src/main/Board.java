import java.util.Map;

public class Board {
    static int[][] board = new int[8][8];

    public static int[][] init_board() {
        /* white checks = -1
        black empty = 0
        red pawn = 2
        white pawn = 4
        red king = 1
        white king = 3 */

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
        return board;
    }
    
    public static void print_board(int[][] boardState) {
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                System.out.print(board[i][j]);
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }

    public static void display_board(int[][] board) {
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

    public static int[][] update_board(String input) {
        // todo: update when there is a king piece
        // todo: update in case of knockout
        Mapper m = new Mapper();

        String[] col_rows = utils.get_current_future_positions(input);

        int current_mapped_row = m.map_rows(Integer.valueOf(col_rows[1]));
        int future_mapped_row = m.map_rows(Integer.valueOf(col_rows[3]));

        int current_mapped_col = m.map_columns(col_rows[0]);
        int future_mapped_col = m.map_columns(col_rows[2]);

        int current_board_value = Board.board[current_mapped_row][current_mapped_col];
        // see later what happen when there is a king in the game

        Board.board[current_mapped_row][current_mapped_col] = 0;
        Board.board[future_mapped_row][future_mapped_col] = current_board_value;

        return Board.board;
    }
}