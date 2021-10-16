public class Game {
    static String player;
    static String game_state;

    public static void Play(){
        System.out.print("Welcome to Checkers Game! \n");

        game_state = "Started";
        String input_move;

        int[][] board = Board.init_board();
        Board.display_board(board);
        // Board.print_board(board); // for printing board array

        Validation validate = new Validation();
        int i = 1;
        while (true) {
            game_state = "Ongoing";

            if (i%2 == 0) player = "White";
            else player = "Red";
            i++;

            while (true) {
                String new_move = utils.input_move(player);
                Boolean validity = validate.is_valid_input(new_move);
                if (validity == true) {
                    input_move = new_move;
                    break;
                }
                else System.out.println("Invalid move!");
            }

            board = Board.update_board(input_move);
            Board.display_board(board);

            game_state = validate.update_game_state();

            if (game_state.equals("GameOver")) {
                System.out.print("Game is Over! " + player + " is winner!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Game.Play();
    }

}
