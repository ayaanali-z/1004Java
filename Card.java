/* Ayaan Ali 
* aa4688 
* Card.java - This class creates a card. 
* It assigns it a rank and suit, overrides compareTo and toString, 
* and provides two accessor methods. 
*/

public class Card implements Comparable<Card>{
	
	private int suit;  
	private int rank;  
	
	public Card(int s, int r){
		suit = s; // constructs card 

		rank = r; 
 	}
	
	public int compareTo(Card c){ // overrode compareTo for Collections.sort 
		 if (this.rank == c.rank) { 
			return 0; 
		 }

		 else if (this.rank > c.rank) { 
			 return 1;  
		 }

		 else if (this.rank < c.rank) { 
			 return -1; 
		 }

		 return 0; 
	}
	
	public String toString(){ // overrode toString for presentation
        String s = " "; 

        String r = " ";

        if(suit == 1) {
			s = "Spades";
		}

        else if(suit == 2) {
			s = "Clubs";
		}

        else if(suit == 3) {
			s = "Hearts";
		}

        else if(suit == 4) {
			s = "Diamonds";
		}
		
		if(rank == 1) {
			r = "Ace";
		}

        else if(rank == 2) {
			r = "2";
		}

        else if(rank == 3) {
			r = "3";
		}

        else if(rank == 4) {
			r = "4";
		}

        else if(rank == 5) {
			r = "5";
		}

        else if(rank == 6) {
			r = "6";
		}

        else if(rank == 7) {
			r = "7";
		}

        else if(rank == 8) {
			r = "8";
		}

        else if(rank == 9) {
			r = "9";
		}

        else if(rank == 10) {
			r = "10";
		}

        else if(rank == 11) {
			r = "Jack";
		}

        else if(rank == 12) {
			r = "Queen";
		}

        else if(rank == 13) {
			r = "King";
		}
        
        String temp = r + " of " + s;

        return temp;
		
 	}
	 
	public int getSuit() { // accessor for suit 
		return suit; 

	}
	public int getRank() { // accessor for rank 
		return rank; 
	}
}
