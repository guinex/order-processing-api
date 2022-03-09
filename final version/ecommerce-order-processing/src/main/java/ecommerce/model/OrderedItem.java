package ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "ordered_items")
public class OrderedItem {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="sub_total")
	private double subTotal;
	private double tax;
	
	@Value("${config.orderedItem.orderStatus: false}")
	@Column(name="order_status")	
	private boolean orderStatus;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

	@JsonIgnore
    @OneToMany(mappedBy = "ordered_item", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<LineItem> line_items;       
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}
	
	public boolean getStatus() {
		return orderStatus;
	}

	public void setStatus(boolean status) {
		this.orderStatus = status;
	}	

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Set<LineItem> getLineItems() {
		return this.line_items;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTotal() {
		return this.subTotal + this.tax;
	}
	
	public OrderedItem(){
	}

	public OrderedItem(Long id){
		super();
		this.id = id;
	}	
}
