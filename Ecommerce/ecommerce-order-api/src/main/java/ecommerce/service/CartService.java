package ecommerce.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ecommerce.model.Cart;
import ecommerce.model.Customer;
import ecommerce.model.LineItem;
import ecommerce.model.Product;
import ecommerce.repository.CartRepository;

// Cart Service with cart related business logic
// connected with cart repository
// connected with product Service
// connected with LineItem Service
// connected with Customer Service
@Service
public class CartService {
	@Autowired
	private CartRepository repository;
	@Autowired
	private ProductService productService;	
	@Autowired
	private LineItemService lineItemService;	
	@Autowired
	private CustomerService customerService;

	// gets the carts based on the cart id
	// if successful returns cart instance else returns new cart instance
	public Cart getCart(Long id)
	{
		try
		{
			return (Cart) repository.findById(id).get();
		}
		catch(Exception exc)
		{
			Cart cart = new Cart();
			return cart;
		}		
	}

	// inserts a new cart in Cart table
	// flushed the data after insert
	// if successful returns cart else returns new cart instance
	public Cart createCart(Cart cart)
	{
		try
		{
			Cart c = repository.save(cart);
			repository.flush();
			return c;
		}
		catch(Exception exc)
		{
			return new Cart();
		}
	}

	// updates a cart in Cart table
	// flushed the data after insert
	// if successful returns true else returns false
	public boolean updateCart(Cart cart)
	{
		try
		{
			repository.save(cart);
			repository.flush();
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}			
	}

	// deletes a new cart in Cart table using id
	// if successful returns true else returns false
	public boolean deleteCart(Long id)
	{
		try
		{
			repository.deleteById(id);
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
	}

	// inserts a new lineItem using cartId, productId, quantity
	// if successful returns true else prints exception and returns false
	public boolean addProduct(Long cartId, Long productId, int quantity)
	{
		try
		{
			Product product = productService.getProduct(productId);
			Cart cart = repository.findById(cartId).get();
			boolean success = lineItemService.createLineItem(cart, product, quantity);
			return success;
		}
		catch(Exception exc)
		{
			System.out.println(exc);
			return false;
		}						
	}

	// removes a lineItem from the cart based on productId and cartId
	// if successful returns true else returns false
	public boolean removeProduct(Long cartId, Long productId)
	{
		try
		{
			lineItemService.deleteLineItem(
					lineItemService.getLineItem(cartId, productId)
			);
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
	}

	// get the List of line items of a cart
	// if successful returns List of line items else returns empty list of line items
	public List<LineItem> getLineItems(Long id)
	{
		try
		{
			Cart cart = repository.findById(id).get();
			return (List<LineItem>) lineItemService.getLineItemsByCartId(cart.getId());
		}
		catch(Exception exc)
		{
			List<LineItem> lineItems = new ArrayList<>();
			return lineItems;
		}
	}

	// gets the customer of the cart
	// if successful returns customer else returns new customer instance
	public Customer getCustomer(Cart cart)
	{
		try
		{
			return customerService.getCustomer(cart);
		}
		catch(Exception exc) {
			return new Customer();
		}
	}

	// TESTING APACHE KAFKA
	// creates bunch of line items for carts (id: 1..4)
	// sets random quantity, product
	// returns OK status on success otherwise returns INTERNAL_SERVER_ERROR
	public HttpStatus createBulkCarts()
	{
		try
		{
			addProduct(1L, 1L, 1);
			addProduct(2L, 2L, 2);
			addProduct(3L, 1L, 4);
			addProduct(4L, 2L, 3);
			addProduct(1L, 3L, 1);
			addProduct(2L, 4L, 6);
			addProduct(3L, 3L, 2);
			addProduct(4L, 4L, 1);
			return HttpStatus.CREATED;
		}
		catch(Exception exc)
		{
			System.out.println(exc.getCause());
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
}
