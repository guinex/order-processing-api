package ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.Address;
import ecommerce.repository.AddressRepository;

// Address Service is liked to Address repository and customer service
// All the Address related business logic are defined below
@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;

	// Retrieve the Address based on customer id provided
	// returns the address of the customer with id={id}
	public Address getAddress(Long id) {
		return (Address) repository.findById(id).get();
	}

	
}
