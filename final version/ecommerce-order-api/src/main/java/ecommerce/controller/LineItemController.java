package ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecommerce.model.Cart;
import ecommerce.model.LineItem;
import ecommerce.model.Product;
import ecommerce.service.LineItemService;

@RestController
@RequestMapping("/api/v1/lineItems")
public class LineItemController {
	
	@Autowired
	private LineItemService lineItemService;
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}
	
//	@GetMapping("")
//	public ResponseEntity<List<LineItem>> getAllProduct(){
//		List<LineItem> products = lineItemService.getAllLineItems();
//		if(products.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity<>(products, HttpStatus.OK);
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<LineItem> getLineItem(@PathVariable String id){
//		LineItem lineItem = lineItemService.getLineItem(convertToLong(id));
//		if(!lineItem.isNew()) {
//			 return new ResponseEntity<>(lineItem, HttpStatus.OK);
//		 }
//		 else {
//			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		 }
//	}
//
//	@PostMapping("/")
//	public ResponseEntity<String> createLineItem(@RequestBody Product product, @RequestBody Cart cart, @PathVariable String quantity){
//		boolean success = lineItemService.createLineItem(cart, product, Integer.parseInt(quantity));
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<String> updateLineItem(@RequestBody LineItem lineItem){
//		boolean success = lineItemService.updateLineItem(lineItem);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteListing(@PathVariable String id){
//		LineItem item = lineItemService.getLineItem(convertToLong(id));
//		boolean success = lineItemService.deleteLineItem(item);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}

}
