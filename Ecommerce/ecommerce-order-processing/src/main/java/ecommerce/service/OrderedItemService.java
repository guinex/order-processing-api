package ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.LineItem;
import ecommerce.model.Order;
import ecommerce.model.OrderedItem;
import ecommerce.repository.OrderedItemRepository;

// OrderedItem Service with OrderedItem related business logic
// connected with OrderedItem repository
// connected with lineItem Service
@Service
public class OrderedItemService {
	@Autowired
	private OrderedItemRepository repository;
	@Autowired
	private LineItemService lineItemService;

	// get all OrderedItem
	// returns list of OrderedItem on success
	// otherwise return empty List
	public List<OrderedItem> getAllOrderedItems(){
		List<OrderedItem> orderedItem= new ArrayList<>();
		repository.findAll()
			.forEach(orderedItem::add);
		return orderedItem;
	}

	// get OrderedItem with id={id}
	// returns  OrderedItem on success
	// otherwise return new instance
	public OrderedItem getOrderedItem(Long id) {
		try
		{
			return repository.findById(id).get();

		}catch(Exception exc){
			return new OrderedItem();
		}

	}

	//on order create it builds order
	// apply tax logic, calculates subtotal
	// save orderedItem in LineItem
	// saves orderedItem
	public OrderedItem buildOrderedItems(List<LineItem> items, Order order) {
		OrderedItem orderedItem = new OrderedItem();
		orderedItem.setOrder(order);
		orderedItem = repository.save(orderedItem);
		double tax = 0.0;
		double total = 0.0;
		double subTotal;
		for(int i = 0; i < items.size(); i++) {
			subTotal = items.get(i).getTotal();
			 if(subTotal > 50.0) {
				 tax = tax + 2.0;
			 }
			 total = total + subTotal;
			 items.get(i).setOrderedItem(orderedItem);
			 lineItemService.save(items.get(i));
		}
		orderedItem.setSubTotal(total);
		orderedItem.setTax(tax);
		repository.save(orderedItem);
		repository.flush();
		return orderedItem;
	}

}
