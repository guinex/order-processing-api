package ecommerce.repository;

import ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// Order repository for Order
public interface OrderRepository extends JpaRepository<Order, Long> {

}
