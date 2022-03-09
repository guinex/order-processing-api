package ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ecommerce.model.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {

	Iterable<LineItem> findByCartId(Long cartId);
	Iterable<LineItem> findByProductId(Long productId);
	LineItem findByCartIdAndProductId(Long cartId, Long productId);
	List<LineItem> getLineItemsByCartId(Long cartId);
	
}
