import java.util.Iterator;

public class Game {
    public static void main(String[] args) {
        CardSource deck = new Deck();
        for(int i=0; i<5; i++){
            Card card = deck.draw();
            card.print();
        }

        Player user = new Player("user", 100);
        Player bank = new Player("bank", 100000);
        boolean money_left = user.remove_balance(90);
        System.out.println(money_left);

    }

}
