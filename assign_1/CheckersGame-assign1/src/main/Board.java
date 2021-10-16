import java.util.Arrays;

public class Board {
    static int[][] board = new int[8][8];

    public static int[][] setBoard() {
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
    
    public static void printBoard(int[][] boardState) {
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                System.out.print(boardState[i][j]);
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

//todo
//We need to have "current board" where we make the updates to pieces positions
//the easiest way so far to update the board is to track just one player and one change at a time
//this means, moves or conditions where multiple pieces are affected, need to be broken down into multiple
//smaller updates
    public static int[][] updateBoard(int piece, int[] initialToFinalPosition, int[][] currentBoard) {
        System.out.print("Piece = ");
        System.out.print(piece);
        System.out.print("Initial to final position = ");
        System.out.print(Arrays.toString(initialToFinalPosition));
        return currentBoard; //todo: change to updated board
    }
}


