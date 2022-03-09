package ecommerce.controller;
import java.util.List;

import javax.persistence.Table;

import ecommerce.lib.FilterParams;
import ecommerce.lib.PaymentData;
import ecommerce.model.Order;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecommerce.model.Payment;
import ecommerce.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
@Table(name = "payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}

	// accepts GET request at localhost:3000/api/v1/payment/customers/{id}
	// returns payment for the customer with id={id}
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("/customers/{id}/")
	public ResponseEntity<List<Payment>> getAllPayments(@PathVariable String id){
		List<Payment> payments = paymentService.getAllPayments(convertToLong(id));
		if(payments.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}
	// accepts POST request at localhost:3000/api/v1/payment/orders/{orderId}
	// creates payment for order with id={orderId}
	// if successful returns Http status CREATED else returns BAD_REQUEST
	@PostMapping("/orders/{orderId}")
	public HttpStatus createPayment(@RequestBody JSONObject entity){
		try{
			System.out.println(entity.toString());
			PaymentData paymentData = FilterParams.filterPaymentParams(entity);
			paymentService.createPayment(Long.parseLong(paymentData.id), paymentData.getPaymentDetails());
			return HttpStatus.CREATED;
		}catch(Exception exc){
			System.out.println(exc);
			return HttpStatus.BAD_REQUEST;
		}
	}

	// accepts POST request at localhost:3000/api/v1/payment/cancel
	// cancels the payment and re-calculate paid field of order
	// if successful returns Http status OK else returns BAD_REQUEST
	@PostMapping(value="/cancel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus cancelPayment(@RequestBody JSONObject entity){
		try{
			System.out.println(entity.toString());
			Long paymentId = FilterParams.filterById(entity);
			paymentService.cancelPayment(paymentId);
			return HttpStatus.OK;
		}catch(Exception exc){
			System.out.println(exc);
			return HttpStatus.BAD_REQUEST;
		}
	}
}
