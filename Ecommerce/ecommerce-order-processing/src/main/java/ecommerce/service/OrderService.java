package ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import ecommerce.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.lib.PaymentHash;
import ecommerce.repository.OrderRepository;

// Order Service with Order related business logic
@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderedItemService orderedItemService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CartService cartService;
	
	// returns all the orders
	public List<Order> getAllOrders(){
		List<Order> orders = new ArrayList<>();
		repository.findAll()
			.forEach(orders::add);
		return orders;
	}

	// returns the orders with id={id}
	public Order getOrder(Long id) {
		try
		{
			return repository.findById(id).get();
		}
		catch (Exception exc)
		{
			return new Order();
		}

		
	}

	// creates order using multiple payments
	// cart id is required
	// creates ordereditems and payment
	public Order createOrder(Long cartId, List<PaymentHash> paymentData) {
		Cart cart = cartService.getCart(cartId);
		Customer customer = cart.getCustomer();
		List<LineItem> items = cartService.getLineItems(cart.getId());
		System.out.println("lineitems create");
		Order order = setAddress(new Order(), cart);
		order.setCustomer(customer);
		System.out.println("address set");
		boolean success = false;
		order = repository.save(order);
		System.out.println("create order: save ordered");
		System.out.println("building order");
		success = buildOrder(order, items, paymentData);
		if(success){
			repository.save(order);
		}
		return order;
	}

	//set the billing and shipping address of the address
	private Order setAddress(Order order, Cart cart) {
		Address billing = addressService.getAddress(
				cart.getBillingAddressId());
		Address shipping = addressService.getAddress(
				cart.getShippingAddressId());
		order.setBillingAddress(billing);
		order.setShippingAddress(shipping);
		return order;
	}

	//calculates the shipping charges
	private double calculateShippingCharges(Order order) {
		double charges = 0.0;
		Address shippingAddress = order.getShippingAddress();
		if(shippingAddress.getZipcode() == "27606") {
			charges = charges + 10.0;
		}
		return charges;
		
	}

	//captures the payment for a order
	private boolean capturePayment(Order order, List<PaymentHash> paymentData) {
		System.out.println("capture payment");
		boolean paymentSuccess = false;
		for(int i= 0; i < paymentData.size();i++) {
			paymentSuccess = paymentSuccess || paymentService.addPayment(order, paymentData.get(i));
		}
		return paymentSuccess;
	}

	// builds an order and set its attributes
	public Boolean buildOrder(Order order, List<LineItem> items, List<PaymentHash> paymentData) {
		OrderedItem orderedItem = orderedItemService.buildOrderedItems(items, order);
		System.out.println("ordered item created");
		order.setTax(orderedItem.getTax());		
		double shippingCharges = calculateShippingCharges(order);	
		order.setShippingCharges(shippingCharges);
		order.setTotal(orderedItem.getTotal() + shippingCharges + order.getTax());
		System.out.println("build order: save ordered");
		repository.save(order);
		boolean paymentSucess = false;
		paymentSucess = capturePayment(order, paymentData);
		return paymentSucess;
	}

	// saves an order
	// RUNS callbacks
	public void save(Order order) {
		order.callback(false);
		repository.save(order);
	}

	// saves an order
	// DOESN`T RUNS callbacks
	public void saveAndSkip(Order order) {
		order.callback(true);
		repository.save(order);
	}

	//check orders total and paid amount
	public boolean checkTotal(Order order) {
		return order.getPaid() >= order.getTotal();
	}


	public void cancelOrder(Long id) {
		Order order = repository.findById(id).get();
		order.setStatus(false);
		saveAndSkip(order);
	}
}
