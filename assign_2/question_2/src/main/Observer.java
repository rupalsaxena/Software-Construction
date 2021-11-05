import java.awt.*;

public interface Observer {
    public void update(String event_type, Point old_pos, Point new_pos, Piece updated_piece);
}