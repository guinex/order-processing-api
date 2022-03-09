package ecommerce.controller;

import ecommerce.lib.FilterParams;
import ecommerce.lib.ListPaymentData;
import ecommerce.lib.PaymentHash;
import ecommerce.model.Order;
import ecommerce.model.OrderedItem;
import ecommerce.service.OrderService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
			
	@GetMapping("")
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	@GetMapping("/{id}")
	public Order getOrder(@PathVariable String id){
		return orderService.getOrder(convertToLong(id));
	}
	
	@PostMapping("")
	public HttpStatus createOrder(@RequestBody JSONObject entity){
		try{
			System.out.println(entity.toString());
			ListPaymentData paymentData = FilterParams.filterListPaymentParams(entity);
			System.out.println("filtered params");
			orderService.createOrder(Long.parseLong(paymentData.id), paymentData.paymentDetails);
			return HttpStatus.OK;
		}catch(Exception exc){
			System.out.println(exc);
			return HttpStatus.BAD_REQUEST;
		}
	}

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

	@PutMapping("/{id}")
	public void updateOrder(@RequestBody Order order){
		orderService.updateOrder(order);
	}		
	
	@PutMapping("/{id}/payment")
	public void updateOrderPayment(@RequestBody PaymentHash paymentHash, @PathVariable String id){
		orderService.updateOrderPayment(convertToLong(id), paymentHash);
	}	
	
	@PutMapping("/{id}/orderItem")
	public void updateOrderByItem(@RequestBody OrderedItem orderedItem, @PathVariable String id){
		orderService.updateOrderByItem(convertToLong(id), orderedItem);
	}	
	
	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable String id){
		orderService.deleteOrder(convertToLong(id));
	}	
		
}
