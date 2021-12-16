import java.util.List;

public interface CardSource {
    Card draw();
    boolean isEmpty();
    void print(int amount);
}
