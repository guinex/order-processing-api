package ecommerce.controller;

import java.util.List;

import ecommerce.model.Cart;
import ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecommerce.model.Customer;
import ecommerce.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;


	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}

	// accepts Get request at localhost:3000/api/v1/customers
	// returns all the customers
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		if(customers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(customers, HttpStatus.OK);		
	}

	// accepts Get request at localhost:3000/api/v1/customers/{id}
	// returns  the customer with id={id}
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String id){
		Customer customer = customerService.getCustomer(convertToLong(id));
		if(customer.getId() > 0) {
			 return new ResponseEntity<>(customer, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }
	}
	// accepts Post request at localhost:3000/api/v1/customers/
	// with payload of Customer type
	// if successful returns Http status CREATED else returns BAD_REQUEST
	@PostMapping("/")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
		boolean success = customerService.createCustomer(customer);
		if(success) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
