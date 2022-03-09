package ecommerce.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.Customer;
import ecommerce.model.Address;
import ecommerce.repository.AddressRepository;

// Address Service is liked to Address repository and customer service
// All the Address related business logic are defined below
@Service
public class AddressService
{
	@Autowired
	private AddressRepository repository;
	@Autowired
	private CustomerService customerService;

	// Retrieve all the Addresses based on customer id provided
	// returns List of all the addresses of the customer with id={id}
	public List<Address> getAllAddress(Long CustomerId)
	{
		List<Address> address= new ArrayList<>();
		repository.findByCustomerId(CustomerId)
			.forEach(address::add);
		return address;
	}

	// Retrieve the Address based on customer id provided
	// returns the address of the customer with id={id}
	public Address getAddress(Long id)
	{
		try {
			return (Address) repository.findById(id).get();
		}catch(Exception exc){
			Address address = new Address();
			return address;
		}
	}

	// set customer and insert a new address in Address table
	// flushed the data after insert
	// if successful returns true else returns false
	public boolean addAddress(Address address, Long customerId)
	{
		try {
			Customer customer = customerService.getCustomer(customerId);
			address.setCustomer(customer);
			repository.save(address);
			repository.flush();
			return true;
		}catch(Exception exc){
			System.out.println(exc.getMessage());
			return false;
		}
	}

	// updates an address in Address table
	// flushed the data after insert
	// if successful returns true else returns false
	public boolean updateAddress(Address address)
	{
		try {
			repository.save(address);
			repository.flush();
			return true;
		}catch(Exception exc){
			System.out.println(exc.getMessage());
			return false;
		}	
	}

	// deletes an address in Address table by its id
	// if successful returns true else returns false
	public boolean deleteAddress(Long id)
	{
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception exc){
			return false;
		}
	}
}
