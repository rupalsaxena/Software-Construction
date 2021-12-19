import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements CardSource{
    /*
    This class implements CardSource interface.
    Deck is a Single Object class and we implemented Singleton pattern
    in this class.
     */
    private static Deck instance = new Deck();
    private Deck(){
        this.newDeck();
    }
    public static Deck getInstance() {
        return instance;
    }

    private List<Card> Cards = new ArrayList<>();
    private void shuffle(){
        Collections.shuffle(Cards);
    }

    private void newDeck(){
        // Fill deck with 6 * 52 unique cards
        // 6 full decks are used to hinder card counting. Strategy commonly used by casinos
        if(this.isEmpty()) {
            for(int i=0; i<6; i++){
                for (Rank aRank : Rank.values()) {
                    for (Suit aSuit : Suit.values()) {
                        Cards.add(new Card(aRank, aSuit));
                    }
                }
            }
            this.shuffle();
        }
    }

    @Override
    public Card draw() {
        // Refill deck if empty, then draw & return card
        if (Cards.isEmpty()) {
            this.newDeck();
        }
        return Cards.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return Cards.size() == 0;
    }
}
