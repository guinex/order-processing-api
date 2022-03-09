package ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//package imports
import ecommerce.model.Address;
import ecommerce.service.AddressService;

@RestController
@RequestMapping("/api/v1/customers")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	// converts string type ids to Long data type
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}

	// accepts Get request at localhost:3000/api/v1/customers/{id}/addresses
	// returns all the addresses of the customer with id={id}
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("/{customerId}/addresses")
	public ResponseEntity<List<Address>> getAllAddress(@PathVariable String customerId){
		List<Address> addresses = addressService.getAllAddress(convertToLong(customerId));
		if(addresses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(addresses, HttpStatus.OK);	
	}

	// accepts Post request at localhost:3000/api/v1/customers/{id}/addresses
	// with payload of address type
	// returns all the addresses of the customer with id={id}
	// if successful returns Http status CREATED else returns BAD_REQUEST
	@PostMapping("/{customerId}/addresses}")
	public ResponseEntity<String> addAddress(@RequestBody Address address, @PathVariable String customerId){
		boolean success = addressService.addAddress(address, convertToLong(customerId));
		if(success) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// accepts Delete request at localhost:3000/api/v1/customers/{id}/addresses
	// with payload of address type
	// returns Http status OK if successful else returns BAD_REQUEST
	@DeleteMapping("/{customerId}/addresses")
	public ResponseEntity<String>  deleteListing(@RequestBody int id){
		boolean success = addressService.deleteAddress(Long.valueOf(id));
		if(success) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}
		
}
