package ecommerce.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.Address;
import ecommerce.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;
	
	public Address getAddress(Long id) {
		return (Address) repository.findById(id).get();
	}

	
}
