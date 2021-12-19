import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CasinoTest {
    @Test
    public void testplayBlackjack() {
        List<String> outcomes = new ArrayList<>();
        outcomes.add("Win");
        outcomes.add("Lose");
        outcomes.add("Tie");
        Deck deck = Deck.getInstance();
        Player p1 = new Player("p1", 100);
        Player dealer = new Player("dealer", 100000);
        InputStream sysInBackup = System.in;
        System.setIn(new ByteArrayInputStream("hit\nhit\nhit\nhit\nhit\nhit\nhit\nhit\nhit\nhit\n".getBytes()));
        String result = Casino.playBlackjack(50, p1, dealer);
        assertEquals(1, outcomes.contains(result)?1:-1);
        System.setIn(sysInBackup);
    }
}
//
// returns lose, win, tie
//check among these