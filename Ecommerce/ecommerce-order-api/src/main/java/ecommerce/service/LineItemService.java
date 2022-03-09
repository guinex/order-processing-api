package ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.Cart;
import ecommerce.model.LineItem;
import ecommerce.model.Product;
import ecommerce.repository.LineItemRepository;

// LineItem Service with LineItem related business logic
// connected with LineItem repository
@Service
public class LineItemService {
	@Autowired
	private LineItemRepository lineItemRepository;

	// gets the lineItem based on the lineItem id
	// if successful returns lineItem instance else print cause and returns new lineItem instance
	public LineItem getLineItem(Long id) {
		LineItem lineItem = null;
		try {
			lineItem = lineItemRepository.findById(id).get();
		}catch(Exception exc){
			System.out.println(exc.getCause());
		}
		if (lineItem == null)
		{
			lineItem = new LineItem();
		}
		return lineItem;
	}

	// gets the lineItem based on the product id and cart id
	// if successful returns lineItem instance else print cause and returns new lineItem instance
	public LineItem getLineItem(Long cartId, Long productId) {
		LineItem lineItem = null;
		try {
			lineItem = lineItemRepository.findByCartIdAndProductId(cartId, productId);
		}catch(Exception exc){
			System.out.println(exc.getCause());
		}
		if (lineItem == null)
		{
			lineItem = new LineItem();
		}

		return lineItem;
	}

	// updates a lineItem
	// if successful returns true else returns false
	public boolean updateLineItem(LineItem lineItem)
	{
		try
		{
			lineItemRepository.save(lineItem);
			lineItemRepository.flush();
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
	}

	// deletes a lineItem by id
	// if successful returns true else returns false
	public boolean deleteProduct(Long id)
	{
		try
		{
			lineItemRepository.deleteById(id);
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
	}

	// creates a new lineItem
	// check if it exists if yes return that item
	// if no, create a new one
	// if successful returns true else returns false
	public boolean createLineItem(Cart cart, Product product, int quantity)
	{
		try
		{
			LineItem lineItem = getLineItem(cart.getId(),product.getId());
			if(lineItem.isNew())
			{
				lineItem.setName(product.getName());
				lineItem.setDescription(product.getDescription()); 
				lineItem.setPrice(product.getPrice()); 
				lineItem.setQuantity(quantity); 
				lineItem.setProduct(product);
				lineItem.setCart(cart);				
			}
			lineItemRepository.save(lineItem);
			return true;
		}
		catch(Exception exc)
		{
			System.out.println(exc.getCause());
			return false;
		}
	}

	// deletes a lineItem
	// if successful returns true else returns false
	public boolean deleteLineItem(LineItem lineItem)
	{
		try
		{
			lineItemRepository.delete(lineItem);
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
	}

	// saves a lineItem
	// flushes the data
	// if successful returns true else returns false
	public boolean save(LineItem lineItem)
	{
		try
		{
			lineItemRepository.save(lineItem);
			lineItemRepository.flush();
			return true;
		}
		catch(Exception exc)
		{
			return false;
		}
	}

	// get lineItem by cartId
	// returns list of lineItems on success
	// otherwise return empty List
	public List<LineItem> getLineItemsByCartId(Long cartId)
	{
		try
		{
			return lineItemRepository.getLineItemsByCartId(cartId);
		}
		catch(Exception exc)
		{
			List<LineItem> items = new ArrayList<LineItem>();
			return items;
		}
	}

	// gets the List of LineItems
	// if successful returns lists of LineItems else returns empty list of LineItems
	public List<LineItem> getAllLineItems()
	{
		List<LineItem> items= new ArrayList<>();
		try
		{
		lineItemRepository.findAll()
			.forEach(items::add);
		return items;
		}
		catch(Exception exc)
		{
			System.out.println(exc.getCause());
			return items;
		}
	}
}
