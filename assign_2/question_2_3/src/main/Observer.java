import java.awt.*;

public interface Observer {
    public void update(String event_type, Point old_pos, Point new_pos, Color new_pos_color);
}