package ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.LineItem;
import ecommerce.repository.LineItemRepository;
@Service
public class LineItemService {
	@Autowired
	private LineItemRepository repository;
	
	public void save(LineItem lineItem) {
		repository.save(lineItem);
	}

	public List<LineItem> getLineItemsByCartId(Long cartId) {
		return repository.getLineItemsByCartId(cartId);
	}



}
