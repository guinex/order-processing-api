package ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	@Column(name="payment_method")
	private String methodName;
	@NotNull
	@Column(name="confirmation_date")
	private String confirmationDate;
	@NotNull
	@Column(name="confirmation_number")	
	private String confirmationNumber;
	@NotNull
	private float total;
	
	@Value("${config.payment.type:Card")
	@Column(name="pay_type")	
	private String payType;
	
	@Value("${config.payment.status: false}")
	private boolean status;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;    
    
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	
	
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(String confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}	

	public Payment(){
	}

	public Payment(int id, String methodName, String confirmationDate, String confirmationNumber, float total, Order order, Customer customer){
		super();
		this.id = id;
		this.setConfirmationDate(confirmationDate);
		this.setConfirmationNumber(confirmationNumber);
		this.setMethodName(methodName);
		this.setTotal(total);
		this.order = order;
		this.customer = customer;
	}	
}