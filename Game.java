/* Ayaan Ali 
* aa4688 
* Player.java - This class creates the game.
* It includes: a game constructor (overloaded), a play 
* method, a checkHand method, a method to replace cards, 
* a method to deal cards, and 9 boolean methods to evaluate 
* hands. 
*/

import java.util.ArrayList; 
import java.util.Scanner; 
import java.util.Collections; 

public class Game {

	private Player p;
	private Deck cards;
	
	public Game(String[] testHand){ // takes in command line arguments 
		
		p = new Player(); 
		cards = new Deck(); 

		for(String a: testHand) { 
			String suit = "" + a.charAt(0); // determines suit 
			int rank = 0; 

			if(a.length() == 3) { // if it's a rank over 10 
				int digitTwo = Character.getNumericValue(a.charAt(2)); 

				rank = 10 + digitTwo; 
			}
			else {
				rank = Character.getNumericValue(a.charAt(1));
			}  
			
			int suitNumber = 0; 
			String spades = "s"; // suit comparables 
			String clubs = "c"; 
			String hearts = "h";
			String diamonds = "d"; 

			if(suit.equals(spades)) { 
				suitNumber = 1;  
			}
			else if(suit.equals(clubs)) { 
				suitNumber = 2; 
			}
			else if (suit.equals(hearts)) { 
				suitNumber = 3; 
			}
			else if (suit.equals(diamonds)) { 
				suitNumber = 4; 
			}
			Card testCard = new Card(suitNumber, rank); // adds cards 
			p.addCard(testCard); 
		}
	}
	
	public Game(){
		p = new Player(); // constructs player 
		cards = new Deck(); // constructs deck 		
	}
	
	public void play(){ // plays the game 
		System.out.println("%$%$%$%$%$% WELCOME TO P O K E R %$%$%$%$%$%"); 
		
		Scanner input = new Scanner(System.in); 

		System.out.println("You have " + p.getBankroll() + " tokens."); 
		System.out.println("You can bet up to 5 tokens."); 
		System.out.println("How much are you betting?"); 

		double b = input.nextDouble(); // input bet 

		while(b > 5 || b < 0) { // input validation 
			System.out.println("Please bet between 1 to 5 tokens.");
			b = input.nextDouble(); 
		}

		if ( b < 5 || b > 0) { 
			p.bets(b); 
		} 

		System.out.println("%$%$%$% SHUFFLING CARDS %$%$%$%"); 

		cards.shuffle(); // shuffles deck

		System.out.println("%$%$%$% DEALING CARDS %$%$%$%");

		deal(); // deals first hand 

		System.out.println("%$%$%$% YOUR HAND %$%$%$%");
		
		Collections.sort(p.getHand()); // sorts using Collections by rank 

		System.out.println(p.getHand()); 

		replaceCard(p.getHand()); // allows user to replace cards 

		System.out.println("%$%$%$% EVALUATING HAND %$%$%$%"); 

		System.out.println(checkHand(p.getHand())); // evaluates hand 

		System.out.println("%$%$%$% THANK YOU FOR PLAYING %$%$%$%"); 
	}
	
	public String checkHand(ArrayList<Card> hand){ 
		int payout = 0; 
		String yourHand = ""; 
		
		Collections.sort(hand); // sorts 

		if(royalFlush(hand)) { // uses boolean methods below to evaluate hand
			payout = 250; 
			yourHand = "Wow! You have a royal flush!"; 
		}
		else if (straightFlush(hand)) { 
			payout = 50; 
			yourHand = "Wow! You have a straight flush!"; 
		}
		else if (fourOfAKind(hand)) { 
			payout = 25; 
			yourHand = "You have four of a kind!";
		}
		else if (fullHouse(hand)) { 
			payout = 6; 
			yourHand = "You have a full house!"; 
		}
		else if (flush(hand)) { 
			payout = 5; 
			yourHand = "You have a flush!"; 
		}
		else if (straight(hand)) { 
			payout = 4; 
			yourHand = "You have a straight!";
		}
		else if (threeOfAKind(hand)) { 
			payout = 3; 
			yourHand = "You have three of a kind!"; 
		}
		else if (twoPairs(hand)) { 
			payout = 2; 
			yourHand = "You have two pairs!"; 
		}
		else if(singlePair(hand)) { 
			payout = 1;
			yourHand = "You have one pair!"; 
		}
		else {
			payout = 0; 
			yourHand = "You have no pair... better luck next time!"; 
		}

		p.winnings(payout); // passes through payout into winnings method
		
		System.out.println("You have " + p.getBankroll() + " tokens.");  

		return yourHand; // returns your winning, bankroll, and evaluated hand 
	}
	
	public void deal() { // another deal method 
		
		if(p.getHand().size() == 0) { // if hand is completely empty 
			for(int i = 0; i < 5; i++ ) { 
				p.addCard(cards.deal()); // adds until hand size is 5 
			}
		}
	}

	public void replaceCard(ArrayList<Card> hand) { // allows user to replace cards 

		System.out.println("How many cards would you like to replace (from 0-5)?"); 

		ArrayList<Integer> usedNumbers = new ArrayList<Integer>(); 
		// Array list for "used numbers" or indices already inputted by player

		Scanner input = new Scanner(System.in); 

		int replaceNumber = input.nextInt(); // player how many cards to exchange

		while(replaceNumber < 0 || replaceNumber > 5 ) {  // input validation 
			System.out.println("That's an invalid input. Try again"); 

			replaceNumber = input.nextInt(); 
		}

		for(int i = 0; i < replaceNumber; i++) {  // asks player which specific card
			
			System.out.println("Which card would you like to exchange?"); 
			System.out.println("Please type its position from 1 to 5!!!!");

			// player inputs the position of the card in the list, not its indice. 
			// From 1-5 only. 

			int position = input.nextInt(); 

			for(int j = 0; j < usedNumbers.size(); j++) { 
				if(usedNumbers.get(j) == position) { /* if position has been inputted before 
				the player will be reprompted */
					System.out.println("You can't exchange a card twice! Try again."); 
					position = input.nextInt(); 
				}
			}
			
			int index = (position-1); // calculates indice 

			p.removeCard(p.getCard(index)); // removes card at said indice 
			p.addCard(cards.deal(), index); // adds card at at the same indice
			// to maintain order 
			usedNumbers.add(position); // adds input to used numbers arraylist 
			}

		System.out.println("This is your new hand:"); 

		Collections.sort(p.getHand()); // sorts new hand 

		System.out.println(p.getHand()); // outputs new hand
	}

	public boolean singlePair(ArrayList<Card> hand) { 
		int pairNumber = 0; 

		for (int i = 0; i < 4; i++) { // loop to see if there's a single pair only
			if(hand.get(i).getRank() == hand.get(i+1).getRank()) { 		
				pairNumber++; 
			}
		}

		if(pairNumber == 1 && !(fullHouse(hand))) { 
			return true; /* checks to see if there's a full house first before 
			calling it a single pair */
		}

		return false; 
	} 

	public boolean twoPairs(ArrayList<Card> hand) { 
		int pairNumber = 0; 
		
		for (int i = 0; i < 4; i++) {
			if(hand.get(i).getRank() == hand.get(i+1).getRank()) { 	
				pairNumber++; 
			}
		}	
		if(pairNumber == 2) { // same exact procedure except checks for extra pair
			return true;
		}
		return false; 
	}
	
	public boolean threeOfAKind(ArrayList<Card> hand) { 
		int matches = 0; 
	
		for (Card i: hand) { 
			for(Card j: hand) {
				if(i.getRank() == j.getRank()) { 
					matches++; 
				}
			}
			
			if(matches == 3 && (!fullHouse(hand))) { 
				return true; /* again checks for full house first before 
				before calling it a three of a kind */
			}
			else { 
				matches = 0; 
			}
		}

		return false; 
	}
	
	public boolean straight(ArrayList<Card> hand) { 
		/* checks for pairs first then sees if the max rank minus 
		the minimum rank == 4 which means that there are no repeat 
		ranks and the ranks are in increasing order-- a straight */
		if ((!singlePair(hand) && !twoPairs(hand)) && 
			(hand.get(4).getRank() - hand.get(0).getRank() == 4)) { 	
				return true; 
			}
		if(hand.get(0).getRank() == 10 && 
				hand.get(1).getRank() == 11 && 
				hand.get(2).getRank() == 12 && 
				hand.get(3).getRank() == 13 && 
				hand.get(4).getRank() == 1) { // special case with Aces		
				return true; 
		}			
		return false; 
	}

	public boolean flush(ArrayList<Card> hand) { 
		int suitNumber = 0; 
		
		for (int i = 0; i < 4; i++) { // sees if all the suits match
			if(hand.get(i).getSuit() == hand.get(i+1).getSuit()) { 		
				suitNumber++; 
			}
		}
		
		if (suitNumber == 4) { // if 5 suits match, it's true 
			return true;
		}
		else { 
			suitNumber = 0;
		}
		return false; 
	}

	public boolean fullHouse(ArrayList<Card> hand) { 
		int rank1 = hand.get(0).getRank(); // if min and max are not equal 

		int sum1 = 1; 

		int rank2 = hand.get(4).getRank();

		int sum2 = 1; 

		for(int i = 1; i < 4; i++) { 
			if (hand.get(i).getRank() == rank1) { 	
				sum1++; // checks to see if there's more than one min rank
			}
			else if (hand.get(i).getRank() == rank2) { 
				sum2++; // checks to see if there's more than one max rank 
			}
		}

		if((sum1 + sum2) == 5) { // 2 max ranks and 3 min ranks == 5 
		// 2 min + 3 max = 5 
		// if those conditions validate, then it's a full house. 
			return true; 
		}

		return false; 
	}

	public boolean fourOfAKind(ArrayList<Card> hand) { 
		int matches = 0; // same as three of a kind except for 4 matches
		for (Card i: hand) { 
			
			for(Card j: hand) { 

				if(i.getRank() == j.getRank()) { 
					matches++; 
				}
			}

			if(matches == 4) { 
				return true; 
			}

			else { 
				matches = 0; 
			}
		}
		return false; 
	}

	public boolean straightFlush(ArrayList<Card> hand) { 
		int suitMatches = 0; 

		for(int i = 0; i < 4; i++) { 
			if(hand.get(i).getSuit() == hand.get(i+1).getSuit()) { 
				suitMatches++; 
			}
		} 

		if (suitMatches == 4 && (straight(hand))) { 
			return true; // if the suits match and it's a straight
			// it's a  straight flush
		}
		return false; 
	}

	public boolean royalFlush(ArrayList<Card> hand) { 
		if(flush(hand) == true) { 
			// unique case calls for long if statement 
			if(hand.get(0).getRank() == 1 && 
				hand.get(1).getRank() == 10 && 
				hand.get(2).getRank() == 11 && 
				hand.get(3).getRank() == 12 && 
				hand.get(4).getRank() == 13) { 
				return true; 
			}
		}
		return false; 
	}
}
