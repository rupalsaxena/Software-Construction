import java.awt.*;

public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers(String event_type, Point old_pos, Piece updated_piece);
}