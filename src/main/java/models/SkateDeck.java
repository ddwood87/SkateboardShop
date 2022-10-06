package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author dominicwood - ddwood2@dmacc.edu
 * CIS175 - Fall 2022
 * Oct 2, 2022
 */
@Entity
@Table(name="SkateDecks")
public class SkateDeck {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="BRAND")
	private String brand;
	@Column(name="WIDTH")
	private double width;
	
	@OneToMany(mappedBy="deck", targetEntity=Skateboard.class, orphanRemoval=true, cascade=CascadeType.REMOVE)
	@JoinColumn
	private List<Skateboard> skates;
	
	public SkateDeck() {
		super();
		skates = new ArrayList<Skateboard>();
	}

	public SkateDeck(String brand, double width) {
		super();
		this.brand = brand;
		this.width = width;
		skates = new ArrayList<Skateboard>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	
	public List<Skateboard> getSkates(){
		return skates;
	}
	public void setSkates(List<Skateboard> skates){
		this.skates = skates;
	}
	public void addSkate(Skateboard skate) {
		skates.add(skate);
	}
	public void removeSkate(Skateboard skate) {	
		skates.remove(skate);		
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
		SkateDeck deck = (SkateDeck)o;
		if(this.brand != null && deck.brand != null) {
			if(this.brand.equals(deck.brand)) {
				result = true;			
			} else { return false; }
		}
		if(this.width == deck.width && result == true) {
			result = true;			
		} else { return false; }
		return result;
	}
	@Override
	public String toString() {
		return "SkateDeck [id=" + id + ", brand=" + brand + ", width=" + width + "]";
	}

	
}
