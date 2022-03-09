package ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.Cart;
import ecommerce.model.LineItem;
import ecommerce.repository.CartRepository;
@Service
public class CartService {
	@Autowired
	private CartRepository repository;
	@Autowired
	private LineItemService lineItemService;	

	public List<LineItem> getLineItems(Long id) {
		Cart cart = repository.findById(id).get();
		return (List<LineItem>) lineItemService.getLineItemsByCartId(cart.getId());		
	}

    public Cart getCart(Long cartId) {
		return repository.findById(cartId).get();
    }
}
