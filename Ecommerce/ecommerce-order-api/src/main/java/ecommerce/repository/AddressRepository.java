package ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.model.Address;


// Address repository for Address
public interface AddressRepository extends JpaRepository<Address, Long> {

	public Iterable<Address> findByCustomerId(Long customerId);
}
