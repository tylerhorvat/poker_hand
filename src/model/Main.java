/*
 * Written by: Tyler Horvat
 * CSC 335 Project 2 6/20/2017
 * This class is the main class. This is where the game is run, and the
 * user may interact with the system.
 * note to grader: many methods were moved to main as a way of debugging...
 * i just happened to run out of time to reorganize everything to give
 * the program a better flow.
 */


package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many players? ");
		int numberOfPlayers = scanner.nextInt();
		System.out.println();
		String playAgain = null;
		
		ArrayList<Player> players = new ArrayList<>();
		ArrayList<Card> communityHand = new ArrayList<>(5);
		setUpPlayers(numberOfPlayers, players);
		
		do {
			
			playGame(numberOfPlayers, players, communityHand);
			
			System.out.print("Play another game? <y or n> ");
			playAgain = scanner.next();
			System.out.println();
			//if(playAgain.equals("n"))
				//break;
					
		}while(playAgain.equals("y"));
		
		scanner.close();

}

	//this method adds players to an arraylist, depending on the number of
	//players entered by the user
    static void setUpPlayers(int numberOfPlayers, ArrayList<Player> players) {	
	    for(int i = 0; i < numberOfPlayers; i++) {
		    players.add(new Player(i +1));
	    }	
    }
    

    //this player plays the game by calling many helper methods, as well as printing
    //game results
	public static void playGame(int numberOfPlayers, ArrayList<Player> players, ArrayList<Card> communityHand) {
		Deck deck = new Deck ();
		double pot = 2.00 * numberOfPlayers;
		for(int i = 0; i < 5; i++) {
			communityHand.add(i, deck.dealCard());
		}

		double share;
		
		for(int i = 0; i < players.size(); i++){
			players.get(i).setBalance(-2.00);
			players.get(i).bestHand = HighHand.UNASSIGNED;
			players.get(i).playerCards.add(0, deck.dealCard());
			players.get(i).playerCards.add(1, deck.dealCard());
		}
		
		setBestHand(communityHand, players);
		
		NumberFormat formatter = DecimalFormat.getCurrencyInstance();
		
		System.out.println("Community Cards: " + communityHand.get(0).toString() + " " + communityHand.get(1).toString() + " " + communityHand.get(2).toString() +
				" " + communityHand.get(3).toString() + " " + communityHand.get(4).toString());
		System.out.println("++++++++++++++++++++++++++++++++++++");
		
		for(int i = 0; i < players.size(); i++) {
			
			System.out.println("Player " + players.get(i).getPlayerNumber() + ": " + formatter.format(players.get(i).getBalance()) + " - " + 
					players.get(i).playerCards.get(0).toString() + " " +players.get(i).playerCards.get(1).toString());
			
			System.out.println("\tBest Hand: " + players.get(i).playerHand.toString() + "\t" + players.get(i).bestHand);
			System.out.println();
		}
		ArrayList<Player> sortedPlayers = new ArrayList<>(players);
		
		Collections.sort(sortedPlayers, new sortDescending());
		int numberOfTies = countTies(sortedPlayers);
		
		share = pot/numberOfTies;
		
		if(numberOfTies > 1) {
			for(int i = 0; i < numberOfTies; i++)
				sortedPlayers.get(i).setBalance(share);
		}
		else
			sortedPlayers.get(0).setBalance(share);
		
		if(numberOfTies > 1) {
			System.out.println("Winning Hands (tie)");
			System.out.println("++++++++++++++++++++++++++++++++++++");
			
			for(int i = 0; i < numberOfTies; i++) {
				System.out.println(sortedPlayers.get(i).playerHand.toString() + "  " + sortedPlayers.get(i).bestHand + "  Player " + 
						sortedPlayers.get(i).getPlayerNumber() + "  " + formatter.format(sortedPlayers.get(i).getBalance()));
			}
			System.out.println();
		}
		else {
			System.out.println("Winner: Player " + sortedPlayers.get(0).getPlayerNumber() + " " + formatter.format(sortedPlayers.get(0).getBalance()));
			System.out.println("++++++++++++++++++++++++++++++++++++");
			System.out.println("\t" + sortedPlayers.get(0).bestHand + " " + sortedPlayers.get(0).playerHand.toString());
			System.out.println();
		}
	}
	
	//this helper method counts the number of ties among players
	static int countTies(ArrayList<Player> sortedPlayers) {
		
		int ties = 1;
		for(int i = 0; i < sortedPlayers.size() - 1; i++) {
			if(sortedPlayers.get(i).getBestHand().getValue() != sortedPlayers.get(i + 1).getBestHand().getValue())
				break;
			else {
				if(sortedPlayers.get(i).playerHand.compareTo(sortedPlayers.get(i + 1).playerHand) == 0)
					ties++;
			}
		}
		
		return ties;
	 }
	
// this method joins all possible cards into one list, and calls helper methods to find the best possible method	
public static void setBestHand(ArrayList<Card> communityHand, ArrayList<Player> players) { //change back to pokerHand
		
		ArrayList<Card> allCardOptions = new ArrayList<Card>();
		for(int p =0; p < 7; p++) {
			allCardOptions.add(null);
		}
	    for(int i = 0; i < players.size(); i++) {
	    	
	    	for(int k = 0; k < 2; k++) {
	    		allCardOptions.set(k, players.get(i).playerCards.get(k));
	    	}
	    	for(int j = 2; j < 7; j++) {
	    		allCardOptions.set(j, communityHand.get(j - 2));
	    	}
			printCombination(allCardOptions, 7, 5, players, i);
	    }	
	}

    //initializes array to temporarily store data, as well as call helper method
	private static void printCombination(ArrayList<Card> array, int n, int r, ArrayList<Player> players, int i) {
		ArrayList<Card> data = new ArrayList<>(n);
		for(int j = 0; j < 7; j++) {
			data.add(null);
		}
		combinationUtil(array, data, 0, n - 1, 0, r, players, i);
	}
	
	//this method recursively determines the best possible hand for the player
	private static void combinationUtil(ArrayList<Card> array, ArrayList<Card> data, int start, int end, int index, int r, ArrayList<Player> players, int i) {
		
		if (index == r) {
			
			Player player = players.get(i);
			
			PokerHand hand = new PokerHand(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4));
			
			if(player.bestHand == HighHand.UNASSIGNED) {
				player.setHighHand(hand.getHighHand());
				player.playerHand = hand;
			}
				
			else {
				if(player.playerHand.compareTo(hand) < 0) {
					player.setHighHand(hand.getHighHand());
					player.playerHand = hand;
				}		
			}
			
			players.set(i, player);
			return;		
		}
		
		for (int j = start; j <= end && end - j + 1 >= r - index; j++) {
			data.set(index, array.get(j));
			combinationUtil(array, data, j + 1, end, index + 1, r, players, i);
		}
	}
}
	


