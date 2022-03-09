package ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.model.OrderedItem;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
	
	public List<OrderedItem> findByOrderId(Long  orderId);

}
