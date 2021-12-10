import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Deck implements Iterable, CardSource{

    private List<Card> Cards = new ArrayList<>();
    public Deck(){
        this.newDeck();
        this.shuffle();
        // System.out.println(Cards.size());
    }

    private void shuffle(){
        Collections.shuffle(Cards);
    }

    private void newDeck(){
        if(this.isEmpty()) {
            for (Rank aRank : Rank.values()) {
                for (Suit aSuit : Suit.values()) {
                    Cards.add(new Card(aRank, aSuit));
                }
            }
        }
    }

    public Iterator<Card> iterator(){return Cards.iterator();}

    @Override
    public Card draw() {
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
