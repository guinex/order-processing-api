package ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.model.Cart;

// Cart repository for Cart
public interface CartRepository extends JpaRepository<Cart, Long> {

}
