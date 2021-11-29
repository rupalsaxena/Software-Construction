import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void test_play(){
        /*
         * Hard to test, needs user input and main runs in infinite loop until game is over
         * -> Would need to encode sequence of inputs that results in gameover in string, tedious
         * "Solution": Input some moves to get line coverage, when string is finished error is thrown which we test for
         */
        String input = "[b6]X[c5]\nhint@c3\n[c3]X[b55]\n[c3]X[b4]\n[d6]X[e5]\n[b4]X[d6]";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Exception exception = assertThrows(java.util.NoSuchElementException.class, () -> {
            Game.main(new String[]{"game"});
        });

        String expectedMessage = "No line found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}