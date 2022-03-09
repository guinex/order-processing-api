package ecommerce.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Collection<Product> findByListingId(Long listingId);
}
