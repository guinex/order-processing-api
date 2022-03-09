package ecommerce.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "line_items")
public class LineItem {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String name;
	private String description;
	@NotNull
	private double price;
	@NotNull
	private int quantity;

	
	// default value is false, set to true when order is placed
	@Value("${config.lineItem.status:false}")
	private boolean status;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
	
	// from JPA
	@JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ordered_item_id", nullable = false)
    private OrderedItem ordered_item;
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double  price) {
		this.price = price;
	}	
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderedItem getOrderedItem() {
		return this.ordered_item;
	}

	public void setOrderedItem(OrderedItem orderedItem) {
		this.ordered_item = orderedItem;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity= quantity;
	}

	public boolean isOrdered() {
		return status;
	}

	public void setOrdered(boolean status) {
		this.status= status;
	}

	public double  getTotal() {
		return (this.price * (double) this.quantity);
	}

	public LineItem(){
	}

	public LineItem(String name, String description, float price, int quantity){
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		
	}
	public boolean isNew() {
		return this.id == null;
	}	
}
