/** Player.java
*   Author: Steven Huang
*
*   Player class as part of Crazy Eights
*   To be used with Game, Card, Deck classes
*
*/

import java.util.ArrayList;
import java.util.Scanner;

class Player{
    
    private ArrayList<Card> hand; // the player's hand
    private Scanner input;

    public Player(){
        // your code here
        hand=new ArrayList<Card>();
        Scanner input=new Scanner(System.in);
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        // your code here
        hand.add(c);
    }
   
    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    public Card playsTurn(Deck deck){
        // your code here
        Card playedCard=hand.get(0);
        boolean askAgain=true;
        Scanner input=new Scanner(System.in);
        while(askAgain==true) {
            System.out.println(handStatement());
            int choice=input.nextInt();
            if(choice>0&&choice<hand.size()+1) {
                playedCard=hand.get(choice-1);
                askAgain=false;
            }
            if(choice==-1) {
                if(deck.canDeal()==true)
                addCard(deck.deal());
                else {
                    System.out.println("There are no more cards in the deck.");
                    askAgain=false;
                }
            }
            if(choice==0||choice>=hand.size()+1||choice<-1) {
                System.out.println(invalidChoice());
            }
        }
        return playedCard;
    }
    
    // Accessor for the players hand
    public ArrayList<Card> getHand(){
        // your code here
        return hand;
    }

    // Returns a printable string representing the player's hand
    public String handToString(){
        // your code here
        int position=1;
        String handDescription;
        handDescription="Your cards in your hand are: \n\n";
        for(Card card : hand ) {
            handDescription+=position+"\t"+card+"\n";
            position++;
        }
        return handDescription;
    }

// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.

    // Removes a card from the player's hand
    public void removeCard(Card c) {
        hand.remove(c);
    }

    // Accessor for human hand size
    public int handLength() {
        return hand.size();
    }

    // returns String for invalid hand choices
    public String invalidChoice() {
        String invalidChoice;
        invalidChoice="\nThis option is not in your hand. Please try again.\n";
        return invalidChoice;
    }

    // returns player hand statement
    public String handStatement() {
        String handStatement;
        handStatement="Type \'-1\' to draw a card, or type "+
        "the number next to the card in your hand that you wish to play \n"+
        handToString();
        return handStatement;
    }

} // end
