/* Ayaan Ali 
* aa4688 
* Deck.java - This class creates a deck of cards. 
* It constructs a deck and provides a deal method and 
* shuffle method to be used in the game class. I also 
* coded a returnDeck method to test the Deck class. 
*/

public class Deck {
	
	private Card[] cards; 
	private int top; 
	private int shuffleNumber; // number of shuffles


	
	public Deck(){ 
		int cardNumber = 0; 
		top = 0; 
		cards = new Card[52]; // array of 52 cards 
			
		for(int suit = 1; suit < 5; suit++) { // for each suit 

			for (int rank = 1; rank < 14; rank++) { // creates a card from 1-13 
				cards[cardNumber] = new Card(suit, rank);  

				cardNumber++; 
			}
		}
	}
	
	public void shuffle(){ // shuffles the deck 1k times 
		while(shuffleNumber < 1000) { 
			int a = (int)(Math.random()*52); // random number from 0 to 52 
			int b = (int)(Math.random()*52); 

			Card temp = cards[a]; // temp card variable 
			cards[a] = cards[b]; // card a is assigned to b 
			cards[b] = temp; // b takes card a's spot 

			shuffleNumber++; 
		}
	}
	
	public Card deal(){
		return cards[top++]; // deals top card of the deck while incrementing top
	}

	public void returnDeck() { 
		for(int i = 0; i < cards.length; i++) { 
			System.out.println(cards[i]); // prints every card in the deck
		}
	}
}
