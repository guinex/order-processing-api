package ecommerce.controller;

import ecommerce.lib.PaymentHash;
import ecommerce.service.PaymentService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}

	// accepts Post request at localhost:3000/api/v1/orders/{orderId}/payments
	// with payload of PaymentHash type
	// creates a payment for order with id={orderId}
	// if successful returns Http status OK else returns BAD_REQUEST
	@PostMapping("/orders/{orderId}/payments")
	public ResponseEntity<String>  createPayment(@RequestBody PaymentHash payment, @PathVariable String orderId){
		JSONObject obj = filterParams(payment, orderId);
		HttpStatus status = paymentService.addPayment(orderId, obj);
		return new ResponseEntity<>(status);
	}

	// accepts Post request at localhost:3000/api/v1/payments/cancel
	// with payload of Payment Id
	// cancels the payment
	// if successful returns Http status OK else returns BAD_REQUEST
	@PostMapping("/payments/cancel")
	public ResponseEntity<String>  createPayment(@RequestBody Long paymentId){
		JSONObject obj = new JSONObject();
		obj.put("id", paymentId);
		HttpStatus status = paymentService.cancelPayment(obj);
		return new ResponseEntity<>(status);
	}

	// filters the Payment data parameter and returns a JSON object
	// remove redundant fields
	private JSONObject filterParams(PaymentHash paymentData, String orderId) {
		JSONObject paymentObject = new JSONObject();
		paymentObject.put("methodName", paymentData.getMethodName());
		paymentObject.put("confirmationDate", paymentData.getConfirmationDate());
		paymentObject.put("payType", paymentData.getPayType());
		paymentObject.put("total", paymentData.getTotal());
		paymentObject.put("tnxNumber", paymentData.getTnxNumber());
		paymentObject.put("userId", paymentData.getUserId());
		JSONObject orderHash = new JSONObject();
		orderHash.put("paymentDetails", paymentObject);
		orderHash.put("id", orderId);
		return orderHash;
	}



}
