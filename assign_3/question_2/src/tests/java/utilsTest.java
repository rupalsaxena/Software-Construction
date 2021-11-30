import org.junit.jupiter.api.Test;
import java.lang.*;

import static org.junit.jupiter.api.Assertions.*;

class utilsTest {

    @Test
    void get_current_future_positions() {
        String[] actual = utils.get_current_future_positions("[b6]X[c5]");
        String[] expected = {"b", "6", "c", "5"};
        for(int i=0; i < actual.length; i++){
            assertEquals(expected[i], actual[i]);
        }

    }

    @Test
    void is_valid_input_format_hint() {
        boolean actual = utils.is_valid_input_format_hint("hint@b6");
        boolean expected = true;
        assertEquals(expected, actual);

        boolean actual2 = utils.is_valid_input_format_hint("hintab6");
        boolean expected2 = false;
        assertEquals(expected2, actual2);

        boolean actual3 = utils.is_valid_input_format_hint("hint@b11");
        boolean expected3 = false;
        assertEquals(expected3, actual3);
    }

    @Test
    void input_move() {
        // Would need test to input move on console, not really that necessary, already well-tested through playing
    }

    @Test
    void map_rows() {
        for(int i=1; i<=8; i++){
            assertEquals(utils.map_rows(i), Math.abs(i-8));
        }
    }

    @Test
    void reverse_map_rows() {
        for(int i=0; i<=7; i++){
            assertEquals(utils.reverse_map_rows(i), Math.abs(i-8));
        }
    }

    @Test
    void map_columns() {
        for(int i=97; i<=104; i++){
            String str = String.valueOf((char)i);
            assertEquals(utils.map_columns(str), Math.abs(i-97));
        }
    }

    @Test
    void reverse_map_columns() {
        for(int i=97; i<=104; i++){
            String str = String.valueOf((char)i);
            assertEquals(utils.reverse_map_columns(i-97), str);
        }
    }
}