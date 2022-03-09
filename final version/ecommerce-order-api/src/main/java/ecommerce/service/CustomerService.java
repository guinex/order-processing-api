package ecommerce.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.Cart;
import ecommerce.model.Customer;
import ecommerce.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;
	
	public List<Customer> getAllCustomers(){
		List<Customer> customers= new ArrayList<>();
		repository.findAll()
			.forEach(customers::add);
		return customers;
	}
	
	public Customer getCustomer(Long id) {
		try {
			return repository.findById(id).get();
		}catch(Exception exc){
			return new Customer();
		}	
		
	}
	
	public boolean createCustomer(Customer customer) {
		try {
			Cart cart = new Cart();
			customer.setCart(cart);
			repository.save(customer);
			return true;
		}catch(Exception exc){
			return false;
		}	

	}

	public boolean updateCustomer(Customer customer) {
		try {
			repository.save(customer);
			return true;
		}catch(Exception exc){
			return false;
		}	
		
	}

	public boolean deleteCustomer(Long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception exc){
			return false;
		}	
		
	}

	public Customer getCustomer(Cart cart) {
		try {
			return repository.findByCartId(cart.getId());
		}catch(Exception exc){
			return new Customer();
		}	
	}
	
}
