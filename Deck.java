/** Deck.java
*   Author: Steven Huang
*
*   Models a typical deck of playing cards
*   To be used with Card class
*
*/

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Deck{

    private Card[] deck; // contains the cards to play with
    private int top; // controls the "top" of the deck to deal from

    // constructs a default Deck
    public Deck(){
        // your code here
        deck = new Card[52];
        createDeck();
        top = 0;
    }

    // Deals the top card off the deck
    public Card deal(){
        // your code here
        Card topCard=deck[top];
        int maxIndex=deck.length;
        for(int i=0; i<deck.length-1; i++) {
            deck[i]=deck[i+1];
        }
        Card[] updatedDeck=Arrays.copyOfRange(deck, 0, maxIndex-1);
        deck=updatedDeck;
        return topCard;
    }


    // returns true provided there is a card left in the deck to deal
    public boolean canDeal(){
        // your code here
        boolean canDeal=true;
        int deckLength=deck.length;
        if(deckLength<=0) {
            canDeal=false;
        }
        return canDeal;
    }

    // Shuffles the deck
    public void shuffle(){
        // your code here
        List<Card> shuffleList=Arrays.asList(deck);
        Collections.shuffle(shuffleList);
        deck=shuffleList.toArray(deck);
    }

    // Accessor method for deck length
    public int getLength() {
        return deck.length;
    }

    // Returns a string representation of the whole deck
    public String toString(){
       // your code here
       String deckDescription;
       deckDescription = "Your deck of Cards is:\n\n";
       for(Card card : deck) {
           deckDescription += card + "\n";
       }
       return deckDescription;
    }

    // you may wish to have more helper methods to simplify
    // and shorten the methods above.

    // creates diamond suit
    public void createDiamonds() {
        int k=0;
        for(int i=0; i<13; i++) {
            deck[i]=new Card('d', k);
            if(k!=13) {
                k++;
            }
        }
    }

    // creates heart suit
    public void createHearts() {
        int k=0;
        for(int i=13; i<26; i++) {
            deck[i]=new Card('h', k);
            if(k!=13) {
                k++;
            }
        }
    }

    // creates spade suit
    public void createSpades() {
        int k=0;
        for(int i=26; i<39; i++) {
            deck[i]=new Card('s', k);
            if(k!=13) {
                k++;
            }
        }
    }

    // creates clubs suit
    public void createClubs() {
        int k=0;
        for(int i=39; i<52; i++) {
            deck[i]=new Card('c', k);
            if(k!=13) {
                k++;
            }
        }
    }

    // creates entire deck
    public void createDeck() {
        createDiamonds();
        createHearts();
        createSpades();
        createClubs();
    }

}