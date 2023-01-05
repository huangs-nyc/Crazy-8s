/** Game.java
*   Author: Steven Huang
*   
*   Game class for playing crazy eights in commandline
*   To be used with Player, Card, Deck classes
*
*/


import java.util.Scanner;
import java.util.ArrayList;

class Game{

    private char currentSuit; // need in case an 8 is played
    private Card faceup; 
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    
    // sets up the Game object for play
    public Game(){
        // your code here
        cards=new Deck();
        faceup=randomFaceup();
        currentSuit=faceup.getSuit();
        input= new Scanner(System.in);
        p1=new Player();
        compHand=new ArrayList<Card>();

    }

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){
        // your code here
        startDeal();
        boolean keepGoing = true;
        while(keepGoing==true) {
            keepGoing=stopGame();
            if(keepGoing==false){
                System.out.println(whyWin());
                break;
            }
            System.out.println(gameTracker());
            humanPlays();
            if(cards.canDeal()==true){
                System.out.println("\nYou played "+faceup+"\n");
            }
            keepGoing=stopGame();
            if(keepGoing==false){
                System.out.println(whyWin());
                break;
            }
            System.out.println(gameTracker());
            Card cpuPlayedCard=computerTurn();
            if(cards.canDeal()==true) {
                System.out.println("Computer played: "+cpuPlayedCard+"\n");
            }
        }
        return keepGoing;
    }

    /* Naive computer player AI that does one of two actions:
        1) Plays the first card in their hand that is a valid play
        2) If no valid cards, draws until they can play

        You may choose to use a different approach if you wish but
        this one is fine and will earn maximum marks
     */

    private Card computerTurn(){
        // your code here
        Card play=compHand.get(0);
        boolean askAgain=true;
        boolean draw=true;
        while(askAgain==true&&cards.canDeal()==true) {
            for(int i=0; i<compHand.size(); i++) {
                play=compHand.get(i);
                if(play.getRank()==faceup.getRank() ||
                play.getSuit()==currentSuit ||
                play.getRank()==7) {
                    currentSuit=play.getSuit();
                    draw=false;
                    askAgain=false;
                    faceup=play;
                    compHand.remove(play);
                    break;
                }
            }
            if(draw==true&&cards.canDeal()==true) {
                compHand.add(cards.deal());
            }
        }
    return play;
    }
    
// you will likely wish to have several more helper methods to simplify
// and shorten the methods above.

    // deal cards
    public void startDeal() {
        for(int i=0; i<7; i++) {
            p1.addCard(cards.deal());
            compHand.add(cards.deal());
        }
        System.out.println("\nWelcome to Crazy Eights! "+
        "You'll start with 7 cards.");
        System.out.println("Your job is to match a card in your "+
        "hand with the up card.");
        System.out.println("You can match it by suit or rank.");
        System.out.println("If you play an 8, you can switch "+
        "the active suit.");
        System.out.println("If you run out of cards, you win!");
        System.out.println("If you make it through the whole deck "+
        "then whoever has the fewest cards left wins!");
        System.out.println("Good luck!\n");
    }

    // print top card in discard pile
    public String showTopCard() {
        String showTopCard;
        showTopCard="The current top card is "+faceup+"\n";
        return showTopCard;
    }

    // prints current suit
    public String showCurrentSuit() {
        String suitInPlay=" ";
        switch(currentSuit) {
            case 'd':
                suitInPlay="The current suit is: Diamonds \n";
                break;
            case 'h':
                suitInPlay="The current suit is: Hearts \n";
                break;
            case 's':
                suitInPlay="The current suit is: Spades \n";
                break;
            case 'c':
                suitInPlay="The current suit is: Clubs \n";
        }
        return suitInPlay;
    }

    // human turn
    public void humanPlays() {
        boolean askAgain=true;
        while(askAgain==true&&cards.canDeal()==true) {
            Card humanPlays=p1.playsTurn(cards);
            if(humanPlays.getSuit()==currentSuit ||
            humanPlays.getRank()==faceup.getRank() ||
            humanPlays.getRank()==7) {
                if(humanPlays.getRank()==7) {
                    faceup=humanPlays;
                    humanWildCard();
                    p1.removeCard(humanPlays);
                    askAgain=false;
                }
                else {
                faceup=humanPlays;
                currentSuit=faceup.getSuit();
                p1.removeCard(humanPlays);
                askAgain=false;
                }
            }
            else {
                errorMessage();
            }
        }
    }

    // if human plays an 8
    public void humanWildCard() {
        System.out.println("Please enter the suit you want to play: "+
        "\'d\',\'h\',\'s\',\'c\', "+
        "where d = diamond, h = hearts, s = spades, and c = clubs.");
        currentSuit=input.next().charAt(0);
    }

    // if human plays a ineligible card
    public void errorMessage() {
        System.out.println("\nYou cannot perform this action.\n");
    }

    // returns who wins
    public String winStatements() {
        String win=" ";
        if((p1.handLength())<compHand.size()) {
            win="Congratulations, you won!";
        }
        else {
            win="Sorry, the computer beat you!";
        }
        return win;
    }

    // returns why who wins
    public String whyWin() {
        String whyWin=" ";
        if(cards.canDeal()==false) {
            whyWin="The game is stopping because there are no "+
            "longer drawable cards. "+winStatements();
        }
        if(compHand.size()==0) {
            whyWin="The game is stopping because the computer "+
            "has played all their cards. "+winStatements();
        }
        if(p1.handLength()==0) {
            whyWin="The game is stopping because you "+
            "have played all your cards. "+winStatements();
        }
        return whyWin;
    }


    // deals first card in discard pile
    public Card randomFaceup() {
        cards.shuffle();
        faceup=cards.deal();
        return faceup;
    }

    // returns current card and suit on discard pile
    public String gameTracker() {
        String gameDescription;
        gameDescription=showTopCard()+showCurrentSuit();
        return gameDescription;
    }

    // stops game if necessary
    public boolean stopGame() {
        boolean keepGoing=true;
        if(cards.canDeal()==false || p1.handLength()==0 ||
            compHand.size()==0) {
            keepGoing=false;
        }
        return keepGoing;
    }

}