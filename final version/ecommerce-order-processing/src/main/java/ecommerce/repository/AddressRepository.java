package ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
