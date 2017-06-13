/* CSC 335 Summer 2017 Project 1
 * Tyler Horvat
 * 
 * This class runs tests on class Card
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Card;
import model.Rank;
import model.Suit;

public class CardTest {

  //Find higher rank
  @Test
  public void testTwoCards() {
    Card C2 = new Card(Rank.DEUCE, Suit.CLUBS);
    Card CA = new Card(Rank.ACE, Suit.CLUBS);
    assertTrue(C2.compareTo(CA) < 0);
  }
  
  //Test same card
  @Test
  public void testTwoCards2() {
	  Card card = new Card(Rank.DEUCE, Suit.CLUBS);
	  Card same = new Card(Rank.DEUCE, Suit.CLUBS);
	  assertTrue(card.compareTo(same) == 0);
  }
  
  //Test same Rank
  @Test
  public void testTwoCards3() {
	  Card C2 = new Card(Rank.ACE, Suit.CLUBS);
	  Card D2 = new Card(Rank.ACE, Suit.DIAMONDS);
	  assertTrue(C2.compareTo(D2) == 0);
  }
  
  //Test same suit, different Rank
  @Test
  public void testTwoCards4() {
	  Card C3 = new Card(Rank.THREE, Suit.CLUBS);
	  Card C2 = new Card(Rank.DEUCE, Suit.CLUBS);
	  assertTrue(C3.compareTo(C2) > 0);
  }
  
  @Test
  public void testTwoCards5() {
	  Card H4 = new Card(Rank.FOUR, Suit.HEARTS);
	  Card S5 = new Card(Rank.FIVE, Suit.SPADES);
	  assertTrue(H4.compareTo(S5) < 0);
  }
  
  @Test
  public void testTwoCards6() {
	  Card S6 = new Card(Rank.SIX, Suit.SPADES);
	  Card SK = new Card(Rank.KING, Suit.SPADES);
	  assertTrue(SK.compareTo(S6) > 0);
  }
  
  @Test
  public void testTwoCards7() {
	  Card DQ = new Card(Rank.QUEEN, Suit.DIAMONDS);
	  Card SQ = new Card(Rank.QUEEN, Suit.SPADES);
	  assertTrue(DQ.compareTo(SQ) == 0);
  }
  
  @Test
  public void testGetters() {
    Card c1 = new Card(Rank.DEUCE, Suit.CLUBS);
    Card c2 = new Card(Rank.DEUCE, Suit.DIAMONDS);
    Card c3 = new Card(Rank.THREE, Suit.CLUBS);
    Card c4 = new Card(Rank.THREE, Suit.DIAMONDS);

    assertFalse(c1.equals(null));
    
    assertEquals(Rank.DEUCE, c1.getRank());
    assertEquals(Suit.CLUBS, c1.getSuit());
    assertEquals(2, c1.getValue());

    assertTrue(Rank.DEUCE == c2.getRank());
    assertTrue(Suit.DIAMONDS == c2.getSuit());
    assertEquals(2, c1.getValue());

    assertTrue(Rank.THREE == c3.getRank());
    assertTrue(Suit.CLUBS == c3.getSuit());
    assertEquals(3, c3.getValue());

    assertTrue(Rank.THREE == c4.getRank());
    assertTrue(Suit.DIAMONDS == c4.getSuit());
    assertEquals(3, c4.getValue());
  }

  @Test
  public void testCompareTo() {
    Card c1 = new Card(Rank.DEUCE, Suit.CLUBS);
    Card c2 = new Card(Rank.THREE, Suit.DIAMONDS);

    assertTrue(c1.compareTo(c2) < 0);
    assertTrue(c2.compareTo(c1) > 0);
    assertTrue(c1.compareTo(c1) == 0);
  }

  @Test
  public void testEquals() {
    Card c1 = new Card(Rank.DEUCE, Suit.CLUBS);
    Card c2 = new Card(Rank.THREE, Suit.DIAMONDS);

    Card c3 = new Card(Rank.DEUCE, Suit.CLUBS);
    Card c4 = new Card(Rank.THREE, Suit.DIAMONDS);

    assertFalse(c1.equals(c2));
    assertTrue(c1.equals(c3));
    assertTrue(c2.equals(c4));
  }
  
  @Test
  public void testEqualsOnTheHighSide() {
    Card c1 = new Card(Rank.ACE, Suit.CLUBS);
    Card c2 = new Card(Rank.KING, Suit.DIAMONDS);

    Card c3 = new Card(Rank.ACE, Suit.CLUBS);
    Card c4 = new Card(Rank.KING, Suit.DIAMONDS);

    assertFalse(c1.equals(c2));
    assertTrue(c1.equals(c3));
    assertTrue(c2.equals(c4));
  }
 
  @Test
  public void testCardWithAllValueToString() {
    Card c1 = new Card(Rank.ACE, Suit.CLUBS);
    Card c2 = new Card(Rank.KING, Suit.CLUBS);
    Card c3 = new Card(Rank.QUEEN, Suit.CLUBS);
    Card c4 = new Card(Rank.JACK, Suit.CLUBS);
    Card c5 = new Card(Rank.TEN, Suit.CLUBS);
    
    assertTrue(c1.toString().contains("A"));
    assertTrue(c2.toString().contains("K"));
    assertTrue(c3.toString().contains("Q"));
    assertTrue(c4.toString().contains("J"));
    assertTrue(c5.toString().contains("10"));
  }
}