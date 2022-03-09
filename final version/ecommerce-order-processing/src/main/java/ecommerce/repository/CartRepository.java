package ecommerce.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
