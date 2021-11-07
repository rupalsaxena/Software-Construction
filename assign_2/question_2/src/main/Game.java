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

    private static void play(){
        /*
        This method is called from entrypoint. It is responsible for overall flow and running of the game.
         */

        String player;
        System.out.print("Welcome to Checkers Game! \n");

        game_state = "Started";
        String input_move;

        new Board();

        int i = 1;
        while (true) {
            game_state = "Ongoing";

            if (i%2 == 0) player = "White";
            else player = "Red";
            i++;

            while (true) {
                String new_move = utils.input_move(player);
                Boolean validity = Board.check_input_validity(new_move, player);
                if (validity) {
                    input_move = new_move;
                    break;
                }
                else{
                    Boolean hintValidity = Validation.is_valid_input_format_hint(new_move);
                    if(hintValidity){
                        String possibleMoves = Board.getAllPossibleMoves(new_move, player);
                        if(possibleMoves != "[]"){
                            System.out.println("Possible Moves: " + possibleMoves);
                        }
                    }
                    else System.out.println("Invalid move!");
                } 
            }
            
            Board.update_board(input_move, player);
            game_state = update_game_state();

            if (game_state.equals("GameOver")) {
                System.out.print("Game is Over! " + player + " is winner!");
                break;
            }
        }
    }

    private static String update_game_state() {
        /*
        updates the class variable game_state. Checks game over state and changes game_state to GameOver.
         */

        int white_counter = Board.count_pieces("White");
        int red_counter = Board.count_pieces("Red");

        if (white_counter == 0 || red_counter == 0) {
            game_state = "GameOver";
        }
        return game_state;
    }

}
