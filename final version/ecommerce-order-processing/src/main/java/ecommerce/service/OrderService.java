package ecommerce.service;

import java.util.ArrayList;

import java.util.List;

import ecommerce.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.lib.PaymentHash;
import ecommerce.repository.OrderRepository;

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
	
	
	public List<Order> getAllOrders(){
		List<Order> orders = new ArrayList<>();
		repository.findAll()
			.forEach(orders::add);
		return orders;
	}
	
	public Order getOrder(Long id) {
		return repository.findById(id).get();
		
	}

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
	
	public void updateOrderPayment(Long id, PaymentHash paymentData) {
		Order order = repository.findById(id).get();
		boolean paymentSuccess = paymentService.addPayment(order,  paymentData);
		if(paymentSuccess) {
			order.setStatus(paymentSuccess);
			repository.save(order);
		}
	}
	
	public void updateOrderByItem(Long id, OrderedItem orderedItem) {
		Order order = repository.findById(id).get();
		List<LineItem> items = (List<LineItem>) orderedItem.getLineItems();
		orderedItem = orderedItemService.processOrderedItems(items, order);
		order.setTax(orderedItem.getTax());		
		order.setTotal(orderedItem.getTotal() + order.getShippingCharges() + order.getTax());
		repository.save(order);
		boolean paymentSuccess = checkTotal(order);
		//boolean paymentSuccess = buildOrder(order);
		if(paymentSuccess) {
			order.setStatus(paymentSuccess);
			repository.save(order);
		}
		
	}	

	private Order setAddress(Order order, Cart cart) {
		Address billing = addressService.getAddress(
				cart.getBillingAddressId());
		Address shipping = addressService.getAddress(
				cart.getShippingAddressId());
		order.setBillingAddress(billing);
		order.setShippingAddress(shipping);
		return order;
	}

	private double calculateShippingCharges(Order order) {
		double charges = 0.0;
		Address shippingAddress = order.getShippingAddress();
		if(shippingAddress.getZipcode() == "27606") {
			charges = charges + 10.0;
		}
		return charges;
		
	}

	private boolean capturePayment(Order order, List<PaymentHash> paymentData) {
		System.out.println("capture payment");
		boolean paymentSuccess = false;
		for(int i= 0; i < paymentData.size();i++) {
			paymentSuccess = paymentSuccess || paymentService.addPayment(order, paymentData.get(i));
		}
		return paymentSuccess;
	}

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

	public void updateOrder(Order order) {
		repository.save(order);
	}
	

	public void deleteOrder(Long id) {
		repository.deleteById(id);
		return;
	}
	
	public void save(Order order) {
		order.callback();
		repository.save(order);
	}

	public boolean checkTotal(Order order) {	
		return order.getPaid() >= order.getTotal();
	}


	public void cancelOrder(Long id) {
		Order order = repository.findById(id).get();
		order.setStatus(false);
		save(order);
	}
}
