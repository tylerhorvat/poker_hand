/* CSC 335 Summer 2017 Project 1
 * Tyler Horvat
 * 
 * This class is used to throw a RunTimeException
 * when a duplicate card is found in a hand or when
 * comparing two hands in a game of poker 
 */

package model;

public class DuplicateCardException extends RuntimeException {
	
	public DuplicateCardException () {
		System.out.println("Looks like you have a trick deck! You can't fool me! Try again with a legal deck!");
	}
}