package ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.Cart;
import ecommerce.model.LineItem;
import ecommerce.repository.CartRepository;

// Cart Service with cart related business logic
// connected with cart repository
// connected with LineItem Service

@Service
public class CartService {
	@Autowired
	private CartRepository repository;
	@Autowired
	private LineItemService lineItemService;

	// get the List of line items of a cart
	// if successful returns List of line items else returns empty list of line items
	public List<LineItem> getLineItems(Long id) {
		Cart cart = repository.findById(id).get();
		return (List<LineItem>) lineItemService.getLineItemsByCartId(cart.getId());		
	}

	// gets the carts based on the cart id
	// if successful returns cart instance else returns new cart instance
    public Cart getCart(Long cartId) {
		return repository.findById(cartId).get();
    }

}
