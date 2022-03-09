package ecommerce.controller;

import java.util.List;

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
	
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		if(customers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(customers, HttpStatus.OK);		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String id){
		Customer customer = customerService.getCustomer(convertToLong(id));
		if(customer.getId() > 0) {
			 return new ResponseEntity<>(customer, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 }
	}
	
//	@PostMapping("/")
//	public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
//		boolean success = customerService.createCustomer(customer);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
//		boolean success = customerService.updateCustomer(customer);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteCustomer(@PathVariable String id){
//		boolean success = customerService.deleteCustomer(convertToLong(id));
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
		
}
