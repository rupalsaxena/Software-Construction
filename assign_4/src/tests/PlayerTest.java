import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testgetBalance() {
        Player user = new Player("Group29", 1000);
        int actual1 = user.getBalance();
        int expected1 = 1000;
        assertEquals(expected1, actual1);

        user.addBalance(200);
        int actual2 = user.getBalance();
        int expected2 = 1200;
        assertEquals(expected2, actual2);

        boolean actual3 = user.removeBalance(800);
        boolean expected3 = true;
        assertEquals(expected3, actual3);

        int actual4 = user.getBalance();
        int expected4 = 400;
        assertEquals(expected4, actual4);
    }

    @Test
    public void testaddBalance() {
        // already tested in testgetBalance
    }

    @Test
    public void testremoveBalance() {
        // already tested in testgetBalance
    }

}