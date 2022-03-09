package ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ecommerce.model.Cart;
import ecommerce.service.CartService;


@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;

	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}

	// accepts Get request at localhost:3000/api/v1/cart/{id}
	// returns the cart with id={id}
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("/{id}")
	public ResponseEntity<Cart> getCart(@PathVariable String id){
		 Cart cart = cartService.getCart(convertToLong(id));
		 if(cart.getId() > 0) {
			 return new ResponseEntity<>(cart, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }
	}

	// accepts Post request at localhost:3000/api/v1/carts/{cartId}/product/{productId}/add/{quantity}
	// creates line item for that cart
	// if successful returns Http status CREATED else returns BAD_REQUEST
	@PostMapping("/{cartId}/product/{productId}/add/{quantity}")
	public ResponseEntity<String> addProduct(@PathVariable String quantity, @PathVariable String cartId, @PathVariable String productId){
		boolean success = cartService.addProduct(convertToLong(cartId), convertToLong(productId), Integer.parseInt(quantity));
		if(success) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);		
	}

	// KAFKA CONFIG
	// accepts Post request at localhost:3000/api/v1/carts/create-carts
	// creates dummy line_items
	// if successful returns Http status CREATED else returns BAD_REQUEST
	@GetMapping("/create-carts")
	public ResponseEntity<String> bulkCartProcess(){
		try{
			HttpStatus status = cartService.createBulkCarts();
			return new ResponseEntity<>(status);
		}catch(Exception exc){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// accepts Post request at localhost:3000/api/v1/carts/{cartId}/remove/{productId}
	// removes line item with product id {productId}
	// if successful returns Http status OK else returns BAD_REQUEST
	@PostMapping("/{cartId}/remove/{productId}")
	public ResponseEntity<String> removeProduct(@PathVariable String cartId, @PathVariable String productId){
		boolean success = cartService.removeProduct(convertToLong(cartId), convertToLong(productId));
		if(success) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
