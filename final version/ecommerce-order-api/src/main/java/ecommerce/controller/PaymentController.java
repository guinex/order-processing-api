package ecommerce.controller;

import ecommerce.lib.PaymentHash;
import ecommerce.model.Payment;
import ecommerce.producer.OrderProducer;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

	@Autowired
	private OrderProducer orderProducer;
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}
		
	@PostMapping("/{orderId}")
	public ResponseEntity<String>  createPayment(@RequestBody PaymentHash payment, @PathVariable String orderId){
		JSONObject obj = filterParams(payment, orderId);
		HttpStatus status = orderProducer.addPayment(orderId, obj);
		return new ResponseEntity<>(status);
	}
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
