public class Moves {
    // Simple move, single jump, multiple jump moves stay here
    public static boolean check_move(int current_row, int current_col, int future_row, int future_col) {
        // check diagonally next values of board according to player and the check what is the situation. If it is empty then it is a possible simple move.
        // Otherwise check for a single jump by further checking diagonally adjusent piece. If a jump is possible then player must take a jump.
        String player = Game.player;
        int check_row;
        int check_col_right;
        int check_col_left;
        //System.out.println(current_row);
        //System.out.println(current_col);
        //System.out.println(future_row);
        //System.out.println(future_col);

        if (player == "Red") { // when row is 7 meaning piece is on most bottom row, for pawn not possible to go further!
            if (current_row == 7) {
                System.out.print("Currently implemented for pawns! King not implemented! Returning false");
                return false;
            }
            check_row = current_row + 1;
        }
        else {
            if (current_row == 0){
                System.out.print("Currently implemented for pawns! King not implemented! Returning false");
                return false;
            }
            check_row = current_row - 1;
        }

        // if (player == "Red"){
            //check_row = current_row + 1;
        if (current_col < 7){
        check_col_right = current_col + 1;
        }
        else return false;
        int board_value_right = Board.board[check_row][check_col_right]; // diagonally right up value of board
        if (current_col > 0){
            check_col_left = current_col - 1;
        }
        else return false;

        int board_value_left = Board.board[check_row][check_col_left]; // diagonally left up value of board
        if (board_value_right == 0 && board_value_left == 0) {
            if (check_row == future_row && (check_col_right == future_col || check_col_left == future_col)) {
                return true;
            }
            else {
                System.out.print("Wrong move! \n");
                return false;
            }
        }
        else {
            System.out.print("part to be implemented! returning false");
            return false;
        }
    }
}
