import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements CardSource{

    private List<Card> Cards = new ArrayList<>();

    public Deck(){
        this.newDeck();
    }

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

    public void print(int amount){
        System.out.format("%7s | %8s | %8s\n ---------------------------------\n"," ", "player", "dealer");
        for(int i=0; i<amount; i++){
            Card card = this.draw();
            System.out.format("%7s | %8s | %8s\n","Cards", card, card);
        }
        System.out.format("---------------------------------\n%7s | %8d | %8d\n ","Points", 12, 15);
    }
    @Override
    public Card draw() {
        // Refill deck if empty, then draw card
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
