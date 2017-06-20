/*
 * Written by: Tyler Horvat
 * CSC 335 Project 2 6/20/17
 * This class represents a player in a poker game
 */

package model;

import java.util.ArrayList;
import java.util.Comparator;

public class Player {

	double balance;
	int playerNumber;
	HighHand bestHand;
	public PokerHand playerHand;
	ArrayList<Card> allCardOptions;
	public ArrayList<Card> playerCards = new ArrayList<>();
	
	Player(int playerNumber) {
		this.playerNumber = playerNumber;
		balance = 100.00;
		allCardOptions = new ArrayList<Card>(7);
		bestHand = HighHand.UNASSIGNED;
	}
	
	
	// return best hand
	public HighHand getBestHand() {
		return this.bestHand;
	}
	
	// set high hand
	public HighHand setHighHand(HighHand highand) {
		 return this.bestHand = highand;
	}
	
	//get player balance
	public double getBalance() {
		return balance;
	}

	//set player balance
	public void setBalance(double balance) {
		this.balance += balance;
	}

	// get player number
	public int getPlayerNumber() {
		return playerNumber;
	}

}
//sort player's hands in descending order, starting with
//highest ranking hand
class sortDescending implements Comparator<Player> {
	
	sortDescending() {
		
	}
	
	@Override
	public int compare(Player o1, Player o2) { return o2.getBestHand().getValue() - o1.getBestHand().getValue(); }
}