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
        }
    }
}