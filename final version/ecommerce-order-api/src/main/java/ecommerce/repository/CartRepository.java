package ecommerce.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ecommerce.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
}
