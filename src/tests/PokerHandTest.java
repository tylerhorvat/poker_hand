/* CSC 335 Summer 2017 Project 1
 * Tyler Horvat
 * 
 * This class runs several tests on class PokerHand
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Card;
import model.DuplicateCardException;
import model.PokerHand;
import model.Rank;
import model.Suit;

/**
 * This unit test only has tests for the two methods in PokerHand
 * 
 * 1. boolean hasTwoPair()
 * 
 * 2. int compare(PokerHand other)
 * 
 */
public class PokerHandTest {
	
	@Test
	public void straightTestAces() {
		PokerHand a = new PokerHand(CA, S2, D3, S4, D5);
		PokerHand b = new PokerHand(D10, SJ, DQ, CK, HA);
		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(a));
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException11() {
		PokerHand a = new PokerHand(C2, C3, S5, S8, S8);
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException10() {
		PokerHand a = new PokerHand(C2, C3, S5, S8, S5);
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException9() {
		PokerHand a = new PokerHand(C2, C3, S5, S5, S10);
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException8() {
		PokerHand a = new PokerHand(C2, C3, S5, S8, C3);
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException7() {
		PokerHand a = new PokerHand(C2, C3, S5, C3, S10);
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException6() {
		PokerHand a = new PokerHand(C2, C3, C3, S8, S10);
	}
	
	@Test
	public void threeOfAKind() {
		PokerHand a = new PokerHand(C5, D5, H5, S3, C7);
		PokerHand b = new PokerHand(C8, D8, S8, DK, D4);
		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(a));
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException5() {
		PokerHand a = new PokerHand(C2, C3, S5, S8, C2);
		//PokerHand b = new PokerHand(S2, S4, S7, C8, H9);
		//assertEquals(1, a.compareTo(b));
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException4() {
		PokerHand a = new PokerHand(C2, C3, S5, C2, H10);
		//PokerHand b = new PokerHand(S2, S4, S7, C8, H9);
		//assertEquals(1, a.compareTo(b));
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException3() {
		PokerHand a = new PokerHand(C2, C3, C2, S8, H10);
		//PokerHand b = new PokerHand(S2, S4, S7, C8, H9);
		//assertEquals(1, a.compareTo(b));
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException2() {
		PokerHand a = new PokerHand(C2, C2, S5, S8, H10);
		//PokerHand b = new PokerHand(S2, S4, S7, C8, H9);
		//assertEquals(1, a.compareTo(b));
	}
	
	@Test(expected = DuplicateCardException.class)
	public void duplicateException() {
		PokerHand a = new PokerHand(C2, C3, S5, S8, H10);
		PokerHand b = new PokerHand(C2, S4, S7, C8, H9);
		assertEquals(1, a.compareTo(b));
	}
	
	@Test(expected = DuplicateCardException.class)
	public void trickDeck() {
		PokerHand a = new PokerHand(CA, C2, C3, C4, C5);
		PokerHand b = new PokerHand(CA, D2, D3, D4, D5);
		assertEquals(0, a.compareTo(b));
	}
	
	@Test
	  public void testSuitEnum() {
	    String result = "";
	    for (Suit suit : Suit.values())
	      result += suit + " ";
	    assertEquals("CLUBS DIAMONDS HEARTS SPADES", result.trim());
	  }

	  @Test
	  public void testRankEnum() {
	    String result = "";
	    for (Rank rank : Rank.values())
	      result += rank + " ";
	    assertEquals(
	        "DEUCE THREE FOUR FIVE SIX SEVEN EIGHT NINE TEN JACK QUEEN KING ACE",
	        result.trim());
	  }

	  // Do not allow duplicate cards, throw an exception
	  @Test(expected = DuplicateCardException.class)
	  public void tryToAddTheSameCardTwiceA() {
	    new PokerHand(C2, C3, C4, C5, C5);
	  }

	  @Test(expected = DuplicateCardException.class)
	  public void testPair2() {
	    PokerHand a = new PokerHand(H3, CA, D4, H6, DA);
	    a.toString();
	    PokerHand b = new PokerHand(H3, C5, HA, SA, C6);
	    assertTrue(a.compareTo(b) < 0);
	  }

	  @Test
	  public void testTwoPairWhenOnePairIsEqual() {
	    PokerHand a = new PokerHand(C4, HK, D4, H3, DK);
	    PokerHand b = new PokerHand(H4, C10, CA, DA, S4);
	    assertTrue(a.compareTo(b) < 0);
	    assertTrue(b.compareTo(a) > 0);
	  }

	  @Test
	  public void testAceLowStraight() {
	    PokerHand a = new PokerHand(CA, C2, C3, C4, H5);
	    PokerHand b = new PokerHand(D2, D3, D4, D5, H6);
	    boolean answer = a.compareTo(b) < 0;
	    assertTrue(answer);
	  }
	  
	@Test
	public void compareToSameHighHand8() {
		PokerHand a = new PokerHand(C2, C6, C7, C10, CJ);
		PokerHand b = new PokerHand(D5, D6, D8, D10, DJ);
		PokerHand c = new PokerHand(S3, S9, S10, SJ, SQ);
		PokerHand d = new PokerHand(S4, S6, S8, S10, SJ);
		assertEquals(-1, a.compareTo(b));
		assertEquals(-1, a.compareTo(c));
		assertEquals(1, b.compareTo(a));
		assertEquals(-1, b.compareTo(c));
		assertEquals(1, c.compareTo(a));
		assertEquals(1, c.compareTo(b));
		assertEquals(1, b.compareTo(d));
		assertEquals(-1, d.compareTo(b));
	}
	  
	@Test
	public void compareToFullHouseTest() {
		PokerHand a = new PokerHand(C6, D6, H6, H7, D7);
		PokerHand b = new PokerHand(D3, C3, S3, S7, C7);
		PokerHand c = new PokerHand(C4, D4, H4, HK, HA);
		assertEquals(1, a.compareTo(b));
		assertEquals(1, a.compareTo(c));
		assertEquals(-1, b.compareTo(a));
		assertEquals(1, b.compareTo(c));
		assertEquals(-1, c.compareTo(a));
		assertEquals(-1, c.compareTo(b));
	}
	  
	@Test
	public void compareToSameHighHand7() {
		PokerHand a = new PokerHand(C4, D4, H4, S4, HK);
		PokerHand b = new PokerHand(HJ, DJ, SJ, CJ, CA);
		PokerHand c = new PokerHand(S8, D8, C8, H8, SQ);
		assertEquals(-1, a.compareTo(b));
		assertEquals(-1, a.compareTo(c));
		assertEquals(1, b.compareTo(a));
		assertEquals(1, b.compareTo(c));
		assertEquals(1, c.compareTo(a));
		assertEquals(-1, c.compareTo(b));
	}
	  
    @Test
    public void compareToSameHighHand6() {
    	PokerHand a = new PokerHand(C3, C4, C5, C6, C7);
    	PokerHand b = new PokerHand(H6, H7, H8, H9, H10);
    	PokerHand c = new PokerHand(D10, DJ, DQ, DK, DA);
    	assertEquals(-1, a.compareTo(b));
    	assertEquals(1, b.compareTo(a));
    	assertEquals(1, c.compareTo(a));
    	assertEquals(-1, b.compareTo(c));
    	assertEquals(1, c.compareTo(b));
    	assertEquals(-1, a.compareTo(c));
    }
	  
	@Test
	public void compareToSameHighHand5() {
		PokerHand a = new PokerHand(CA, CK, CQ, CJ, C10);
		PokerHand b = new PokerHand(DA, DK, DQ, DJ, D10);
		assertEquals(0, a.compareTo(b));
		assertEquals(0, b.compareTo(a));
	}
	  
	@Test
	public void compareToSameHighHand4() {
		PokerHand a = new PokerHand(S2, C2, D4, H7, DK);
		PokerHand b = new PokerHand(D6, C6, H8, S9, HJ);
		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(a));
		PokerHand c = new PokerHand(D5, H5, S9, H10, SJ);
		PokerHand d = new PokerHand(S5, C5, C8, S2, CJ);
		assertEquals(1, c.compareTo(d));
		assertEquals(-1, d.compareTo(c));
	}
	
	@Test
	public void compareToSameHighHand3() {
		PokerHand a = new PokerHand(S2, D4, C4, C9, D9);
		PokerHand b = new PokerHand(H5, D7, C7, S8, H8);
		assertEquals(1, a.compareTo(b));
		assertEquals(-1, b.compareTo(a));
	}
	
	
	@Test
	public void compareToSameHighHand2() {
		PokerHand a = new PokerHand(D4, C4, C9, C10, S2);
		PokerHand b = new PokerHand(D7, C7, S8, S9, H5);
		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(a));
	}
	
	
	@Test
	public void compareToSameHighHand() {
		PokerHand a = new PokerHand(D4, H8, C9, C10, S2);
		PokerHand b = new PokerHand(D7, C3, S8, S9, H5);
		assertEquals(1, a.compareTo(b));
		assertEquals(-1, b.compareTo(a));
	}
	
	@Test 
	public void compareToDifferentHighHand3() {
		PokerHand a = new PokerHand(H2, H5, H7, HJ, HK);
		PokerHand b = new PokerHand(D4, S4, H4, D5, S5);
		PokerHand c = new PokerHand(H8, D8, S8, C8, HQ);
		PokerHand d = new PokerHand(CA, C2, C3, C4, C5);
		assertEquals(-1, a.compareTo(b));
		assertEquals(-1, a.compareTo(c));
		assertEquals(-1, a.compareTo(d));
		assertEquals(1, b.compareTo(a));
		assertEquals(-1, b.compareTo(c));
		assertEquals(-1, b.compareTo(d));
		assertEquals(1, c.compareTo(a));
		assertEquals(1, c.compareTo(b));
		assertEquals(-1, c.compareTo(d));
		assertEquals(1, d.compareTo(a));
		assertEquals(1, d.compareTo(b));
		assertEquals(1, d.compareTo(c));
	}
	
	@Test
	public void compareToDifferentHighHand2() {
		PokerHand a = new PokerHand(C2, D2, C3, D3, C4);
		PokerHand b = new PokerHand(D4, S4, H4, D5, D6);
		PokerHand c = new PokerHand(H6, D7, S8, S9, C5);
		PokerHand d = new PokerHand(H2, H5, H7, HJ, HK);
		assertEquals(-1, a.compareTo(b));
		assertEquals(-1, a.compareTo(c));
		assertEquals(-1, a.compareTo(d));
		assertEquals(1, b.compareTo(a));
		assertEquals(-1, b.compareTo(c));
		assertEquals(-1, b.compareTo(d));
		assertEquals(1, c.compareTo(a));
		assertEquals(1, c.compareTo(b));
		assertEquals(-1, c.compareTo(d));
		assertEquals(1, d.compareTo(a));
		assertEquals(1, d.compareTo(b));
		assertEquals(1, d.compareTo(c));
	}
	
	@Test
	public void compareToDifferentHighHand() {
		PokerHand a = new PokerHand(C5, D3, S7, H2, SK);
		PokerHand b = new PokerHand(S5, D5, D10, H9, H7);
		PokerHand c = new PokerHand(C6, D6, H8, S8, D9);
		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(a));
		assertEquals(-1, b.compareTo(c));
		assertEquals(1, c.compareTo(b));
		assertEquals(-1, a.compareTo(c));
		assertEquals(1, c.compareTo(a));
	}
	
	@Test
	public void compareHighCard() {
		PokerHand a = new PokerHand(C2, S4, D6, H9, H10);
		PokerHand b = new PokerHand(C3, H5, D8, SJ, SK);
		PokerHand c = new PokerHand(D3, C5, S8, DJ, CK);
		assertEquals(-1, a.compareHighCard(b));
		assertEquals(1, b.compareHighCard(a));
		assertEquals(0, b.compareHighCard(c));
		assertEquals(0, c.compareHighCard(b));
	}
	
	@Test
	public void getHighHand2() {
		PokerHand a = new PokerHand(CA, C2, C6, C8, C10);
		PokerHand b = new PokerHand(CA, HA, DA, HK, CK);
		PokerHand c = new PokerHand(CA, HA, DA, SA, HK);
		PokerHand d = new PokerHand(H2, H3, H4, H5, H6);
		PokerHand e = new PokerHand(CA, CK, CQ, CJ, C10);
		assertEquals(6, a.getHighHand().getValue());
		assertEquals(7, b.getHighHand().getValue());
		assertEquals(8, c.getHighHand().getValue());
		assertEquals(9, d.getHighHand().getValue());
		assertEquals(10, e.getHighHand().getValue());
	}
	
	@Test
	public void getHighHand() {
		PokerHand a = new PokerHand(CA, S4, H7, HK, H8);
		PokerHand b = new PokerHand(CA, HA, H7, HK, C8);
		PokerHand c = new PokerHand(CA, HA, H7, D7, HK);
		PokerHand d = new PokerHand(CA, HA, DA, D7, HK);
		PokerHand e = new PokerHand(CA, S2, H3, D4, C5);
		assertEquals(1, a.getHighHand().getValue());
		assertEquals(2, b.getHighHand().getValue());
		assertEquals(3, c.getHighHand().getValue());
		assertEquals(4, d.getHighHand().getValue());
		assertEquals(5, e.getHighHand().getValue());
	}
	
	@Test
	public void hasRoyalFlush() {
		PokerHand a = new PokerHand(HA, H2, H3, H4, H5);
		PokerHand b = new PokerHand(H10, HJ, HQ, HK, HA);
		PokerHand c = new PokerHand(C3, C5, C6, C7, C8);
		assertTrue(a.hasStraighFlush());
		assertTrue(b.hasStraighFlush());
		assertFalse(c.hasStraighFlush());
		assertFalse(a.hasRoyalFlush());
		assertTrue(b.hasRoyalFlush());
		assertFalse(c.hasRoyalFlush());
		assertEquals(1, b.compareStraightFlushHands(a));
		assertEquals(-1, a.compareStraightFlushHands(b));
	}
	
	@Test
	public void compareStraightFlushHands() {
		PokerHand a = new PokerHand(H4, H5, H6, H7, H8);
		PokerHand b = new PokerHand(C4, C5, C6, C7, C8);
		PokerHand c = new PokerHand(D8, D9, D10, DJ, DQ);
		assertTrue(a.hasStraighFlush());
		assertTrue(b.hasStraighFlush());
		assertTrue(c.hasStraighFlush());
		assertEquals(0, a.compareStraightFlushHands(b));
		assertEquals(0, b.compareStraightFlushHands(a));
		assertEquals(-1, a.compareStraightFlushHands(c));
		assertEquals(1, c.compareStraightFlushHands(a));
		assertEquals(-1, b.compareStraightFlushHands(c));
		assertEquals(1, c.compareStraightFlushHands(b));
	}
	
	@Test
	public void hasStraightFlush() {
		PokerHand a = new PokerHand(H4, H5, H6, H7, H8);
		PokerHand b = new PokerHand(C7, C8, C9, C10, DJ);
		PokerHand c = new PokerHand(D2, D4, D6, D8, D10);
		assertTrue(a.hasStraighFlush());
		assertFalse(b.hasStraighFlush());
		assertFalse(c.hasStraighFlush());
	}
	
	@Test
	public void hasFourOfAKind() {
		PokerHand a = new PokerHand(H4, C4, S4, D4, S6);
		PokerHand b = new PokerHand(D6, CJ, DJ, SJ, HJ);
		PokerHand c = new PokerHand(D5, H5, C7, D7, S9);
		assertTrue(a.hasFourOfAKind());
		assertTrue(b.hasFourOfAKind());
		assertFalse(c.hasFourOfAKind());
		assertEquals(-1, a.compareFourOfAKindHands(b));
		assertEquals(1, b.compareFourOfAKindHands(a));		
	}
	
	@Test
	public void compareFullHouseHands2() {
		PokerHand a = new PokerHand(H6, D6, C3, S3, H3);
		PokerHand b = new PokerHand(D4, S4, H7, C7, D7);
		assertTrue(a.hasFullHouse());
		assertTrue(b.hasFullHouse());
		assertEquals(-1, a.compareFullHouseHands(b));
		assertEquals(1, b.compareFullHouseHands(a));
	}
	
	@Test
	public void compareFullHouseHands() {
		PokerHand a = new PokerHand(D4, S4, C3, S3, H3);
		PokerHand b = new PokerHand(H6, D6, H7, C7, D7);
		assertTrue(a.hasFullHouse());
		assertTrue(b.hasFullHouse());
		assertEquals(-1, a.compareFullHouseHands(b));
		assertEquals(1, b.compareFullHouseHands(a));
	}
	
	@Test
	public void hasFullHouse() {
		PokerHand a = new PokerHand(H2, C2, HJ, DJ, SJ);
		PokerHand b = new PokerHand(C3, H3, S6, C6, H9);
		assertTrue(a.hasFullHouse());
		assertFalse(b.hasFullHouse());
	}
	
	@Test
	public void compareFlushHands() {
		PokerHand a = new PokerHand(H6, H9, H10, HK, H2);
		PokerHand b = new PokerHand(C4, C5, H9, C10, CJ);
		assertTrue(a.hasFlush());
		assertFalse(b.hasFlush());
		assertEquals(0, a.compareFlushHands(a));
		assertEquals(0, b.compareFlushHands(b));
		assertEquals(1, a.compareFlushHands(b));
		assertEquals(-1, b.compareFlushHands(a));
	}
	
	@Test
	public void hasFlush() {
		PokerHand a = new PokerHand(H6, H9, H10, HK, H2);
		PokerHand b = new PokerHand(C4, C5, H9, C10, CJ);
		assertTrue(a.hasFlush());
		assertFalse(b.hasFlush());
	}
	
	@Test
	public void compareStraightHandsWithAceHigh() {
		PokerHand a = new PokerHand(S10, DJ, DK, DQ, DA);
		PokerHand b = new PokerHand(D5, D6, H7, H8, S9);
		PokerHand c = new PokerHand(H9, H10, HJ, HQ, SK);
		PokerHand d = new PokerHand(D10, HJ, SQ, SK, SA);
		assertTrue(a.hasStraight());
		assertTrue(b.hasStraight());
		assertTrue(c.hasStraight());
		assertTrue(d.hasStraight());
		assertTrue(a.hasAce());
		assertFalse(b.hasAce());
		assertFalse(c.hasAce());
		assertTrue(d.hasAce());
		assertTrue(a.isAceHigh());
		assertTrue(d.isAceHigh());
		assertEquals(0, a.compareStraightHands(a));
		assertEquals(0, b.compareStraightHands(b));
		assertEquals(0, c.compareStraightHands(c));
		assertEquals(0, d.compareStraightHands(d));
		assertEquals(1, a.compareStraightHands(b));
		assertEquals(-1, b.compareStraightHands(a));
		assertEquals(-1, c.compareStraightHands(d));
		assertEquals(1, d.compareStraightHands(c));
		assertEquals(-1, b.compareStraightHands(c));
		assertEquals(1, c.compareStraightHands(b));
		assertEquals(0, a.compareStraightHands(d));
		assertEquals(0, d.compareStraightHands(a));
		
	}
	
	@Test
	public void compareStraightHandsWithAceLow() {
		PokerHand a = new PokerHand(HA, S2, S3, D4, H5);
		PokerHand b = new PokerHand(D4, D5, D6, D7, H8);
		PokerHand c = new PokerHand(H7, H8, D9, S10, SJ);
		PokerHand d = new PokerHand(SA, S2, S3, S4, D5);
		assertTrue(a.hasStraight());
		assertTrue(b.hasStraight());
		assertTrue(c.hasStraight());
		assertTrue(d.hasStraight());
		assertTrue(a.hasAce());
		assertFalse(b.hasAce());
		assertFalse(c.hasAce());
		assertTrue(d.hasAce());
		assertFalse(a.isAceHigh());
		assertFalse(d.isAceHigh());
		assertEquals(0, a.compareStraightHands(a));
		assertEquals(0, b.compareStraightHands(b));
		assertEquals(0, c.compareStraightHands(c));
		assertEquals(0, d.compareStraightHands(d));
		assertEquals(-1, a.compareStraightHands(b));
		assertEquals(1, b.compareStraightHands(a));
		assertEquals(1, c.compareStraightHands(d));
		assertEquals(-1, d.compareStraightHands(c));
		assertEquals(-1, b.compareStraightHands(c));
		assertEquals(1, c.compareStraightHands(b));
		assertEquals(0, a.compareStraightHands(d));
		assertEquals(0, d.compareStraightHands(a));
		
	}
	
	@Test
	public void compareStraightHandsWithoutAce() {
		PokerHand a = new PokerHand(S2, D3, C4, H5, S6);
		PokerHand b = new PokerHand(C3, H4, D5, D6, D7);
		assertFalse(a.hasAce());
		assertTrue(a.hasStraight());
		assertFalse(b.hasAce());
		assertTrue(b.hasStraight());
		assertEquals(0, a.compareStraightHands(a));
		assertEquals(0, b.compareStraightHands(b));
		assertEquals(-1, a.compareStraightHands(b));
		assertEquals(1, b.compareStraightHands(a));
	}
	
	@Test
	public void hasStraightWhenAceIsHigh() {
		PokerHand a = new PokerHand(HA, HK, CQ, SJ, D10);
		PokerHand b = new PokerHand(CA, DK, SQ, S10, C9);
		assertTrue(a.hasAce());
		assertTrue(a.hasStraight());
		assertTrue(a.isAceHigh());
		assertTrue(b.hasAce());
		assertFalse(b.hasStraight());
	}
	
	@Test
	public void hasStraightWhenAceIsLow() {
		PokerHand a = new PokerHand(CA, S2, S3, D4, H5);
		PokerHand b = new PokerHand(CA, DA, C2, S3, H4);
		assertTrue(a.hasAce());
		assertTrue(a.hasStraight());
		assertFalse(a.isAceHigh());
		assertTrue(b.hasAce());
		assertFalse(b.hasStraight());
	}
	
	@Test
	public void hasStraight() {
		PokerHand a = new PokerHand(S2, H3, C4, D5, C6);
		PokerHand b = new PokerHand(DA, H4, D8, C9, C2);
		assertTrue(a.hasStraight());
		assertFalse(b.hasStraight());
		assertFalse(a.hasAce());
		assertTrue(b.hasAce());
	}
	
    @Test
    public void hasThreeOfAKind() {
	    PokerHand a = new PokerHand(S2, CK, C3, H3, D3);
	    PokerHand b = new PokerHand(CA, C4, SA, D5, DA);
	    PokerHand c = new PokerHand(S2, H3, D4, HK, C5);
	    assertTrue(a.hasThreeOfAKind());
	    assertTrue(b.hasThreeOfAKind());
	    assertFalse(c.hasThreeOfAKind());
	    assertEquals(3, a.getHighPair());
	    assertEquals(14, b.getHighPair());
	    assertEquals(-1, a.compareThreeOfAKindHands(b));
	    assertEquals(1, b.compareThreeOfAKindHands(a));
    }
  
    @Test
    public void hasPair(){
	    PokerHand a = new PokerHand(C4, D4, C5, C8, CK);
	    PokerHand b = new PokerHand(H3, C10, D10, S9, H2);
	    PokerHand c = new PokerHand(DA, CK, S4, S5, S6);
	    assertTrue(a.hasPair());
	    assertTrue(b.hasPair());
	    assertFalse(c.hasPair());
	    assertEquals(4, a.getHighPair());
	    assertEquals(10, b.getHighPair());
	    assertEquals(0, a.compareOnePairHands(a));
	    assertEquals(0, b.compareOnePairHands(b));
	    assertEquals(-1, a.compareOnePairHands(b));
	    assertEquals(1, b.compareOnePairHands(a));
   }
  
    @Test
    public void hasPairAndHighTwoCardsAreEqual(){
	    PokerHand a = new PokerHand(H7, D7, S2, S8, S10);
	    PokerHand b = new PokerHand(S7, C7, C3, D8, H10);
	    a.hasPair();
	    b.hasPair();
	    assertEquals(-1, a.compareOnePairHands(b));
	    assertEquals(1, b.compareOnePairHands(a));
	  
    }
    
  @Test
  public void hasPairAndHighCardIsEqual(){
	  PokerHand a = new PokerHand(H7, D7, S2, S6, S10);
	  PokerHand b = new PokerHand(S7, C7, C3, H8, H10);
	  assertEquals(-1, a.compareOnePairHands(b));
	  assertEquals(1, b.compareOnePairHands(a));
	  
  }
  
  @Test 
  public void hasPairTie() {
	  PokerHand a = new PokerHand(H7, D7, S2, S8, S10);
	  PokerHand b = new PokerHand(S7, C7, C2, H8, H10);
	  assertEquals(0, a.compareOnePairHands(b));
	  assertEquals(0, b.compareOnePairHands(a));
  }
  
  @Test
  public void hasSamePair(){
	  PokerHand a = new PokerHand(C4, D4, S2, H5, S6);
	  PokerHand b = new PokerHand(D2, D3, H4, S4, D5);
	  assertEquals(1, a.compareOnePairHands(b));
	  assertEquals(-1, b.compareOnePairHands(a));
  }
	
	
  @Test
  public void testTwoPair1() {
    PokerHand a = new PokerHand(C4, CA, D4, H3, DA);
    PokerHand b = new PokerHand(H4, C10, HA, SA, S4);
    assertTrue(a.hasTwoPair()); 
    assertTrue(b.hasTwoPair());
  }

  @Test
  public void testTwoPair2() {
    PokerHand a = new PokerHand(C4, CA, D4, H3, DA); //2 FOURS, 2 ACES, 1 THREE
    PokerHand b = new PokerHand(H4, C10, HA, SA, S4); //2 FOURS, 2 ACES, 1 TEN
    assertEquals(-1, a.compareTwoPairHands(b));
    assertEquals(1, b.compareTwoPairHands(a));
    assertEquals(0, a.compareTwoPairHands(a));
    assertEquals(0, b.compareTwoPairHands(b));
  }

  @Test
  public void testTwoPairWhenOnePairIsEqual2() {
    PokerHand a = new PokerHand(C8, D8, CA, SA, CK); // 2 ACES, 2 EIGHTS
    PokerHand b = new PokerHand(H9, S9, HA, DA, HK); // 2 ACES, 2 NINES
    assertTrue(a.hasTwoPair());
    assertTrue(b.hasTwoPair());

    assertEquals(-1, a.compareTwoPairHands(b));
    assertEquals(1, b.compareTwoPairHands(a));
  }

  @Test
  public void testTwoPairWhenTwoPairsAreEqual4() {
    PokerHand a = new PokerHand(C2, D2, C5, D5, CJ);
    PokerHand b = new PokerHand(H2, S2, H5, S5, C10);
    assertEquals(+1, a.compareTwoPairHands(b));
    assertEquals(-1, b.compareTwoPairHands(a));
    assertEquals(0, b.compareTwoPairHands(b));
    assertEquals(0, a.compareTwoPairHands(a));
  }

  @Test
  public void testTwoPairWhenTwoPairsAreEquaAndTheFifthCardHasSameRank() {
    PokerHand a = new PokerHand(C2, D2, C5, D5, CJ);
    PokerHand b = new PokerHand(H2, S2, H5, S5, SJ);
    assertEquals(0, a.compareTwoPairHands(a));
    assertEquals(0, b.compareTwoPairHands(b));
  }

  // Set up 52 cards so we can use C2 instead of new Card(Rank.Deuce,
  // Suit.Clubs)
  private final static Card C2 = new Card(Rank.DEUCE, Suit.CLUBS);
  private final static Card C3 = new Card(Rank.THREE, Suit.CLUBS);
  private final static Card C4 = new Card(Rank.FOUR, Suit.CLUBS);
  private final static Card C5 = new Card(Rank.FIVE, Suit.CLUBS);
  private final static Card C6 = new Card(Rank.SIX, Suit.CLUBS);
  private final static Card C7 = new Card(Rank.SEVEN, Suit.CLUBS);
  private final static Card C8 = new Card(Rank.EIGHT, Suit.CLUBS);
  private final static Card C9 = new Card(Rank.NINE, Suit.CLUBS);
  private final static Card C10 = new Card(Rank.TEN, Suit.CLUBS);
  private final static Card CJ = new Card(Rank.JACK, Suit.CLUBS);
  private final static Card CQ = new Card(Rank.QUEEN, Suit.CLUBS);
  private final static Card CK = new Card(Rank.KING, Suit.CLUBS);
  private final static Card CA = new Card(Rank.ACE, Suit.CLUBS);

  private final static Card D2 = new Card(Rank.DEUCE, Suit.DIAMONDS);
  private final static Card D3 = new Card(Rank.THREE, Suit.DIAMONDS);
  private final static Card D4 = new Card(Rank.FOUR, Suit.DIAMONDS);
  private final static Card D5 = new Card(Rank.FIVE, Suit.DIAMONDS);
  private final static Card D6 = new Card(Rank.SIX, Suit.DIAMONDS);
  private final static Card D7 = new Card(Rank.SEVEN, Suit.DIAMONDS);
  private final static Card D8 = new Card(Rank.EIGHT, Suit.DIAMONDS);
  private final static Card D9 = new Card(Rank.NINE, Suit.DIAMONDS);
  private final static Card D10 = new Card(Rank.TEN, Suit.DIAMONDS);
  private final static Card DJ = new Card(Rank.JACK, Suit.DIAMONDS);
  private final static Card DQ = new Card(Rank.QUEEN, Suit.DIAMONDS);
  private final static Card DK = new Card(Rank.KING, Suit.DIAMONDS);
  private final static Card DA = new Card(Rank.ACE, Suit.DIAMONDS);

  private final static Card H2 = new Card(Rank.DEUCE, Suit.HEARTS);
  private final static Card H3 = new Card(Rank.THREE, Suit.HEARTS);
  private final static Card H4 = new Card(Rank.FOUR, Suit.HEARTS);
  private final static Card H5 = new Card(Rank.FIVE, Suit.HEARTS);
  private final static Card H6 = new Card(Rank.SIX, Suit.HEARTS);
  private final static Card H7 = new Card(Rank.SEVEN, Suit.HEARTS);
  private final static Card H8 = new Card(Rank.EIGHT, Suit.HEARTS);
  private final static Card H9 = new Card(Rank.NINE, Suit.HEARTS);
  private final static Card H10 = new Card(Rank.TEN, Suit.HEARTS);
  private final static Card HJ = new Card(Rank.JACK, Suit.HEARTS);
  private final static Card HQ = new Card(Rank.QUEEN, Suit.HEARTS);
  private final static Card HK = new Card(Rank.KING, Suit.HEARTS);
  private final static Card HA = new Card(Rank.ACE, Suit.HEARTS);

  private final static Card S2 = new Card(Rank.DEUCE, Suit.SPADES);
  private final static Card S3 = new Card(Rank.THREE, Suit.SPADES);
  private final static Card S4 = new Card(Rank.FOUR, Suit.SPADES);
  private final static Card S5 = new Card(Rank.FIVE, Suit.SPADES);
  private final static Card S6 = new Card(Rank.SIX, Suit.SPADES);
  private final static Card S7 = new Card(Rank.SEVEN, Suit.SPADES);
  private final static Card S8 = new Card(Rank.EIGHT, Suit.SPADES);
  private final static Card S9 = new Card(Rank.NINE, Suit.SPADES);
  private final static Card S10 = new Card(Rank.TEN, Suit.SPADES);
  private final static Card SJ = new Card(Rank.JACK, Suit.SPADES);
  private final static Card SQ = new Card(Rank.QUEEN, Suit.SPADES);
  private final static Card SK = new Card(Rank.KING, Suit.SPADES);
  private final static Card SA = new Card(Rank.ACE, Suit.SPADES);

 }