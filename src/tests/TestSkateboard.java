package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import models.*;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Sep 2, 2022
 */
public class TestSkateboard {
	Skateboard testSkate = new Skateboard();
	SkateDeck testDeck = new SkateDeck("<ShopName>", 8.25);
	//SkateWheel
	//SkateTruck
	//SkateBearing
	String toStringOut = "\nDeck: <ShopName>, 8.25\"";
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testAddPart_deck() {
		assertNull(testSkate.getDeck());
		testSkate.addPart(testDeck);
		assertNotNull(testSkate.getDeck());
	}
	
	@Test
	public void testRemoveDeck() {
		testSkate.addPart(testDeck);
		assertNotNull(testSkate.getDeck());
		testSkate.removeDeck();
		assertNull(testSkate.getDeck());
	}
	@Test
	public void testToString() {
		testSkate.addPart(testDeck);
		assertEquals(toStringOut, testSkate.toString());
	}
	

}
