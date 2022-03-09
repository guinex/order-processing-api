package ecommerce.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.LineItem;
import ecommerce.model.Order;
import ecommerce.model.OrderedItem;
import ecommerce.repository.OrderedItemRepository;

@Service
public class OrderedItemService {
	@Autowired
	private OrderedItemRepository repository;
	@Autowired
	private LineItemService lineItemService;	

	public List<OrderedItem> getAllOrderedItems(){
		List<OrderedItem> orderedItem= new ArrayList<>();
		repository.findAll()
			.forEach(orderedItem::add);
		return orderedItem;
	}
	
	public OrderedItem getOrderedItem(Long id) {
		return repository.findById(id).get();
		
	}
	
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
		return orderedItem;
	}
	
	public void saveOrderedItem(OrderedItem orderedItem) {
		repository.save(orderedItem);
	}

	public void updateOrderedItem(OrderedItem orderedItem, Long orderId) {
		//repository.save(orderedItem);
		
	}

	public void deleteOrderedItem(Long id) {
		repository.deleteById(id);
		return;
	}

	public OrderedItem processOrderedItems(List<LineItem> items, Order order) {
		OrderedItem orderedItem = null;
		return orderedItem;
	}
	
}
