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
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}

	// accepts GET request at localhost:3000/api/v1/products/listings/{ListingId}
	// returns product with ListingId={listingId}
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("/products/listings/{listingId}")
	public ResponseEntity<List<Product>> getAllProduct(@PathVariable String listingId){
		List<Product> products = productService.getAllProducts(convertToLong(listingId));
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);		
	}

	// accepts GET request at localhost:3000/api/v1/listings/{ListingId}/products/{id}
	// returns the product with id={id} of the listing with ListingId={ListingId}
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("/listings/{ListingId}/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable String id){
		Product product = productService.getProduct(convertToLong(id));
		if(product.getId() > 0) {
			 return new ResponseEntity<>(product, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }		
	}
	// accepts Post request at localhost:3000/api/v1//listing/{listingId}/}/products
	// with payload of product type
	// creates new product for listing with id={ListingId}
	// if successful returns Http status CREATED else returns BAD_REQUEST
	@PostMapping("/listing/{listingId}/}/products")
	public ResponseEntity<String> addProduct(@RequestBody Product product, @PathVariable String listingId){
		product.setListing(new Listing(convertToLong(listingId), ""));
		boolean success = productService.addProduct(product);
		if(success) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
		
}
