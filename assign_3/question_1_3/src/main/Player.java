//enum PlayerColor{Red, White}
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player{
    private String player_name;
    private Color player_color;

    public void set_player_name(String name){
        this.player_name = name;
    }

    public void set_player_color(Color color){
        this.player_color = color;
    }

    public String get_player_name(){
        return this.player_name;
    }

    public Color get_player_color(){
        return this.player_color;
    }

    //returns a list of coordinates of pieces that are alive
    public List<Point> get_pieces_coordinates(){
        List<Point> coordinates_list = new ArrayList<Point>();
        Piece[][] board = Board.getBoard();
        //iterate and put pieces in a 1D list based on the player_color information and corresponding attribute in Piece
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                Piece current_piece = board[i][j];
                if (current_piece.getColor() == this.get_player_color()){
                    coordinates_list.add(current_piece.getpos());
                }
            }
        }
        return coordinates_list;
    }

    //returns a list of pieces that are alive
    public List<Piece> get_pieces(){
        List<Piece> piece_list = new ArrayList<>();
        Piece[][] board = Board.getBoard();
        //iterate and put pieces in a 1D list based on the player_color information and corresponding attribute in Piece
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                Piece current_piece = board[i][j];
                if (current_piece.getColor() == this.get_player_color()){
                    piece_list.add(current_piece);
                }
            }
        }
        return piece_list;
    }
}