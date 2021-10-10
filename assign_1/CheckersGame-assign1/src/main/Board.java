public class Board {
    static int[][] board = new int[8][8];

    public static int[][] init_board() {
        //white checks = -1
        //black empty = 0
        //red pawn = 2
        //white pawn = 4
        //red king = 1
        //white king = 3

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
                System.out.print(board[i][j]);
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }
}