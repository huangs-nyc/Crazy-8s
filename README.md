# Crazy-8s
author: Steven Huang
A play on UNO game using a deck of cards.

Playing the Game:

To play the game, type "java CrazyEights"

Once the game starts, it will tell you the top card on the discard pile,
along with the current suit that you can match with. The current suit is significant
because if an "8" is played, the current suit shown and top card shown
should INTENTIONALLY be differnt if you choose to change the suit from
the suit attached to the 8 card. For a general rule of thumb, a card is valid to
play matching the suit and rank of the top card, unless an 8 is played, where 
you should look at both the top card rank and the current suit line.

Once you know the top card and current suit, you can play a card!
This program is designed so that you can ONLY play VALID cards.
Input the number (all the way of the left) corresponding to each card of the 
card you want to play in order to play it. If the card you played was valid,
then the program will continue. If not, you will be asked to try again.

Cards with rank of 8 CAN BE PLAYED AT ANY TIME!!
If you played an 8, then you will also be asked to input a character
to what you want to change the suit to. The game will be updated per
your decision. If you know that you do not have any playable cards,
input "-1" in order to draw more cards from the stock pile until you
have a card you can play!

Once you play your turn, the computer will play its turn. It will play
a card either matching the rank or suit of the top card, or, if you played an eight,
it will play a card that matches either the rank of the top card or the 
suit of the current suit line. It can also play a card with the rank of 8
at any time if it has the card in its hand.

Once the computer plays, it is back to your turn! You and the computer
will go back and forth until one of you wins. There are three ways in which
the game ends: (1) there are no more cards to draw from the stock pile, where
if you or the computer cannot draw a card, the game ends and whoever has less
cards wins. (2) The computer plays all their cards and now has an empty hand. They win.
(3) You play all your cards and now have an empty hand. You win.

Good luck, have fun!

Design Choices:

There were no specific extra design choices in either the card or deck class,
they both make a card, and then a deck from that card. The cards in the deck
are shuffled by first changing it into an ArrayList, shuffling, and making it
an Array again.

In Player.java, specific extra design choices included making sure that the
player could not input a integer outside of the ones that they should (either in
the hand or to draw). It also, through an added method, allows a card in the 
hand to be removed ONLY after confirming that it is valid.

In Game.java, there were a could of extra design choices.

Respective hands were dealt using a for loop to make sure only 
7 cards were dealt for each player.

For the computer, the code ensures that it only plays a card
that the player responds with if the stock pile is not empty
and it is a valid cards. This was done with a series of
if statements and booleans confirming those conditions. 

For the player, the code ensures, through similar if statements
as the computer, that the card they are playing is valid. If the 
card is not valid, then the program will tell the player that the action
cannot be performed, and that they should try again. It also tells them
that the action cannot be performed if they try to draw from an empty
stock pile. Thus, the player can only play valid cards.

There were also additional methods for Strings to be printed to help
the readability of the game while it is being played.
