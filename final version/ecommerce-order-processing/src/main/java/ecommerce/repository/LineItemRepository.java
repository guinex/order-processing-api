package ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.model.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {

	List<LineItem> getLineItemsByCartId(Long cartId);

	
}
