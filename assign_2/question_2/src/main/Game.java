public class Game {
    /*
    Class responsibility: This is the main class holding the responsibility of running the entire game.
    Two class variables: player, game_state
     */

    private static String game_state;

    public static void main(String[] args) {
        /*
        Entry point of the game.
         */

        play();
    }

    private static void play() {
        /*
        This method is called from entrypoint. It is responsible for overall flow and running of the game.
         */
        System.out.print("Welcome to Checkers Game! \n");

        game_state = "Started";
        String input_move;

        Board GameBoard = new Board();
        Color player_color = Color.Red;
        String player = "Red";

        while (true) {
            System.out.println("Players left: Red = " + GameBoard.count_pieces(Color.Red) + ", White = " + GameBoard.count_pieces(Color.White));
            game_state = "Ongoing";

            while (true) {
                String new_move = utils.input_move(player);
                boolean validity = GameBoard.check_input_validity(new_move, player_color);
                if (validity) {
                    input_move = new_move;
                    break;
                } else{
                    boolean hintValidity = utils.is_valid_input_format_hint(new_move);
                    if(hintValidity){
                        String possibleMoves = Board.getAllPossibleMoves(new_move, player_color);
                        if(possibleMoves != "[]"){
                            System.out.println("Possible Moves: " + possibleMoves);
                        }
                    }
                    else System.out.println("Invalid move!");
                }
            }

            GameBoard.make_move(input_move, player_color);
            game_state = update_game_state(GameBoard);

            if (game_state.equals("GameOver")) {
                System.out.print("Game is Over! " + player + " is winner!");
                break;
            }

            //Switch player turns
            if (player_color == Color.Red) {
                player_color = Color.White;
                player = "White";
            } else {
                player_color = Color.Red;
                player = "Red";
            }
        }
    }

    private static String update_game_state(Board GameBoard) {
        /*
        updates the class variable game_state. Checks game over state and changes game_state to GameOver.
         */

        int white_counter = GameBoard.count_pieces(Color.White);
        int red_counter = GameBoard.count_pieces(Color.Red);

        if (white_counter == 0 || red_counter == 0) {
            game_state = "GameOver";
        }
        return game_state;
    }

}
