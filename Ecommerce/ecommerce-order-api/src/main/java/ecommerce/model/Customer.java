package ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;

// Customer model
@Entity
@Table(name = "customers")
public class Customer {
	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private  String email;
	
	// from JPA
	@JsonIgnore
    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Cart cart;

	@JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Address> addresses;

	@JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Order> orders;

	@JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Payment> payments;  
    
    
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}	
	
	public void setName(String name) {
		this.name =name;
	}

	public Cart getCart() {
		return cart;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer(){
	}

	public Customer(String name){
		super();
		this.name = name;
	}


	
}
