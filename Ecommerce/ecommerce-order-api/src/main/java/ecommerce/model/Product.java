package ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import javax.persistence.*;
import java.util.Set;

// Product model
@Entity
@Table(name = "products")
public class Product {
	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String description;

	private float price;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Listing_id", nullable = false)
	private Listing listing;

	@JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<LineItem> line_items;
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name =name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}	

	public String getDescription() {
		return this.description;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Product(){
	}

	public Product(String name, String description, float price, Long listingId){
		super();
		this.description = description;
		this.name = name;
		this.price = price;
		this.setListing(new Listing(listingId, ""));
	}
}
