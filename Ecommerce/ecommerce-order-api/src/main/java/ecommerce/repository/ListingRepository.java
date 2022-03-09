package ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.model.Listing;

// Listing repository for Listing
public interface ListingRepository extends JpaRepository<Listing, Long> {

	
}
