package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.SkateDeck;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Sep 2, 2022
 */
public class TestSkateDeck {

		SkateDeck testDeck = new SkateDeck();
		String testBrand = "<ShopName>";
		double testSize = 8.25;
		@Before
		public void setUp() throws Exception {
		}

		@Test
		public void testGetSetBrand() {
			testDeck.setBrand(testBrand);
			
			assertEquals(testBrand, testDeck.getBrand());
		}
		
		@Test
		public void testGetSetSize() {
			testDeck.setSize(testSize);
			
			assertEquals(testSize, testDeck.getSize(), .01);
		}
		
		@Test
		public void testToString() {
			assertNotNull(testDeck.toString());
		}
		
		@Test
		public void testIsWide_true() {
			testDeck.setSize(9.5);
			assertTrue(testDeck.isWide());
		}
		
		@Test 
		public void testIsWide_false() {
			testDeck.setSize(8.5);
			assertFalse(testDeck.isWide());
		}
}
