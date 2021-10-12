import java.util.Scanner;

public class Checkers {
    static Scanner scanner = new Scanner(System.in);
    public static void main() throws Exception {
        System.out.print("Welcome to Checkers Game! \n");

        int[][] board = Board.init_board();
        Board.display_board(board);
        // Board.print_board(board); // for printing board array

        Checkers checkers = new Checkers();
        Validation validate = new Validation();
        String player;

        for (int i = 1; i < 3; i++) { // TODO: increase loops later
            if (i%2 == 0) player = "White";
            else player = "Red";
            for (int j = 1; j < 3; j++) { // TODO: increase loops later
                String new_move = checkers.input_move(player);
                Boolean validity = validate.is_valid_input(new_move);
                if (validity == true) break;
                else System.out.println("Invalid move!");
            }
        }
    }

    public String input_move(String player) {
        System.out.println("Input your move player " + player + ":");
        String input = scanner.nextLine();
        return input;
    }
}
