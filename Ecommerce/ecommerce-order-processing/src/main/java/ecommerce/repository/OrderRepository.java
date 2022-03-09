package ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.model.Order;

// Order repository for Order
public interface OrderRepository extends JpaRepository<Order, Long> {

}
