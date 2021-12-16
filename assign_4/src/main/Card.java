import java.awt.*;

enum Suit {Spades, Hearts, Diamonds, Clubs}
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

    public void print(){
        System.out.println(this);
    }

    public String toString(){
        String suit = "";
        switch (this.cSuit) {
            case Spades -> suit = "\u2660";
            case Hearts -> suit = "\u2665";
            case Diamonds -> suit = "\u2666";
            case Clubs -> suit = "\u2663";
        }
        return String.format("%s %s", cRank.toString(), suit);
    }

}