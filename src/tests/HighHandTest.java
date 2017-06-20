package tests;

import model.HighHand;
import static org.junit.Assert.*;

import org.junit.Test;

public class HighHandTest {
	
	@Test
	public void testHighHand() {
		assertEquals(2, HighHand.ONEPAIR.getValue());
	}

}
