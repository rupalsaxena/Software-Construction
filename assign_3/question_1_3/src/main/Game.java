import java.net.SocketOption;
import java.util.List;

public class Game {
    /*
    Class responsibility: This is the main class holding the responsibility of running the entire game.
     */

    private static String game_state;
    private static Player player_red = new Player();
    private static Player player_white = new Player();
    private static String game_mode;
    private static Context context = new Context();
    private static StartState startState = new StartState();
    private static StopState stopState = new StopState();

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

        // State pattern implemented here and setting state to Start State of the game
        startState.doAction(context);
        game_state = context.getState().toString();

        System.out.print("Welcome to Checkers Game! \n");

        // initiate method variables
        Board GameBoard = new Board();
        player_red.set_player_color(Color.Red);
        player_white.set_player_color(Color.White);
        String input_move = "";
        String new_move = "";
        Player past_player;

        /*
        Input game mode from terminal.
        Game mode can he either Double or Single, otherwise Game will terminate.
        Input player names depending on which mode is chosen by the player.
         */
        game_mode = utils.input_game_mode();

        if (game_mode.equals("Double")) {
            player_red.set_player_name(utils.input_player_name(Color.Red));
            player_white.set_player_name(utils.input_player_name(Color.White));
        }
        else if (game_mode.equals("Single")) {
            System.out.println("The computer is choosing White pieces.");
            player_red.set_player_name(utils.input_player_name(Color.Red));
            player_white.set_player_name("Computer");
        }
        else {
            System.out.println("Game mode unsupported. Terminating game!");
            return;
        }

        // Setting current player as red since as per game rules red should play first.
        Player current_player = player_red;

        while (true) {
            System.out.println("Score Board: " + player_red.get_player_name() + " = " + (12 - GameBoard.count_pieces(Color.White)) +
                    " ," + player_white.get_player_name() + " = " + (12 - GameBoard.count_pieces(Color.Red)));

            // If the mode is Double then terminal inputs on every move is checked as follows.
            if (game_mode.equals("Double")) {
                while (true) {
                    // input next move
                    new_move = utils.input_move(current_player);

                    /*
                    Checking input of the game.
                    First checking if the input is a valid input,
                    if not then checking is it a valid Hint,
                    if not then it's an invalid move!
                     */
                    boolean validity = GameBoard.check_input_validity(new_move, current_player.get_player_color());
                    if (validity) {
                        input_move = new_move;
                        break;
                    } else {
                        boolean hintValidity = utils.is_valid_input_format_hint(new_move);
                        if (hintValidity) {
                            giveHint(new_move, GameBoard, current_player);
                        } else System.out.println("Invalid move!");
                    }
                }
            }
            // If the mode is Single then terminal inputs on every move is checked as follows.
            else if (game_mode.equals("Single")){
                while (true) {

                    // input player move
                    if (current_player.get_player_color() == Color.Red){
                        new_move = utils.input_move(current_player);
                    }
                    // Computer deciding it's move
                    else if (current_player.get_player_color() == Color.White) {
                        AI ai = AI.getInstance();

                        List<Piece> computer_pieces = Game.player_white.get_pieces();
                        new_move = ai.DumbAI(computer_pieces);
                    }

                    /*
                    Checking input of the game.
                    First checking if the input is a valid input,
                    if not then checking is it a valid Hint,
                    if not then it's an invalid move!
                     */

                    boolean validity = GameBoard.check_input_validity(new_move, current_player.get_player_color());
                    if (validity) {
                        input_move = new_move;
                        break;
                    }
                    else {
                        boolean hintValidity = utils.is_valid_input_format_hint(new_move);
                        if (hintValidity) {
                            giveHint(new_move, GameBoard, current_player);
                        } else System.out.println("Invalid move!");
                    }

                }
            }

            // Once a valid move is received, Game will make a move
            GameBoard.make_move(input_move);

            past_player = current_player;

            //Switch player turns
            if (current_player.get_player_color() == Color.Red) {
                current_player = player_white;
            } else {
                current_player = player_red;
            }

            // Checking if the next player already lost and if the game is over
            game_state = update_game_state(GameBoard, current_player.get_player_color());
            if (game_state.equals("GameOver")) {
                System.out.print("Game is Over! " + past_player.get_player_name() + " is winner!");
                break;
            }
        }
    }

    private static String update_game_state(Board GameBoard, Color player_color) {
        /*
        updates the class variable game_state. Checks game over state and changes game_state to GameOver.
         */
        int white_counter = GameBoard.count_pieces(Color.White);
        int red_counter = GameBoard.count_pieces(Color.Red);
        
        if (white_counter == 0 || red_counter == 0) {
            stopState.doAction(context);
            game_state = context.getState().toString();
        }
        else{
            if(!GameBoard.is_game_ongoing(player_color)){
                stopState.doAction(context);
                game_state = context.getState().toString();
            };
        }
        return game_state;
    }

    private static void giveHint(String new_move, Board GameBoard, Player current_player) {
        /*
        give Hint to player
        */
        String new_move_reformatted = "[" + new_move.substring(5, 7) + "]X[a1]";
        String possibleMoves = GameBoard.getAllPossibleMoves(new_move_reformatted,
                current_player.get_player_color());
        System.out.println("Possible Moves: " + possibleMoves);
    }
}

