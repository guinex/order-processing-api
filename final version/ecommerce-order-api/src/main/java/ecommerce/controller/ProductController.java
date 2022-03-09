package ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecommerce.model.Listing;
import ecommerce.model.Product;
import ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/v1/listings")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}
			
	@GetMapping("/{id}/products")
	public ResponseEntity<List<Product>> getAllProduct(@PathVariable String id){
		List<Product> products = productService.getAllProducts(convertToLong(id));
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);		
	}
	
	@GetMapping("/{ListingId}/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable String id){
		Product product = productService.getProduct(convertToLong(id));
		if(product.getId() > 0) {
			 return new ResponseEntity<>(product, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 }		
	}
	
//	@PostMapping("/{listingId}/products}")
//	public ResponseEntity<String> addProduct(@RequestBody Product product, @PathVariable String listingId){
//		product.setListing(new Listing(convertToLong(listingId), ""));
//		boolean success = productService.addProduct(product);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
	
//	@PutMapping("/{listingId}/products/{id}")
//	public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable String listingId, @PathVariable String id){
//		product.setListing(new Listing(convertToLong(listingId), ""));
//		boolean success = productService.updateProduct(product);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
//
//	@DeleteMapping("/{listingId}/products/{id}")
//	public ResponseEntity<String> deleteListing(@PathVariable String id){
//		boolean success = productService.deleteProduct(convertToLong(id));
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//	}
		
}
