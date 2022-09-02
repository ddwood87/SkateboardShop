package models;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Sep 2, 2022
 */
public class SkateDeck {
	private String brand;
	private double size;
	
	public SkateDeck() {
		super();
	}
	public SkateDeck(String brand, double size) {
		super();
		this.brand = brand;
		this.size = size;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	
	public String toString() {
		return getBrand() + ", " + getSize() +"\"";
	}
	public boolean isWide() {
		if(this.size >= 9) {
			return true;
		} else {return false;}
	}
}
