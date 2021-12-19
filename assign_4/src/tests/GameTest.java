import org.junit.Test;
import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void testGame() {

        // test 1
        String input1 = "Group29\nyes\n20\nstay";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        Exception exception1 = assertThrows(java.util.NoSuchElementException.class, () -> {
            Game.main(new String[]{"game"});
        });
        String expectedMessage1 = "No line found";
        String actualMessage1 = exception1.getMessage();

        assertTrue(actualMessage1.contains(expectedMessage1));

        // test 2
        String input2 = "Group29\nyes\n20\nhit\nyes\n30\nstay";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        Exception exception2 = assertThrows(java.util.NoSuchElementException.class, () -> {
            Game.main(new String[]{"game"});
        });
        String expectedMessage2 = "No line found";
        String actualMessage2 = exception2.getMessage();

        assertTrue(actualMessage2.contains(expectedMessage2));
    }
}
//test invalid inputs