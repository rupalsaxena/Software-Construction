public class Game {
    static String player;

    public static void Play(){
        System.out.print("Welcome to Checkers Game! \n");

        int[][] board = Board.setBoard();
        Board.display_board(board);
        // Board.print_board(board); // for printing board array

        Validation validate = new Validation();

        for (int i = 1; i < 3; i++) { // todo: use while loop later and break when game is over
            if (i%2 == 0) player = "White";
            else player = "Red";
            while (true) {
                String new_move = Checkers.input_move(player);
                Boolean validity = validate.is_valid_input(new_move);
                if (validity == true) break;
                else System.out.println("Invalid move!");
            }
            // todo: once received a valid move, update the board
            // todo: check game state before asking for next move else break the loop
        }

    }

    public static void main(String[] args) {
        Game.Play();
    }

}
