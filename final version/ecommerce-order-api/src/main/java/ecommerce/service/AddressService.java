package ecommerce.service;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.Address;
import ecommerce.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;
	
	public List<Address> getAllAddress(Long CustomerId){
		List<Address> address= new ArrayList<>();
		repository.findByCustomerId(CustomerId)
			.forEach(address::add);
		return address;
	}
	
	public Address getAddress(Long id) {
		try {
			return (Address) repository.findById(id).get();
		}catch(Exception exc){
			Address address = new Address();
			return address;
		}
	}
	
	public boolean addAddress(Address address) {
		try {
			repository.save(address);
			return true;
		}catch(Exception exc){
			System.out.println(exc.getMessage());
			return false;
		}
	}

	public boolean updateAddress(Address address) {
		try {
			repository.save(address);
			return true;
		}catch(Exception exc){
			System.out.println(exc.getMessage());
			return false;
		}	
	}

	public boolean deleteAddress(Long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception exc){
			return false;
		}			

	}
	
}
