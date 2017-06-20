/* Download from: https://sites.google.com/site/335summer17/quiz-4-debugging
 * Edited by: Tyler Horvat
 * CSC 335 Quiz 4
 */

package model;

import java.util.ArrayList;
import java.util.Collections;

public class PokerHand implements Comparable<PokerHand> {

    static final int HANDSIZE = 5;
	private ArrayList<Card> cards;
    private int highPair;
    private boolean aceHigh; //True if ace is high in a straight, false if ace low
    private HighHand highHand; //Stores Rank of hand

    // Construct one PokerHand
    // Precondition: All five cards are unique
    public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {
    	cards = new ArrayList<Card>();

		cards.add(c1);		
		cards.add(c2);
		cards.add(c3);		
		cards.add(c4);
		cards.add(c5);
		
		Collections.sort(cards);
		this.setHighHand();;
    }

    public HighHand getHighHand() {
		return highHand;
	}
    
    public int compareTo(PokerHand other){
    	    	
    	int thisHand = this.getHighHand().getValue();
    	int otherHand = other.getHighHand().getValue();
    	//System.out.println("This hand: " + thisHand + " Other hand: " + otherHand);
    	
    	if(thisHand != otherHand)
    		return (thisHand - otherHand)/(Math.abs(thisHand - otherHand));
    	
    	if(this.highHand == HighHand.ONEPAIR)
    		return this.compareOnePairHands(other);
    	
    	if(this.highHand == HighHand.TWOPAIR)
    	    return this.compareTwoPairHands(other);
    		
    	if(this.highHand == HighHand.THREEOFAKIND)
    		return this.compareThreeOfAKindHands(other);
    	
    	if(this.highHand == HighHand.STRAIGHT)
    		return this.compareStraightHands(other);
    	
    	if(this.highHand == HighHand.FLUSH)
    		return this.compareFlushHands(other);
    	
    	if(this.highHand == HighHand.FULLHOUSE)
    		return this.compareFullHouseHands(other);
    	
    	if(this.highHand == HighHand.FOUROFAKIND)
    		return this.compareFourOfAKindHands(other);
    	
    	if(this.highHand == HighHand.STRAIGHTFLUSH)
    		return this.compareStraightFlushHands(other);
    	
    	if(this.highHand == HighHand.ROYALFLUSH)
    		return 0;
    	
    	return this.compareHighCard(other);
    }
    
    public int compareHighCard(PokerHand other) {
    	int thisCard;
    	int otherCard;
    	
    	for(int i = HANDSIZE - 1; i >= 0; i--){
    		thisCard = this.getValue(i);
    		otherCard = other.getValue(i);
    		
    		if(thisCard != otherCard)
    			return (thisCard - otherCard)/(Math.abs(thisCard - otherCard));
    	}
    	
    	return 0;
    }

	private void setHighHand() {
	
		this.highHand = HighHand.HIGHCARD;
		
	    if(this.hasPair())
	    	this.highHand = HighHand.ONEPAIR;
		
	    if(this.hasTwoPair())
	    	this.highHand = HighHand.TWOPAIR;
	    
	    if(this.hasThreeOfAKind())
	    	this.highHand = HighHand.THREEOFAKIND;
	    
	    if(this.hasStraight())
	    	this.highHand = HighHand.STRAIGHT;
	    
	    if(this.hasFlush())
	    	this.highHand = HighHand.FLUSH;
	    
	    if(this.hasFullHouse())
	    	this.highHand = HighHand.FULLHOUSE;
	    
	    if(this.hasFourOfAKind())
	    	this.highHand = HighHand.FOUROFAKIND;
	    
	    if(this.hasStraighFlush())
	    	this.highHand = HighHand.STRAIGHTFLUSH;
	    
	    if(this.hasRoyalFlush())
	    	this.highHand = HighHand.ROYALFLUSH;
	    	
	}

	// Get the rank of any of the five cards
    // Precondition: index >= 0 and index <=4
    public int getValue(int index) {
        return cards.get(index).getValue();
    }
    
    public boolean hasRoyalFlush() {
    	
    	if(this.hasStraighFlush() && this.hasAce() && this.isAceHigh())
    		return true;
    	
    	return false;
    }
    
    public int compareStraightFlushHands(PokerHand other) {
    	int thisHighCard;
    	int otherHighCard;

        for(int i = 4; i >= 0; i--){
        	thisHighCard = this.getValue(i);
           	otherHighCard = other.getValue(i);
            	
           	if(thisHighCard != otherHighCard)
           		return (thisHighCard - otherHighCard)/Math.abs(thisHighCard - otherHighCard);
        }
    	return 0;
    }
    public boolean hasStraighFlush() {
    	
    	if(this.hasStraight() && this.hasFlush())
    		return true;
    	
    	return false;
    }
    
    public int compareFourOfAKindHands(PokerHand other) {
    	
    	int thisKind = this.getHighPair();
    	int otherKind = other.getHighPair();
    	
    	if(thisKind != otherKind)
    		return (thisKind - otherKind)/(Math.abs(thisKind - otherKind));
    	
    	int thisFifthCard = 0, otherFifthCard = 0;
    	
    	for(int i = 4; i >= 0; i--) {
    		thisFifthCard = this.getValue(i);
    		if(thisFifthCard != thisKind)
    			break;
    	}
    	
    	for(int j = 4; j >= 0; j--) {
    		otherFifthCard = other.getValue(j);
    		if(otherFifthCard != otherKind)
    			break;
    	}
    	
    	if(thisFifthCard != otherFifthCard)
    		return (thisFifthCard - otherFifthCard)/(Math.abs(thisFifthCard - otherFifthCard));	
    	
    	return 0;	
    }
    
    public boolean hasFourOfAKind() {
    	
    	if(this.getValue(0) == this.getValue(3)) {
    		this.setHighPair(this.getValue(0));
    		return true;
    	}
    	if(this.getValue(1) == this.getValue(4)) {
    		this.setHighPair(this.getValue(1));
    		return true;
    	}    	
    	return false;
    }
    
    public int compareFullHouseHands(PokerHand other){
    	int thisHighPair = this.getHighPair();
    	int otherHighPair = other.getHighPair();
    	
    	if(thisHighPair != otherHighPair)
    		return (thisHighPair - otherHighPair)/(Math.abs(thisHighPair - otherHighPair));
    	
    	int thisOtherPair = 0, otherOtherPair = 0;
    	
    	for (int i = 4; i >= 0; i--) {
    		thisOtherPair = this.getValue(i);
    		otherOtherPair = other.getValue(i);
    		if(thisOtherPair != otherOtherPair)
    			return (thisOtherPair - otherOtherPair)/(Math.abs(thisOtherPair - otherOtherPair));
    	}
    	
    	return 0;
    }
    public boolean hasFullHouse() {

    	if(this.hasThreeOfAKind()){
    		for(int i = 0; i < HANDSIZE - 1; i++){
    			if(this.getValue(i) == this.getValue(i + 1) && this.getValue(i) != this.getHighPair()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public int compareFlushHands(PokerHand other) {
    	
    	int thisHighCard;
    	int otherHighCard;

        for(int i = 4; i >= 0; i--){
        	thisHighCard = this.getValue(i);
           	otherHighCard = other.getValue(i);
            	
           	if(thisHighCard != otherHighCard)
           		return (thisHighCard - otherHighCard)/Math.abs(thisHighCard - otherHighCard);
        }
    	return 0;
    }
    
    public boolean hasFlush(){
    	Suit thisSuit = cards.get(0).getSuit();
    	
    	if(cards.get(1).getSuit() == thisSuit){
    		if(cards.get(2).getSuit() == thisSuit){
    			if(cards.get(3).getSuit() == thisSuit){
    				if(cards.get(4).getSuit() == thisSuit){
    		    		return true;
    		    	}
    	    	}
        	}
    	}
    	return false;
    }
    
    public int compareStraightHands(PokerHand other) {
    	
    	int thisHighCard;
    	int otherHighCard;
    		
    	if(!this.hasAce() && !other.hasAce()){
        	for(int i = 4; i >= 0; i--){
        		thisHighCard = this.getValue(i);
            	otherHighCard = other.getValue(i);
            	
            	if(thisHighCard != otherHighCard)
            		return (thisHighCard - otherHighCard)/Math.abs(thisHighCard - otherHighCard);
        	}
    	}
    	else if(this.hasAce() && !other.hasAce()) {
    		if(this.isAceHigh()) { // if this ace is high, this hand wins
    			return 1;
    		}
    		else{
    		    thisHighCard = this.getValue(3);
    		    otherHighCard = other.getValue(4);
    		    	
                return (thisHighCard - otherHighCard)/Math.abs(thisHighCard - otherHighCard);
    		}
    		
    	}
    	else if(!this.hasAce() && other.hasAce()) {
    		if(other.isAceHigh()) { // if other ace is high, this hand loses
    			return -1;
    		}
    		else{
    		    thisHighCard = this.getValue(4);
    		    otherHighCard = other.getValue(3);
    		    	
                return (thisHighCard - otherHighCard)/Math.abs(thisHighCard - otherHighCard);
    		}
    		
    	}
    	else if(this.hasAce() && other.hasAce()){
    		if(this.isAceHigh() && other.isAceHigh()) // tie
    			return 0; 
    		if(!this.isAceHigh() && !other.isAceHigh()) // tie
    			return 0;
    		if(this.isAceHigh() && !other.isAceHigh()) // this hand is winner
    			return 1;
    		if(!this.isAceHigh() && other.isAceHigh()) // other hand is winner
    			return -1;
    	}
		
    	return 0;
    }
    
    public boolean hasAce() {
    	
    	if(cards.get(4).getValue() == Rank.ACE.getValue())
    		return true;
    	
    	return false;
    }
    
    public boolean hasStraight() {
    	
    	// hand does not contain ace
    	if(!hasAce()){
    		if(cards.get(0).getValue() == cards.get(1).getValue() - 1) {
        		if(cards.get(1).getValue() == cards.get(2).getValue() - 1) {
        			if(cards.get(2).getValue() == cards.get(3).getValue() - 1) {
        				if(cards.get(3).getValue() == cards.get(4).getValue() - 1) {
        					return true;
        				}
        			}
        		}
        	}
    	}
    	// hand contains ace
    	else{
    		//ace is low
    		if(cards.get(0).getValue() == Rank.DEUCE.getValue()) {
    			if(cards.get(0).getValue() == cards.get(1).getValue() - 1) {
            		if(cards.get(1).getValue() == cards.get(2).getValue() - 1) {
            			if(cards.get(2).getValue() == cards.get(3).getValue() - 1) {
            				setAceHigh(false);
            				return true;
            			}
            		}
    			}
    		}
    		// ace is high
    		if(cards.get(0).getValue() == cards.get(1).getValue() - 1) {
        		if(cards.get(1).getValue() == cards.get(2).getValue() - 1) {
        			if(cards.get(2).getValue() == cards.get(3).getValue() - 1) {
        				if(cards.get(3).getValue() == cards.get(4).getValue() - 1) {
        					setAceHigh(true);
        					return true;
        				}
        			}
        		}
        	}
    	}
    	return false;
    }
  
    public boolean hasThreeOfAKind() {
	  
	    for(int i = 0; i < cards.size() - 2; i++){
		    if(cards.get(i).getValue() == cards.get(i + 1).getValue())
			    if(cards.get(i).getValue() == cards.get(i + 2).getValue()){
				    this.setHighPair(cards.get(i).getValue());
				    return true;
			    }
	    }
	  
	    return false;
    }
    
    public int compareThreeOfAKindHands(PokerHand other){
    	
    	int thisPair = this.getHighPair();
	    int otherPair = other.getHighPair();
	    
	    if(thisPair != otherPair)
	    	return (thisPair - otherPair)/(Math.abs(thisPair - otherPair));
	    
	    int thisHighCard = 0, otherHighCard = 0;
	    int i =4 , j = 4;
	    while(i >= 0) {
	    	thisHighCard = this.getValue(i);
	    	if(thisHighCard != thisPair){
	    		i--;
	    		break;
	    	}
	    	i--;
	    }
	    
	    while (j >= 0) {
	    	otherHighCard = other.getValue(j);
	    	if(otherHighCard != otherPair){
	    		j--;
	    		break;
	    	}
	    	j--;
	    }
	    
	    if(thisHighCard != otherHighCard)
	    	return (thisHighCard - otherHighCard)/(Math.abs(thisHighCard - otherHighCard));
	    
	    while(i >= 0) {
	    	thisHighCard = this.getValue(i);
	    	if(thisHighCard != thisPair){
	    		i--;
	    		break;
	    	}
	    }
	    
	    while (j >= 0) {
	    	otherHighCard = other.getValue(j);
	    	if(otherHighCard != otherPair){
	    		j--;
	    		break;
	    	}
	    }
	    
	    if(thisHighCard != otherHighCard)
	    	return (thisHighCard - otherHighCard)/(Math.abs(thisHighCard - otherHighCard));
	      	  
		return 0;
    }
  
    public boolean hasPair() {
	  
	    for(int i = 0; i < cards.size() - 1; i++){
		    if (cards.get(i).getValue() == cards.get(i + 1).getValue()){
			    this.setHighPair(cards.get(i).getValue());
			    return true;
		    }
	    }
	  
	    return false;
    }
  
    public int compareOnePairHands(PokerHand other){
	  
	    int thisPair = this.getHighPair();
	    int otherPair = other.getHighPair();
	  
	    if(thisPair != otherPair)
		    return (thisPair - otherPair)/(Math.abs(thisPair - otherPair));
	  
	    int thisHighCard = 0;
	    int otherHighCard = 0;
	    int i = HANDSIZE - 1, j = HANDSIZE - 1;
	    while(i >= 0){
		    if(this.getValue(i) != thisPair){
			    thisHighCard = this.getValue(i);
			    i--;
			    break;
		    }
		    i--;
	    }
	    while(j >= 0){
		    if(other.getValue(j) != otherPair){
			    otherHighCard = other.getValue(j);
			    j--;
			    break;
		    }
		    j--;
	    }
	  
	    if(thisHighCard != otherHighCard)
		    return (thisHighCard - otherHighCard)/(Math.abs(thisHighCard - otherHighCard));
	  
	    while(i >= 0){
		    if(this.getValue(i) != thisPair){
			    thisHighCard = this.getValue(i);
			    i--;
			    break;
		    }
		    i--;
	    }
	    while(j >= 0){
		    if(other.getValue(j) != otherPair){
			    otherHighCard = other.getValue(j);
			    j--;
			    break;
		    }
		    j--;
	    }
	  
	    if(thisHighCard != otherHighCard)
		    return (thisHighCard - otherHighCard)/(Math.abs(thisHighCard - otherHighCard));
	  
	    while(i >= 0){
		    if(this.getValue(i) != thisPair){
			    thisHighCard = this.getValue(i);
			    break;
		    }
		    i--;
	    }
	    while(j >= 0){
		    if(other.getValue(j) != otherPair){
			    otherHighCard = other.getValue(j);
			    break;
		    }
		    j--;
	    }
	  
	    if(thisHighCard != otherHighCard)
		    return (thisHighCard - otherHighCard)/(Math.abs(thisHighCard - otherHighCard));
	  
	    return 0;
    }

    // Return true if there are exactly two pairs in this hand
    // Precondition: cards is sorted.
    public boolean hasTwoPair()  {
        int numPairs = 0;
        for (int i = 0; i < HANDSIZE - 1; i++) {
            if (cards.get(i).getValue() == cards.get(i + 1).getValue())
                numPairs++;
        }
        if(numPairs == 2) {
        	for(int i = 4; i > 1; i--){
        		if(cards.get(i).getValue() == cards.get(i - 1).getValue()){
        			this.setHighPair(cards.get(i).getValue());
        			break;
        		}
        			
        		
        	}
        }
        return numPairs == 2;
    }

    /** 
    * @param other The PokerHand to be compared to this
    * 
    * @return Return 0 if this and other are tied (have two pair and the ranks of the
    * other fifth card value are equal.  Return +1 if this is a better hand than
    * other.  Or return -1 if other is a better hand than this.
    * 
    *  Precondition: this and other both have a valid two-pair hand with all unique Cards
    */
    public int compareTwoPairHands(PokerHand other) {
        int thisHighPairValue = this.getHighPair();
        int otherHighPairValue = other.getHighPair();
        
        // If the high pair differs, the larger wins
        if (thisHighPairValue != otherHighPairValue)
            return (thisHighPairValue - otherHighPairValue)/(Math.abs(thisHighPairValue - otherHighPairValue));
        else {
            int thisLowPairValue = getLowPair(this);
            int otherLowPairValue = getLowPair(other);
            // If the high pair is the same, and the low pair differs, larger wins
            if (thisLowPairValue != otherLowPairValue)
                return (thisLowPairValue - otherLowPairValue)/(Math.abs(thisLowPairValue - otherLowPairValue));
            else{
    	        int thisFifth = 0;
    	        int otherFifth = 0;
    	        for(int i = 0; i < HANDSIZE; i++){
    		        thisFifth = this.getValue(i);
    		        if(thisFifth != thisHighPairValue && thisFifth != thisLowPairValue)
    			        break;
    	        }
    	        for(int i = 0; i < HANDSIZE; i++){
    		        otherFifth = other.getValue(i);
    		        if(otherFifth != otherHighPairValue && otherFifth != otherLowPairValue)
    			        break;
    	        }
    	  
    	        if(thisFifth != otherFifth)
    		        return (thisFifth - otherFifth)/(Math.abs(thisFifth - otherFifth));
    	  
            }
            return 0;
        }
    }

    // Return the value of the lower pair in a two pair hand (or the pair in a Pair hand).
    // Precondition: ph is a valid PokerHand that is sorted
    private int getLowPair(PokerHand ph) {
        for (int i = 0; i < HANDSIZE - 1; i++) {
            if (ph.getValue(i) == ph.getValue(i + 1)) {
                return ph.getValue(i);
            }
        }
        return 0;
    }

    // Provide a textual version of this PokerHand
    public String toString() {
        String result = "";
        result += cards.get(0) + " ";
        result += cards.get(1) + " ";
        result += cards.get(2) + " ";
        result += cards.get(3) + " ";
        result += cards.get(4);
        return result;
    }

    public int getHighPair() {
	    return highPair;
    }

    private void setHighPair(int highPair) {
	    this.highPair = highPair;
    }

	public boolean isAceHigh() {
		return aceHigh;
	}

	public void setAceHigh(boolean aceHigh) {
		this.aceHigh = aceHigh;
	} 
	public ArrayList<Card> getCards() {
		return cards;
	}

}