package ecommerce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.model.Customer;

// Customer repository for Customer
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public List<Customer> findByName(String name);

	public Customer findByCartId(Long id);


}
