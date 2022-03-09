package ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.Cart;
import ecommerce.model.LineItem;
import ecommerce.model.Product;
import ecommerce.repository.LineItemRepository;

import javax.transaction.Transactional;

@Service
public class LineItemService {
	@Autowired
	private LineItemRepository lineItemRepository;

	public LineItem getLineItem(Long id) {
		try {
			return lineItemRepository.findById(id).get();
		}catch(Exception exc){
			return new LineItem();
		}			
	}
	
	public LineItem getLineItem(Long cartId, Long productId) {
		LineItem lineItem = null;
		try {
			lineItem = lineItemRepository.findByCartIdAndProductId(cartId, productId);
		}catch(Exception ignored){
			// Some relevant error
		}

		if (lineItem == null)
		{
			lineItem = new LineItem();
		}

		return lineItem;
	}

	public boolean updateLineItem(LineItem lineItem) {
		try {
			lineItemRepository.save(lineItem);
			return true;
		}catch(Exception exc){
			return false;
		}			

	}

	public boolean deleteProduct(Long id) {
		try {
			lineItemRepository.deleteById(id);
			return true;
		}catch(Exception exc){
			return false;
		}			
				
	}

	
	// creates a new lineItem with product and cart

	public boolean createLineItem(Cart cart, Product product, int quantity) {
		try {
			LineItem lineItem = getLineItem(cart.getId(),product.getId());
			if(lineItem.isNew()) {
				lineItem.setName(product.getName());
				lineItem.setDescription(product.getDescription()); 
				lineItem.setPrice(product.getPrice()); 
				lineItem.setQuantity(quantity); 
				lineItem.setProduct(product);
				lineItem.setCart(cart);				
			}
			lineItemRepository.save(lineItem);
			return true;
		}catch(Exception exc){
			System.out.println(exc);
			return false;
		}			

	}


		
	public boolean deleteLineItem(LineItem lineItem) {
		try {
			lineItemRepository.delete(lineItem);
			return true;
		}catch(Exception exc){
			return false;
		}			

	}

	public boolean save(LineItem lineItem) {
		try {
			lineItemRepository.save(lineItem);
			return true;
		}catch(Exception exc){
			return false;
		}			
		
		
	}

	public List<LineItem> getLineItemsByCartId(Long cartId) {
		try {
			return lineItemRepository.getLineItemsByCartId(cartId);
		}catch(Exception exc){
			List<LineItem> items = new ArrayList<LineItem>();
			return items;
		}			
	
	}

	public List<LineItem> getAllLineItems() {
		List<LineItem> items= new ArrayList<>();
		try {
		lineItemRepository.findAll()
			.forEach(items::add);
			return items;	
		}catch(Exception exc) {
			System.out.println(items);
			return items;
		}
	}
	
}
