package ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "orders")
public class Order {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double tax;
	@Column(name="shipping_charges")
	private double shippingCharges;
	private double total;
	private double paid;
	
	@Value("${config.order.status: false}")
	private boolean status;
	
	// from JPA
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shipping_address_id", nullable = false)
	private Address shipping_address;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "billing_address_id", nullable = false)
	private Address billing_address;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<OrderedItem> order_items;

	@JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Payment> payments;      
    
    
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	
	
	public Address getShippingAddress() {
		return this.shipping_address;
	}

	public void setShippingAddress(Address address) {
		this.shipping_address = address;
	}

	public Address getBillingAddress() {
		return this.billing_address;
	}
	
	public Set<Payment> getPayments() {
		return this.payments;
	}	

	public void setBillingAddress(Address address) {
		this.billing_address = address;
	}	
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double  getShippingCharges() {
		return shippingCharges;
	}

	public void setShippingCharges(double  shippingCharges) {
		this.shippingCharges = shippingCharges;
	}

	public double  getTax() {
		return tax;
	}

	public void setTax(double  tax) {
		this.tax = tax;
	}

	public double  getTotal() {
		return total;
	}

	public void setTotal(double  total) {
		this.total = total;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	public Set<OrderedItem> getOrder_items() {
		return order_items;
	}

	public void setOrder_items(Set<OrderedItem> order_items) {
		this.order_items = order_items;
	}
	public Order(){
	}

	public void callback(Boolean skip) {
		if(skip) return;
		this.status = this.paid  >= this.total;
	}

}
