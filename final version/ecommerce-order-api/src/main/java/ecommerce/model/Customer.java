package ecommerce.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	@Email
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

	public void setCart(Cart cart) {
		if(this.cart == null) {
			this.cart = cart;			
		}
	}

	public Customer(){
	}

	public Customer(Long id, String name, Long cartId){
		super();
		this.id = id;
		this.name = name;
		this.setCart(new Cart(cartId));
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	
}
