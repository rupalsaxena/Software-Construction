import java.util.Iterator;

public class Game {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Card card = deck.draw();
        card.print();
        System.out.println(card.getRank());
        System.out.println(card.getSuit());
        Player user = new Player("user", 100);
        Player bank = new Player("bank", 100000);
        boolean money_left = user.remove_balance(120);
        System.out.println(money_left);

    }

}
