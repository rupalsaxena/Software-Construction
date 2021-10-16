import java.util.Arrays;

public class Launcher{
    public static void main(String[] args){
        Checkers.main();
        int[][] board = Board.setBoard();
        Board.printBoard(board);
//        System.out.print(Arrays.deepToString(board));
        while (true){
            String player_move = Checkers.input_move(Checkers.playerTurn);
            int[] mapped_move = Checkers.processInputMove(player_move);
            //Move and move validation
            //get new board_state
            int[][] updated_board_state = board; //todo: need to update board state after validating moves
            Board.display_board(updated_board_state);
            // check for winning todo: check for a winner
            boolean winner = false;
            if (winner){
                System.out.print("We have a winner!!");
                break;
            }
        }
    }
}