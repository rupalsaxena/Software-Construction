//import org.yaml.snakeyaml.DumperOptions;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;


public class AI {

    // Singleton pattern implemented in this class
    private static AI instance = new AI();
    private AI() {}

    public static AI getInstance() {
        return instance;
    }

    public String DumbAI(List<Piece> all_computer_pieces){
        /*
        Gets the computer pieces and randomly choose a piece whose moves are possible.
        From the randomly chosen piece, it will randomly choose a move from all the possible moves.
         */
        Piece[][] b = Board.getBoard();
        List<Piece> computer_pieces_can_move = new ArrayList<>();

        for (Piece element : all_computer_pieces){
            Moves m = new Moves(b, Color.White);
            List<Point> possible_moves = m.getAllPossibleMoves(element.getpos().y, element.getpos().x);
            if (possible_moves.size() > 0){
                computer_pieces_can_move.add(element);
            }
        }

        Random randomizer = new Random();
        Piece random_piece = computer_pieces_can_move.get(randomizer.nextInt(computer_pieces_can_move.size()));

        //for chosen piece get a list of possible moves
        List<Point> possible_moves_for_piece = new Moves(b, Color.White).getAllPossibleMoves(random_piece.getpos().y,
                random_piece.getpos().x);

        //randomly choose a move and return it
        Random rand = new Random();
        Point random_move = possible_moves_for_piece.get(rand.nextInt(possible_moves_for_piece.size()));
        String from =
                "[" + utils.reverse_map_columns(random_piece.getpos().x) + utils.reverse_map_rows(random_piece.getpos().y) + "]";

        //Convert Point to string
        String to = "[" + utils.reverse_map_columns(random_move.y) + utils.reverse_map_rows(random_move.x) + "]";
        System.out.print("Computer's move: ");
        System.out.println(from+"X"+to);

        return from+"X"+to;
    }

    public void BasicAI(){
        //int max_kill_score;
        //dict or map of piece with its move with its score;
        //gets the possible moves and chooses the moves that follow the rules as well as prefers moves that kill
        //call the method that gets all the moves (the following method ignores simple moves if jump moves are also
        // possible since jump moves have higher priority
        // we get all pieces that are alive
        // we get all possible moves for each of the piece that is alive
        // for each move we calculate kill score
        // we choose the move that has maximum kill score
        //Map<String, String> map = new HashMap<String, String>();
    }

    public void SmartAI(){
        //implements minimax algorithm to come up with moves that are intelligent and can challenge the player
        //return;
    }

}
