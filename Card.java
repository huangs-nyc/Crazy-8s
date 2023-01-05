/** Card.java
*   Author: Steven Huang
*   
*   Models a typical playing card
*
*/

class Card{
    
    private char suit;
    private int rank;

    // Initializes a card instance
    public Card(char suit, int rank){
        // your code here
        this.suit=suit;
        this.rank=rank;
    }

    // Accessor for suit
    public char getSuit(){
        // your code here;
        return suit;
    }
    
    // Accessor for rank
    public int getRank(){
        // your code here;
        return rank;
    }

    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString(){
        // your code here
        String currentSuit = " ";
        String[] suits = { "Diamonds", "Hearts", "Spades", "Clubs" };
        String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9",
         "10", "Jack", "Queen", "King" };
        
        String cardDescription;
        switch(suit) {
            case 'd':
                currentSuit = suits[0];
                break;
            case 'h':
                currentSuit = suits[1];
                break;
            case 's':
                currentSuit = suits[2];
                break;
            case 'c':
                currentSuit = suits[3];
                break;
        }
        cardDescription=ranks[rank]+" of "+currentSuit;
        return cardDescription;
    }
}