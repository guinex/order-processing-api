package ecommerce.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "carts")
public class Cart {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="shipping_address_id")
	private Long shippingAddressId;
	@Column(name="billing_address_id")
	private Long billingAddressId;

	// from JPA
	@JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;    

	@JsonIgnore
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<LineItem> line_items;      

	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public Long getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Long shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public Long getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(Long billingAddressId) {
		this.billingAddressId = billingAddressId;
	}
	
	public Set<LineItem> getLineItems(){
		return this.line_items;
	}
	
	public Cart(){
	}

	public Cart(Long id){
		super();
		this.id = id;
	}
}
