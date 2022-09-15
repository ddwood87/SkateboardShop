package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Sep 2, 2022
 */
@Entity
@Table(name="skateboards")
public class Skateboard {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="DECK")
	private String deckBrand;
	
	@Column(name="WHEEL")
	private String wheelBrand;

	@Column(name="TRUCK")
	private String truckBrand;
	
	public Skateboard() {
		super();
	}
	
	public Skateboard(String deckBrand, String wheelBrand, String truckBrand) {
		super();
		this.deckBrand = deckBrand;
		this.wheelBrand = wheelBrand;
		this.truckBrand = truckBrand;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeckBrand() {
		return deckBrand;
	}

	public void setDeckBrand(String deckBrand) {
		this.deckBrand = deckBrand;
	}

	public String getWheelBrand() {
		return wheelBrand;
	}

	public void setWheelBrand(String wheelBrand) {
		this.wheelBrand = wheelBrand;
	}

	public String getTruckBrand() {
		return truckBrand;
	}

	public void setTruckBrand(String truckBrand) {
		this.truckBrand = truckBrand;
	}

	public String toString() {
		String result = "ID: " + id;
		result += ", Deck: " + deckBrand;
		result += ", Wheel: " + wheelBrand;
		result += ", Truck: " + truckBrand;
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if(this == o) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(this.getClass() != o.getClass()) {
			return false;
		}
		Skateboard skate = (Skateboard)o;
		if(this.deckBrand != null && skate.deckBrand != null) {
			if(this.deckBrand == skate.deckBrand) {
				result = true;			
			} else { return false; }
		}
		if(this.wheelBrand != null && skate.wheelBrand != null) {
			if(this.wheelBrand == skate.wheelBrand) {
				result = true;			
			} else { return false; }
		}
		if(this.truckBrand != null && skate.truckBrand != null) {
			if(this.truckBrand == skate.truckBrand) {
				result = true;			
			} else { return false; }
		}
		return result;
	}
}
