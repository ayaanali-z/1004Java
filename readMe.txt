Ayaan Ali 
aa4688 
readMe - this summarizes the program 

This program began with the Card class. My cards are numbered from 1-13 (Ace to King) and 1-4 
(Spades, Clubs, Hearts, Diamonds). The Card class includes two overrode methods- compareTo and toString. 
These two methods are the bedrock for this entire program. There are also two accessors to get the suit and 
rank of the card. 

The second class is Deck. This creates a deck of 52 cards. There's a method to shuffle the cards called shuffle(). 
That method randomly picks two cards in the deck and switches their position. This goes on for 999 more times. 
Deck also includes a deal method which returns the card sitting at the top of the deck (index 0) and then increments 
top to the next index. 

The third class is player which creates the player's bankroll, bets, and hand. It includes a removeCard method to remove 
a card from the hand array list. It also includes an addCard method that's been overloaded in order to add a Card to a 
specific index in the hand. This comes in handy when exchanging cards. The bets method subtracts its argument from the 
bankroll. Input validation is done in Game. Winnings multiplies the payout passed through the method with bets and then 
adds that to the bankroll. getCard. getHand, and getBankroll are all accessor methods. 

The last and most important class in this entire program is Game. It first has two constructors. One allows the user 
to pass through command line arguments into a test hand to test specific evaluations. The other just randomly generates 
a hand for the player. The play method first asks for the betting amount and validates it subsequently. The deck is 
then shuffled and a hand is dealt to the player. The player now has the option to exchange cards. The replaceCard method 
is used here to great benefit. It first prompts the player for how many cards they wish to exchange. If they pick 0 then
the hand is evaluated instantly. If they pick between 1-5, then they're then prompted to enter in the specific position 
where the card they desire to exchange is. This input is a number. If the player reenters the same position twice, the 
for loop will validate it by checking it against an array list of "used numbers" that gets updated with every new input. 
If a number that's been inputted already is inputted again, the player is reprompted for a number that isn't in the arraylist. 
The replace method is only used once during play to ensure that the player doesn't constantly exchange. Then, after the 
replaced hand is sorted, it is evaluated using the checkHand method. This method makes use of 9 additional boolean methods for 
each type of poker hand. If the boolean is true, then the hand is true and a payout is assigned. That payout is then 
passed through the winnings method to calculate the token amount won. Afterward, the player is informed of what their hand 
evaluated to, the amount of tokens they won, and their updated bankroll. The game then ends. 