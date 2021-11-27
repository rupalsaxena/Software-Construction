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

    public String DumbAI(){
        //gets the moves and randomly chooses any move even if they are incorrect
        //we get possible moves for a random piece that can make a move & then we randomly make a move
        //get all locations of pieces that are alive and choose a piece that can move
        List<Piece> all_computer_pieces = Game.player_white.get_pieces();
        System.out.println(all_computer_pieces);
        Piece[][] b = Board.getBoard();
        List<Piece> computer_pieces_can_move = new ArrayList<>();
        for (Piece element : all_computer_pieces){
            Moves m = new Moves(b, Color.White);
            List<Point> possible_moves = m.getAllPossibleMoves(element.pos.x, element.pos.y);
            if (possible_moves.size() > 0){
                computer_pieces_can_move.add(element);
            }
        }
        System.out.println(computer_pieces_can_move);
        Random randomizer = new Random();
        Piece random_piece = computer_pieces_can_move.get(randomizer.nextInt(computer_pieces_can_move.size()));
        System.out.println(random_piece);
        //for chosen piece get a list of possible moves
        List<Point> possible_moves_for_piece = new Moves(b, Color.White).getAllPossibleMoves(random_piece.pos.x,
                random_piece.pos.y);
        System.out.println(possible_moves_for_piece);
        //randomly choose a move and return it
        Random rand = new Random();
        Point random_move = possible_moves_for_piece.get(rand.nextInt(possible_moves_for_piece.size()));
        System.out.print("Dumb AI thinks that it wants to make the move to - ");
        System.out.println(random_move);
        String from =
                "[" + utils.reverse_map_columns(random_piece.pos.y) + utils.reverse_map_rows(random_piece.pos.x) + "]";
        //Convert Point to string
        String to = "[" + utils.reverse_map_columns(random_move.y) + utils.reverse_map_rows(random_move.x) + "]";
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
