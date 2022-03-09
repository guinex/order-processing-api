package ecommerce.controller;

import ecommerce.lib.FilterParams;
import ecommerce.lib.ListPaymentData;
import ecommerce.model.Order;
import ecommerce.service.OrderService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}

	// accepts GET request at localhost:3000/api/v1/orders
	// returns all the orders
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders = orderService.getAllOrders();
		if(orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// accepts GET request at localhost:3000/api/v1/orders/{id}
	// returns the  orders
	// if successful returns Http status OK else returns BAD_REQUEST
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable String id){
		Order order = orderService.getOrder(convertToLong(id));
		if(order.getId() == null) {
			return new ResponseEntity<>(order, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	// accepts POST request at localhost:3000/api/v1/orders/{id}
	// creates order
	// if successful returns Http status CREATED else returns BAD_REQUEST
	@PostMapping("")
	public HttpStatus createOrder(@RequestBody JSONObject entity){
		try{
			System.out.println(entity.toString());
			ListPaymentData paymentData = FilterParams.filterListPaymentParams(entity);
			System.out.println("filtered params");
			orderService.createOrder(paymentData.id, paymentData.paymentDetails);
			return HttpStatus.OK;
		}catch(Exception exc){
			System.out.println(exc);
			return HttpStatus.BAD_REQUEST;
		}
	}

	// accepts POST request at localhost:3000/api/v1/orders/cancel
	// cancels order
	// if successful returns Http status OK else returns BAD_REQUEST
	@PostMapping("/cancel")
	public HttpStatus cancelOrder(@RequestBody JSONObject entity){
		try{
			System.out.println(entity.toString());
			Long orderId = FilterParams.filterById(entity);
			orderService.cancelOrder(orderId);
			return HttpStatus.OK;
		}catch(Exception exc){
			System.out.println(exc);
			return HttpStatus.BAD_REQUEST;
		}
	}

	// KAFKA CONFIG
	// kafka listner to topic name orders
	// creates orders
	@KafkaListener(topics = "${orderCreate.topic.name}", containerFactory = "orderCreateKafkaListenerContainerFactory")
	public void orderCreateListener(ListPaymentData paymentInfo) {
		System.out.println("Received paymentInfo message: " + paymentInfo.getId() + paymentInfo.getPaymentHash());
		orderService.createOrder(paymentInfo.getId(), paymentInfo.getPaymentDetails());
	}

	// KAFKA CONFIG
	// kafka listner to topic name cancelOrder
	// cancels orders
	@KafkaListener(topics = "${orderCancel.topic.name}", containerFactory = "orderCancelKafkaListenerContainerFactory")
	public void orderCancelListener(Long id) {
		orderService.cancelOrder(id);
	}

}
