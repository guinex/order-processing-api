package ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "listings")
public class Listing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String name;

	@JsonIgnore
    @OneToMany(mappedBy = "listing", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Product> products;	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name =name;
	}
	
	public Long getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}	
	
	public Listing(){
	}

	public Listing(Long id, String name){
		super();
		this.id = id;
		this.name = name;
	}	
}