import java.util.ArrayList;
import java.util.List;

public class Game {
    /*
    Class responsibility: This is the main class holding the responsibility of running the entire game.
    Two class variables: player, game_state
     */

    public static String player;
    public static String game_state;

    public static void play(){
        /*
        This method is called from entrypoint. It is responsible for overall flow and running of the game.
         */

        System.out.print("Welcome to Checkers Game! \n");

        game_state = "Started";
        String input_move;

        int[][] board = Board.init_board();
        Board.display_board(board);

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

            game_state = Game.update_game_state();

            if (game_state.equals("GameOver")) {
                System.out.print("Game is Over! " + player + " is winner!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        /*
        Entry point of the game.
         */

        Game.play();
    }

    public static String update_game_state() {
        /*
        updates the class variable game_state. Checks game over state and changes game_state to GameOver.
         */

        List<Integer> white_pieces = new ArrayList<Integer>();
        List<Integer> red_pieces = new ArrayList<Integer>();
        int[][] board = Board.board;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int board_value = board[i][j];
                if (board_value == 2 || board_value == 1) {
                    red_pieces.add(board_value);
                }
                if (board_value == 3 || board_value == 4) {
                    white_pieces.add(board_value);
                }
            }
        }

        int len_white_pieces = white_pieces.size();
        int len_red_pieces = red_pieces.size();

        if (len_white_pieces == 0 || len_red_pieces == 0) {
            game_state = "GameOver";
        }
        return game_state;
    }
}
