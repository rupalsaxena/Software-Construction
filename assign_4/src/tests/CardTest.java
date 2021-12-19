import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void testgetRank() {
        Card card = new Card(Rank.Ace, Suit.Spades);
        Rank actual = card.getRank();
        Rank expected = Rank.Ace;
        assertEquals(expected, actual);
    }

    @Test
    public void testgetSuit() {
        Card card = new Card(Rank.Ace, Suit.Spades);
        Suit actual = card.getSuit();
        Suit expected = Suit.Spades;
        assertEquals(expected, actual);
    }

    @Test
    public void testtoString() {
        Card card = new Card(Rank.Ace, Suit.Spades);
        String actual = card.toString();
        String expected = "Ace \u2660";
        assertEquals(expected, actual);
    }

    @Test
    public void testgetValue() {
        Card card = new Card(Rank.Ace, Suit.Spades);
        Integer actual = card.getValue();
        Integer expected = 11;
        assertEquals(expected, actual);
    }

}