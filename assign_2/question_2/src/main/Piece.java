import java.awt.*;
import java.util.List;
import java.util.ArrayList;

enum Color{Red, White, Empty}
enum Rank{Pawn, King, Empty}


public class Piece implements Subject{
    private Color pCol;
    private Rank pRank;
    private Point pos;
    private List<Observer> observers = new ArrayList<Observer>();

    public Piece(Color col, Rank rank, int x, int y, Observer Board){
        this.pCol = col;
        this.pRank = rank;
        this.pos = new Point(x,y);
        registerObserver(Board);
    }

    //Need to remove, only used in Board.count_pieces()
    public Color get_color(){
        return this.pCol;
    }
    public void move_piece(Point future_point){
        /*
         * Assumes move to future_pos is valid!
         * Updates pos of piece, promotes if necessary and notifies Observers
         */
        Point old_pos = new Point(pos);
        pos.setLocation(future_point);
        promote_if_necessary();
        notifyObservers("Moved", old_pos, this);
    }

    public void eliminate_piece(){
        pCol = Color.Empty;
        pRank = Rank.Empty;
        Point dummy = new Point(0,0);
        notifyObservers("Removed", pos, this);
    }

    public String encoded_value() {
        // Returns information about Piece as String, used for printing board
        if(pCol == Color.Empty){
            return "   ";
        }
        String[] value = {" ", "_", " "};
        if (pCol == Color.White) {
            value[0] = "W";
        } else if (pCol == Color.Red) {
            value[0] = "R";
        }

        if (pRank == Rank.Pawn) {
            value[2] = "P";
        } else if (pRank == Rank.King) {
            value[2] = "K";
        }
        return (value[0] + value[1] + value[2]);
    }

    private void promote_if_necessary(){
        // Checks if piece needs to be promoted and, if so, promotes piece
        if (pCol == Color.Red && pos.y == 7) {
            pRank = Rank.King;
        }
        if (pCol == Color.White && pos.y == 0) {
            pRank = Rank.King;
        }
    }

    public boolean is_empty(){
        return pCol == Color.Empty;
    }

    public boolean is_valid_move(Piece future, String[] positions, Color color){
        int current_row = utils.map_rows(Integer.valueOf(positions[1]));
        int future_row = utils.map_rows(Integer.valueOf(positions[3]));

        int current_col = utils.map_columns(positions[0]);
        int future_col = utils.map_columns(positions[2]);

        if (this.pRank == Rank.Pawn && this.pCol == Color.Red) { // red pawn
            if (future_row < current_row) {
                System.out.print("Pawn cannot move in opposite direction! \n");
                return false;
            }
        }
        if (this.pRank == Rank.Pawn && this.pCol == Color.White) { // white pawn
            if (future_row > current_row) {
                System.out.print("Pawn cannot move in opposite direction! \n");
                return false;
            }
        }
        if (current_col % 2 == current_row % 2) { // white field
            System.out.print("Your move is from White checkers! ");
            return false;
        }
        if (future_col % 2 == future_row % 2) { // white field
            System.out.print("Your move is to White checkers! ");
            return false;
        }
        if (this.pCol == Color.Empty) {
            System.out.print("There is no piece on your current move! ");
            return false;
        }
        if (future.pCol == color) {
                System.out.print("Future move already has your piece! ");
                return false;
        }
        if (this.pCol != color) {
            System.out.print("This is not your piece! ");
            return false;
        }

        return true;
        // return Moves.check_move(current_row, current_col, future_row, future_col);
    }

    public void registerObserver(Observer observer){
        //Register board as observer
        observers.add(observer);
    }
    public void removeObserver(Observer observer){
        //Not really needed
        observers.remove(observer);
    }
    public void notifyObservers(String event_type, Point old_pos, Piece updated_piece){
        //Make Observers (Board) update position of piece on board
        for (Observer observer : observers) {
            observer.update(event_type, old_pos,updated_piece.pos, updated_piece);
        }
    }
}
