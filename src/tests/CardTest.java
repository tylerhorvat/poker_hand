package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Card;
import model.Rank;
import model.Suit;

public class CardTest {

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