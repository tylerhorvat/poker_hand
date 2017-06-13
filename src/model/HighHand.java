/* CSC 335 Summer 2017 Project 1
 * Tyler Horvat
 * 
 * This enum holds all possible hands in a game
 * of poker
 * 
 */

package model;

public enum HighHand {
	HIGHCARD(1), ONEPAIR(2), TWOPAIR(3), THREEOFAKIND(4), STRAIGHT(5), 
	FLUSH(6), FULLHOUSE(7), FOUROFAKIND(8), STRAIGHTFLUSH(9), ROYALFLUSH(10);

	private int value;

	HighHand(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}