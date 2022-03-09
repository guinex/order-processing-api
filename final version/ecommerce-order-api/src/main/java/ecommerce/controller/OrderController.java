package ecommerce.controller;


import ecommerce.lib.PaymentHash;
import ecommerce.producer.OrderProducer;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	@Autowired
	private OrderProducer orderProducer;

	@PostMapping("/{cartId}/process")
	public ResponseEntity<String> processCart(@RequestBody List<PaymentHash> paymentData, @PathVariable String cartId){
		JSONObject obj = filterParams(paymentData, cartId);
		HttpStatus status = orderProducer.placeOrder(obj);
		return new ResponseEntity<>(status);
	}

	@PostMapping("/cancel")
	public ResponseEntity<String> cancelOrder(@RequestBody String orderId){
		JSONObject obj =  new JSONObject();
		obj.put("id", orderId);
		HttpStatus status = orderProducer.cancelOrder(obj);
		return new ResponseEntity<>(status);
	}

	private JSONObject filterParams(List<PaymentHash> paymentData, String cartId) {
		List<JSONObject> payments = new ArrayList<>();
		for (PaymentHash paymentDatum : paymentData) {
			JSONObject paymentObject = new JSONObject();
			paymentObject.put("methodName", paymentDatum.getMethodName());
			paymentObject.put("confirmationDate", paymentDatum.getConfirmationDate());
			paymentObject.put("payType", paymentDatum.getPayType());
			paymentObject.put("total", paymentDatum.getTotal());
			paymentObject.put("tnxNumber", paymentDatum.getTnxNumber());
			paymentObject.put("userId", paymentDatum.getUserId());
			payments.add(paymentObject);
		}
		JSONObject orderHash = new JSONObject();
		orderHash.put("paymentDetails", payments);
		orderHash.put("id", cartId);
		return orderHash;
	}

	

		
}
