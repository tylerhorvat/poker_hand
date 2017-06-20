package model;

/*
 * Written by: Tyler Horvat
 * CSC 335 Project 2 6/20/17
 * This class represents a deck of cards, and will deal
 * cards when called. Also able to shuffle cards
 */

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	final static int COMMUNITYHANDSIZE = 5;
	private ArrayList<Card> cards;
	int topOfDeck;
	////private ArrayList<Card> communityHand;
	
	Deck() {
		 //communityHand = new ArrayList<Card>();
		 cards = new ArrayList<Card>();
		 openDeckOfCards();
		 shuffleDeck();
		 topOfDeck = 0;
	}
	
	//simulates opening a new deck of cards by adding one
	//card into an array list at a time
	private void openDeckOfCards() {
		for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
              cards.add(new Card(rank, suit));
           } 
       }
	}
	
	//shuffles the deck
	void shuffleDeck() {
		Collections.shuffle(cards);
	}
	
	//deals a card
	public Card dealCard() {
		return cards.get(topOfDeck++);
	}
	/*
	//unused
	public ArrayList<Card> dealCommunityHand() {
		
		for(int i = 0; i < COMMUNITYHANDSIZE; i++) {
			communityHand.add(dealCard());
		}
		return communityHand;
		
	}
	//unused
	public ArrayList<Card> dealPlayer2Cards(ArrayList<Card> player2Cards) {
		
		player2Cards.add(dealCard());
		player2Cards.add(dealCard());
		
		return (ArrayList<Card>) player2Cards;
	}*/
	
	
}
