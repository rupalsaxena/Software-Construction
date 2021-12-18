

enum Suit {Spades, Hearts, Diamonds, Clubs}
enum Rank {Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King}


public class Card implements Comparable<Card>{
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
        // Returns String encoding the information of the object: Rank Suit
        String suit = "";
        switch (this.cSuit) {
            case Spades -> suit = "\u2660";
            case Hearts -> suit = "\u2665";
            case Diamonds -> suit = "\u2666";
            case Clubs -> suit = "\u2663";
        }
        return String.format("%s %s", cRank.toString(), suit);
    }

    public Integer getValue() {
        // Returns the "value" a card has, used to count player & dealer's points
        Integer value = 0;
        if(cRank.toString().equals("Jack") || cRank.toString().equals("Queen") || cRank.toString().equals("King") || cRank.toString().equals("Ten")){
            value = 10;
        } 

        else if(cRank.toString().equals("Ace")){
            // Can be 1 or 11, but this is handled separately in utils.cardValues
            value = 11;
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

    @Override
    public int compareTo(Card other) {
        /*
         * Can be used to sort cards, allows for reusability & extendability of class
         */
        // separates Aces from rest
        if(this.getRank() == Rank.Ace){
            if(other.getRank() == Rank.Ace){
                return 0; //Equal
            } else{ return 1; } // this is bigger
        } else if(this.getRank() != Rank.Ace){
            if(other.getRank() == Rank.Ace){
                return -1; // other is bigger
            } else{ return 0; } // "Equal"
        }
        return -999;
    }
}