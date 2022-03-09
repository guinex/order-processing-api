package ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ecommerce.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	public Iterable<Address> findByCustomerId(Long customerId);

}
