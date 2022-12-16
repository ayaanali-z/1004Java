/* Ayaan Ali 
* aa4688 
* Player.java - This class creates a player.
* It constructs a player with a bankroll, hand, and bet. 
* It includes methods to remove and add cards to the hand. 
* It includes methods to calculate bets and tally winnings. 
*/

import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Scanner; 

public class Player {
	
		
	private ArrayList<Card> hand; 
	private double bankroll;
	private double bet;
		
	public Player(){
		
        bankroll = 50.0; // tokens 
        bet = 0.0; 
        hand = new ArrayList<Card>(); // creates hand
 	}

	public void addCard(Card c){
            hand.add(c); // adds card to hand 
	}
	
	public void addCard(Card c, int index) { 
            hand.add(index, c); /* overloaded addCard method to 
            assist in preventing players from repeatedly exchanging 
            the same card in the Game class */
	}

	public void removeCard(Card c){
            hand.remove(c); // removes card from hand 
        }

    	public ArrayList<Card> getHand() { 
        	return hand; // gives hand 
    	}

    	public Card getCard(int i) { 
        	return hand.get(i); // gives specific card from hand 
    	}
		
    	public void bets(double amt){ 
        	if (amt <= bankroll && amt < 6 && amt > 0) { // input validation
            	bet = amt;
             	bankroll -= bet; 
        	}
        	else { 
                System.out.println("You can't bet more than your bankroll."); 
        	} 
    	}

    	public void winnings(double odds){
		
        	double winnings = (bet * odds); // increases winnings with higher bets
        	bankroll+=winnings; 
        	System.out.println("YOU WON " + winnings + " TOKENS!"); 
    	}

    	public double getBankroll(){
            	return bankroll; // returns bankroll 
    	}	
}


