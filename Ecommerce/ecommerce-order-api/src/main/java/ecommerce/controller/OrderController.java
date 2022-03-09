package ecommerce.controller;


import ecommerce.lib.PaymentHash;
import ecommerce.service.OrderService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	@Autowired
	private OrderService orderService;

	// accepts Post request at localhost:3000/api/v1/carts/{cartId}/orders
	// with payload of List<PaymentHash> type
	// processes the cart with id={cartId}
	// if successful returns Http status OK else returns BAD_REQUEST
	@PostMapping("/carts/{cartId}/orders")
	public ResponseEntity<String> processCart(@RequestBody List<PaymentHash> paymentData, @PathVariable String cartId){
		JSONObject obj = filterParams(paymentData, cartId);
		HttpStatus status = orderService.placeOrder(obj);
		return new ResponseEntity<>(status);
	}

	// accepts Post request at localhost:3000/api/v1/orders/cancel
	// with payload of String id
	// cancels the order with id={orderId}
	// if successful returns Http status OK else returns BAD_REQUEST
	@PostMapping("/orders/cancel")
	public ResponseEntity<String> cancelOrder(@RequestBody String orderId){
		JSONObject obj =  new JSONObject();
		obj.put("id", orderId);
		HttpStatus status = orderService.cancelOrder(obj);
		return new ResponseEntity<>(status);
	}

	// KAFKA CONFIG
	// accepts GET request at localhost:3000/api/v1/orders/bulk/create
	// sends dummy orders to kafka
	// if successful returns Http status OK else returns BAD_REQUEST
	@GetMapping("/orders/bulk/create")
	public ResponseEntity<String> bulkOrderProcess(){
		HttpStatus status = orderService.createBulkOrders();
		return new ResponseEntity<>(status);
	}

	// KAFKA CONFIG
	// accepts GET request at localhost:3000/api/v1/orders/bulk/cancel
	// sends cancel all request
	// if successful returns Http status OK else returns BAD_REQUEST
	@GetMapping("/orders/bulk/cancel")
	public ResponseEntity<String> bulkOrderCancel(){
		HttpStatus status = orderService.cancelBulkOrders();
		return new ResponseEntity<>(status);
	}

	// filters the Payment data parameter and returns a JSON object
	// remove redundant fields
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
