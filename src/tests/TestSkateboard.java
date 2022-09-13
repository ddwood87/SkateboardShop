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
	String testDeck = "DECK";
	String testWheel = "WHEEL";
	String testTruck = "TRUCK";
	String toStringOut;	
	@Before
	public void setUp() throws Exception {
		testSkate.setDeckBrand(testDeck);
		testSkate.setWheelBrand(testWheel);
		testSkate.setTruckBrand(testTruck);
	}
	@Test
	public void testToString() {		
		toStringOut ="ID: " + testSkate.getId();
		toStringOut += ", Deck: " + testSkate.getDeckBrand();
		toStringOut += ", Wheel: " + testSkate.getWheelBrand();
		toStringOut += ", Truck: " + testSkate.getTruckBrand();
		
		assertEquals(toStringOut, testSkate.toString());
	}
	@Test
	public void testEquals_differentIds() {
		Skateboard skate = new Skateboard(testSkate.getDeckBrand(), testSkate.getWheelBrand(), testSkate.getTruckBrand());
		
		skate.setId(1);
		testSkate.setId(2);
		
		assertTrue(skate.equals(testSkate));
	}
}
