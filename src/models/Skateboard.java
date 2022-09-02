package models;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Sep 2, 2022
 */
public class Skateboard {
	private SkateDeck deck;
	//private SkateWheel wheel;
	//private SkateTruck truck;
	//private SkateBearing bearing;
	
	public Skateboard() {
		super();
	}
	
	public Skateboard(SkateDeck deck) {
		super();
		this.deck = deck;
	}

	public void addPart(SkateDeck newDeck) {
		this.deck = newDeck;
	}	
	public SkateDeck getDeck() {
		return this.deck;
	}
	public void removeDeck() {
		this.deck = null;		
	}
	
	//public void addPart(SkateWheel newWheel){}
	//public SkateWheel getWheel(){}
	//public void removeDeck(){}
	
	public String toString() {
		return "\nDeck: " + deck.toString();
	}
}
