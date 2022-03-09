package ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.LineItem;
import ecommerce.repository.LineItemRepository;

// LineItem Service with LineItem related business logic
// connected with LineItem repository
@Service
public class LineItemService {
	@Autowired
	private LineItemRepository repository;

	// saves line item and then flush the data
	public void save(LineItem lineItem) {
		repository.save(lineItem);
		repository.flush();
	}

	// get lineItem by cartId
	// returns list of lineItems on success
	// otherwise return empty List
	public List<LineItem> getLineItemsByCartId(Long cartId) {
		return repository.getLineItemsByCartId(cartId);
	}

}
