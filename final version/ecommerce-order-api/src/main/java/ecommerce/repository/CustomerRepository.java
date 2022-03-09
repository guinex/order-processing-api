package ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ecommerce.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public List<Customer> findByName(String name);

	public Customer findByCartId(Long id);


}
