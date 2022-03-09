package ecommerce.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.model.Address;
import ecommerce.service.AddressService;


@RestController
@RequestMapping("/api/v1/customers")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}

	@GetMapping("/{customerId}/addresses")
	public ResponseEntity<List<Address>> getAllAddress(@PathVariable String customerId){
		List<Address> addresses = addressService.getAllAddress(convertToLong(customerId));
		if(addresses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(addresses, HttpStatus.OK);	
	}
	
	@GetMapping("/{customerId}/addresses/{id}")
	public ResponseEntity<Address> getAddress(@PathVariable String id){
		Address address = addressService.getAddress(convertToLong(id));
		if(address == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(address, HttpStatus.OK);	
	}
	
//	@PostMapping("/{customerId}/addresses}")
//	public ResponseEntity<String> addProduct(@RequestBody Address address, @PathVariable String customerId){
//		boolean success = addressService.addAddress(address);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
//
//	@PutMapping("/{customerId}/addresses/{id}")
//	public ResponseEntity<String>  updateProduct(@RequestBody Address address, @PathVariable String customerId, @PathVariable String id){
//		boolean success = addressService.updateAddress(address);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//	}
	
//	@DeleteMapping("/{customerId}/addresses/{id}")
//	public ResponseEntity<String>  deleteListing(@PathVariable String id){
//		boolean success = addressService.deleteAddress(convertToLong(id));
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//	}
		
}
