import java.util.Iterator;

public class Game {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Card card = deck.draw();
        System.out.println(card.getRank());
        System.out.println(card.getSuit());
    }

}
