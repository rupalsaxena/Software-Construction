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

    public Integer getValue(Integer points) {
        Integer value = 0;
        if(cRank.toString().equals("Jack") || cRank.toString().equals("Queen") || cRank.toString().equals("King") || cRank.toString().equals("Ten")){
            value = 10;
        } 

        else if(cRank.toString().equals("Ace")){
            value = 11;
            if(points + value > 21 ){
                value = 1;
            }
        }
        else if(cRank.toString().equals("One")){
            value = 1;
        }
         else if(cRank.toString().equals("Two")){
            value = 2;
        }
        else if(cRank.toString().equals("Three")){
            value = 3;
        }
        else if(cRank.toString().equals("Four")){
            value = 4;
        }
        else if(cRank.toString().equals("Five")){
            value = 5;
        }
        else if(cRank.toString().equals("Six")){
            value = 6;
        }
        else if(cRank.toString().equals("Seven")){
            value = 7;
        }
        else if(cRank.toString().equals("Eight")){
            value = 8;
        }
        else if(cRank.toString().equals("Nine")){
            value = 9;
        }


        return value;
    }

}