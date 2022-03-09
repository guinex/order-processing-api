package ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String name;
	private String description;
	@NotNull
	private float price;
	
	// from JPA
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

	public Product(Long id, String name, String description, float price, Long listingId){
		super();
		this.id = id;
		this.description = description;
		this.name = name;
		this.price = price;
		this.setListing(new Listing(listingId, ""));
	}
		
}
