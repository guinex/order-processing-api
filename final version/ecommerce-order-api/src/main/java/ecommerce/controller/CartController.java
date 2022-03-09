package ecommerce.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import ecommerce.lib.PaymentHash;
import ecommerce.model.Cart;
import ecommerce.producer.OrderProducer;
import ecommerce.service.CartService;


@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;


//	@Autowired
//	private KafkaTemplate<String, String> template;
//		
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cart> getCart(@PathVariable String id){
		 Cart cart = cartService.getCart(convertToLong(id));
		 if(cart.getId() > 0) {
			 return new ResponseEntity<>(cart, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 }
	}

//	@PostMapping("/}")
//	public ResponseEntity<String> createCart(@RequestBody Cart cart){
//		boolean success = cartService.createCart(cart);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}

//	@PutMapping("/{cartId}")
//	public ResponseEntity<String> updateCart(@RequestBody Cart cart){
//		boolean success = cartService.updateCart(cart);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
//
//	@DeleteMapping("/{cartId}")
//	public ResponseEntity<String> deleteCart(@PathVariable String id){
//		boolean success = cartService.deleteCart(convertToLong(id));
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
	
	@PostMapping("/{cartId}/product/{productId}/add/{quantity}")
	public ResponseEntity<String> addProduct(@PathVariable String quantity, @PathVariable String cartId, @PathVariable String productId){
		boolean success = cartService.addProduct(convertToLong(cartId), convertToLong(productId), Integer.parseInt(quantity));
		if(success) {
			return new ResponseEntity<>(HttpStatus.OK);	
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
	}
	
//	@PostMapping("/{cartId}/remove/{productId}")
//	public ResponseEntity<String> removeProduct(@PathVariable String cartId, @PathVariable String productId){
//		boolean success = cartService.removeProduct(convertToLong(cartId), convertToLong(productId));
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
//


}
