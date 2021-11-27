public class Game {
    /*
    Class responsibility: This is the main class holding the responsibility of running the entire game.
    Two class variables: player, game_state
     */

    private static String game_state;
    public static Player player_red = new Player();
    public static Player player_white = new Player();
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
// TODO: Do we check for the fact that when we have many pieces that can make moves, if the Hint or the validity
//  checks if the correct piece is preferred along with the correct move for that piece as well????
    private static void play() {
        /*
        This method is called from entrypoint. It is responsible for overall flow and running of the game.
         */
        startState.doAction(context);
        game_state = context.getState().toString();

        System.out.print("Welcome to Checkers Game! \n");

        Board GameBoard = new Board();
        game_mode = utils.input_game_mode();
        //Color player_color = Color.Red;
        //String player = "Red";
        player_red.set_player_color(Color.Red);
        player_white.set_player_color(Color.White);

        if (game_mode.equals("Double")) {
            player_red.set_player_name(utils.input_player_name(Color.Red));
            player_white.set_player_name(utils.input_player_name(Color.White));
        }
        else if (game_mode.equals("Single")) {
            System.out.println("The computer will take ''White'' pieces.");
            player_red.set_player_name(utils.input_player_name(Color.Red));
            player_white.set_player_name("Computer");
        }
        else {
            System.out.println("Game mode unsupported. Terminating game!");
            return;
        }

        String input_move = "";
        Player current_player = player_red;
        while (true) {
            System.out.println("Pieces left: " + player_red.player_name + " = " + GameBoard.count_pieces(Color.Red) +
                    " ," + player_white.player_name + " = " + GameBoard.count_pieces(Color.White));

            if (game_mode.equals("Double")) {
                while (true) {
                    String new_move = utils.input_move(current_player);
                    boolean validity = GameBoard.check_input_validity(new_move, current_player.player_color);
                    if (validity) {
                        input_move = new_move;
                        break;
                    } else {
                        boolean hintValidity = utils.is_valid_input_format_hint(new_move);
                        if (hintValidity) {
                            String new_move_reformatted = "[" + new_move.substring(5, 7) + "]X[a1]";
                            String possibleMoves = Board.getAllPossibleMoves(new_move_reformatted,
                                    current_player.player_color);
                            System.out.println("Possible Moves: " + possibleMoves);
                        } else System.out.println("Invalid move!");
                    }
                }
            }
            else if (game_mode.equals("Single")){
                if (current_player.player_color == Color.Red){
                    System.out.println("Asking player to make a move -");
                    while (true) {
                        String new_move = utils.input_move(current_player);
                        boolean validity = GameBoard.check_input_validity(new_move, current_player.player_color);
                        if (validity) {
                            input_move = new_move;
                            break;
                        } else {
                            boolean hintValidity = utils.is_valid_input_format_hint(new_move);
                            if (hintValidity) {
                                String new_move_reformatted = "[" + new_move.substring(5, 7) + "]X[a1]";
                                String possibleMoves = Board.getAllPossibleMoves(new_move_reformatted,
                                        current_player.player_color);
                                System.out.println("Possible Moves: " + possibleMoves);
                            } else System.out.println("Invalid move!");
                        }
                    }
                }
                else if (current_player.player_color == Color.White) {
                    System.out.println("AI making a move -");
                    AI ai = new AI();

                    input_move = ai.DumbAI();
                    System.out.println(input_move);
                }
            }

            GameBoard.make_move(input_move, current_player.player_color);
            game_state = update_game_state(GameBoard, current_player.player_color);

            if (game_state.equals("GameOver")) {
                System.out.print("Game is Over! " + current_player.player_name + " is winner!");
                break;
            }

            //Switch player turns
            if (current_player.player_color == Color.Red) {
                current_player = player_white;
            } else {
                current_player = player_red;
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
            if(!GameBoard.check_all_possible_moves(player_color)){
                stopState.doAction(context);
                game_state = context.getState().toString();
            };
        }
        return game_state;
    }
}
