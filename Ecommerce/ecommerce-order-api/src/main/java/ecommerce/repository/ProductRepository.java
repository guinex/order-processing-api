package ecommerce.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.model.Product;

// Product repository for Product
public interface ProductRepository extends JpaRepository<Product, Long> {

    Collection<Product> findByListingId(Long listingId);
}
