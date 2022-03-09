package ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.model.Listing;

public interface ListingRepository extends JpaRepository<Listing, Long> {

	
}
