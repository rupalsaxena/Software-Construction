import java.awt.*;

enum Suit {Clubs, Diamonds, Spades, Hearts}
enum Rank {Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King}
public class Card {
    private Rank cRank;
    private Suit cSuit;

    public Card(Rank aRank, Suit aSuit){
        this.cRank = aRank;
        this.cSuit = aSuit;
    }
    public Rank getRank() { return cRank; }
    public Suit getSuit() { return cSuit; }

}