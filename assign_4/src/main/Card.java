import java.awt.*;
import java.io.PrintWriter;

enum Suit {Spades, Hearts, Diamonds, Clubs}
enum Rank {Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King}
public class Card {
    private Rank cRank;
    private Suit cSuit;
    private PrintWriter pw;

    public Card(Rank aRank, Suit aSuit){
        this.cRank = aRank;
        this.cSuit = aSuit;
        pw = new PrintWriter(System.out, true);
    }
    public Rank getRank() { return cRank; }

    public Suit getSuit() { return cSuit; }

    public void print(){
        System.out.println(this);
    }

    public String toString(){
        return String.format("%s of %s", cRank.toString(), cSuit.toString());
    }

}