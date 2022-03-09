package ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.Cart;
import ecommerce.model.Customer;
import ecommerce.repository.CustomerRepository;

// Customer Service with Customer related business logic
// connected with Customer repository
// connected with cart Service
@Service
public class CustomerService
{
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private CartService cartService;

	// gets the List of customers
	// if successful returns lists of customers else returns empty list of customers
	public List<Customer> getAllCustomers()
	{
		List<Customer> customers= new ArrayList<>();
		repository.findAll()
			.forEach(customers::add);
		return customers;
	}

	// gets the customer based on the customer id
	// if successful returns customer instance else returns new customer instance
	public Customer getCustomer(Long id)
	{
		try
		{
			return repository.findById(id).get();
		}
		catch(Exception exc)
		{
			return new Customer();
		}	
		
	}

	// inserts a new customer
	// flushed the data after insert
	// creates a new cart for that customer
	// if successful returns true else returns false
	public boolean createCustomer(Customer customer)
	{
		try
		{
			Customer c = repository.save(customer);
			repository.flush();
			Cart cart = cartService.createCart(new Cart(c));
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
	}

	// updates a customer
	// if successful returns true else returns false
	public boolean updateCustomer(Customer customer)
	{
		try
		{
			repository.save(customer);
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
	}

	// deletes a customer
	// if successful returns true else returns false
	public boolean deleteCustomer(Long id)
	{
		try
		{
			repository.deleteById(id);
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
	}

	// get customer by cart id
	// returns the found customer else returns a new customer object
	public Customer getCustomer(Cart cart)
	{
		try
		{
			return repository.findByCartId(cart.getId());
		}
		catch(Exception exc)
		{
			return new Customer();
		}	
	}
}
