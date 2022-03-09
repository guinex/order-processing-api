package ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address {
	@Id
	@NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="address_line_1")
	private String addressLine1;
	@Column(name="address_line_2")
	private String addressLine2;
	private String city;
	private String state;
	private String zipcode;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

	@JsonIgnore
    @OneToMany(mappedBy = "shipping_address", fetch = FetchType.LAZY)
    private Set<Order> shippedOrders;

	@JsonIgnore
    @OneToMany(mappedBy = "billing_address", fetch = FetchType.LAZY)
    private Set<Order> billingOrders;    

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

    public String getAddressLine1() {
		return this.addressLine1;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		if(this.customer == null) {
			this.customer = customer;			
		}
	}	
	
    public Address(){
	}

	public Address(String addressLine1, String addressLine2, String city, String state, String zipcode){
		super();
		this.setAddressLine1(addressLine1);
		this.setAddressLine2(addressLine2);
		this.setCity(city);
		this.setState(state);
		this.setZipcode(zipcode);
	}
		
    @Override
    public String toString() {
        return "Address: {id:" + id 
        		+", addressLine1: " + addressLine1 
        		+ ", addressLine2:" + addressLine2 
        		+ ", city:" + city 
        		+ ", state:" + state 
        		+ ", zipcode:" + zipcode 
        		+ "}";
    }	
}
