package ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ecommerce.model.OrderedItem;
import ecommerce.service.OrderedItemService;


@RestController
@RequestMapping("/api/v1/orderedItems")
public class OrderedItemController {
	
	@Autowired
	private OrderedItemService orderedItemService;
	
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}
				
	@GetMapping("")
	public List<OrderedItem> getAllProduct(){
		return orderedItemService.getAllOrderedItems();
	}
	
	@GetMapping("/{id}")
	public OrderedItem getProduct(@PathVariable String id){
		return orderedItemService.getOrderedItem(convertToLong(id));
	}
	

	@PutMapping(value="/{orderedItemId}/orders/{id}")
	public void updateOrderedItem(@RequestBody OrderedItem orderedItem, @PathVariable String orderId, @PathVariable String orderItemId){
		orderedItemService.updateOrderedItem(orderedItem, convertToLong(orderId));
	}	
	
	@DeleteMapping("/{id}/orders/{orderId}")
	public void deleteListing(@PathVariable String id){
		orderedItemService.deleteOrderedItem(convertToLong(id));
	}	
		
}
