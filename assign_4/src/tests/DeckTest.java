import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DeckTest {
    @Test
    public void testdraw() {
        // hard to test it because cards are shuffled
        // and we can only get 1 card from deck using draw.
        // We have nothing to compare it to!
        Deck deck = Deck.getInstance();
        Card card = deck.draw();
        assertTrue(card instanceof Card);
    }
}