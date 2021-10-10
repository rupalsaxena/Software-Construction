import java.util.Scanner;

public class Checkers {
    static Scanner scanner = new Scanner(System.in);
    public static void main() throws Exception {
        System.out.print("Welcome to Checkers Game! \n");

        int[][] board = Board.init_board();
        // Board.printBoard(board); // for printing initial board

        Checkers checkers = new Checkers();
        String player = "Black"; /* for now. delete later */
        String new_move = checkers.input_move(player);

        Mapper m = new Mapper();
        int mapped_row = m.map_rows("1"); // input row from input here to get mapped row for 2d board array
        int mapped_col = m.map_columns("b"); // input col from input here to get mapped col for 2d board array
        int board_value = board[mapped_row][mapped_col]; // getting values of board
        String[] mapped_board_value = m.map_board_values(board_value); // mapping those values to white, pawn;

    }

    public String input_move(String player) {
        System.out.println("Input your move player " + player + ":");
        String input = scanner.nextLine();
        return input;
    }
}
