package ecommerce.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.Cart;
import ecommerce.model.Customer;
import ecommerce.model.LineItem;
import ecommerce.model.Product;
import ecommerce.repository.CartRepository;

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
	
	public Cart getCart(Long id) {
		try {
			return (Cart) repository.findById(id).get();
		}catch(Exception exc){
			Cart cart = new Cart();
			return cart;
		}		
	}
	
	public boolean createCart(Cart cart) {
		try {
			repository.save(cart);
			return true;
		}catch(Exception exc){
			return false;
		}		
	}

	public boolean updateCart(Cart cart) {
		try {
			repository.save(cart);
			return true;
		}catch(Exception exc){
			return false;
		}			
	}

	public boolean deleteCart(Long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception exc){
			return false;
		}				

	}

	public boolean addProduct(Long cartId, Long productId, int quantity) {
		try {
			Product product = productService.getProduct(productId);
			Cart cart = repository.findById(cartId).get();
//			System.out.println(productId);
//			System.out.println(cart.getId());
			boolean success = lineItemService.createLineItem(cart, product, quantity);
//			System.out.println(success);
			return success;
		}catch(Exception exc){
			System.out.println(exc);
			return false;
		}						
	}

	public boolean removeProduct(Long cartId, Long productId) {
		try {
			lineItemService.deleteLineItem(
					lineItemService.getLineItem(cartId, productId)
			);
			return true;
		}catch(Exception exc){
			return false;
		}				
		
	}

	public List<LineItem> getLineItems(Long id) {
		try {
			Cart cart = repository.findById(id).get();
			return (List<LineItem>) lineItemService.getLineItemsByCartId(cart.getId());
		}catch(Exception exc){
			List<LineItem> lineItems = new ArrayList<>();
			return lineItems;
		}				

	}

	
	public Customer getCustomer(Cart cart) {
		try {
			return customerService.getCustomer(cart);
		}catch(Exception exc){
			return new Customer();
		}				
		
	}
	
}
