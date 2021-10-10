import java.util.Scanner;

public class Checkers {
    static Scanner scanner = new Scanner(System.in);
    public static void main() {
        System.out.print("Welcome to Checkers Game! \n");

        int[][] board = Board.init_board();
        Board.printBoard(board); // just for printing initial board

        Checkers checkers = new Checkers();
        String player = "Black"; /* for now. delete later */
        String new_move = checkers.input_move(player);
    }

    public String input_move(String player) {
        System.out.println("Input your move player " + player + ":");
        String input = scanner.nextLine();
        return input;
    }
}
