package ecommerce.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.lib.PaymentHash;
import ecommerce.model.Customer;
import ecommerce.model.Order;
import ecommerce.model.Payment;
import ecommerce.repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository repository;
	@Autowired
	private OrderService orderService;

	// returns a list of payment object
	public List<Payment> getAllPayments(Long customerId){
		List<Payment> Payments = new ArrayList<>();
		repository.findByCustomerId(customerId)
			.forEach(Payments::add);
		return Payments;
	}

	// creates a payment for an order
	public void createPayment(Long orderId, PaymentHash paymentHash){
		Order order = orderService.getOrder(orderId);
		addPayment(order, paymentHash);
	}

	// add a new payment for an order
	// re-calculate paid amount
	public boolean addPayment(Order order, PaymentHash paymentData) {
		Customer customer = order.getCustomer();
		Payment payment = buildPayment(paymentData, customer);
		payment.setOrder(order);
		double paid = order.getPaid()+ payment.getTotal();
		order.setPaid(paid);
		repository.save(payment);
		orderService.save(order);
		return orderService.checkTotal(order);
		
	}

	// builds a new payment object using paymentHash
	private Payment buildPayment(PaymentHash paymentData, Customer customer) {
		Payment payment = new Payment();
		payment.setConfirmationDate(paymentData.getConfirmationDate());
		payment.setConfirmationNumber(paymentData.getTnxNumber());
		payment.setCustomer(customer);
		payment.setMethodName(paymentData.getMethodName());
		payment.setStatus(true);
		payment.setPayType(paymentData.getPayType());
		payment.setTotal(paymentData.getTotal());
		return payment;
		
	}

	// cancels the payment
    public void cancelPayment(Long paymentId) {
		Payment payment = repository.findById(paymentId).get();
		payment.setStatus(false);
		Order order = payment.getOrder();
		orderService.save(order);
		repository.save(payment);
    }
}
