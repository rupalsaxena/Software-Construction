import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void testGame() {
        InputStream sysInBackup = System.in;
        String input1 = "Group29\ndock\nyes\n202\n20\ncdsc\nhit\ncdsc\nhit\nhit\nhit\nstay\n";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        Exception exception1 = assertThrows(java.util.NoSuchElementException.class, () -> {
            Game.main(new String[]{"game"});
        });
        String expectedMessage1 = "No line found";
        String actualMessage1 = exception1.getMessage();
        assertTrue(actualMessage1.contains(expectedMessage1));
        System.setIn(sysInBackup);

    }
}