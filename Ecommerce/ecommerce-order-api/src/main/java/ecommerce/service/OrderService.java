package ecommerce.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ecommerce.model.Order;
import ecommerce.repository.OrderRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ecommerce.lib.ListPaymentData;

// Order Service with Order related business logic
// connected with KafkaTemplate to create bulk and single order
// defines the topic names for the same
@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
	private KafkaTemplate<String, ListPaymentData> bulkCreateOrderKafkaTemplate;

	@Autowired
	private KafkaTemplate<String, Long> bulkOrderCancelKafkaTemplate;

	@Value(value = "${bulkOrderCreate.topic.name}")
	private String bulkOrderCreateTopicName;

	@Value(value = "${bulkOrderCancel.topic.name}")
	private String orderCancelTopicName;

	// sets the rest template
	public OrderService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	// creates a single order
	// using the restful API
	// returns HTTP status
	public HttpStatus placeOrder(JSONObject orderHash) {
		String url = "http://ecommerce-order-processing:3001/api/v1/orders";
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    ResponseEntity<String> response = this.restTemplate.postForEntity(url, orderHash, String.class);
	    return response.getStatusCode();
	   }

	// cancels a single order
	// using the restful API
	// returns HTTP status
	public HttpStatus cancelOrder(JSONObject orderHash) {
		String url = "http://ecommerce-order-processing:3001/api/v1/orders/cancel";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = this.restTemplate.postForEntity(url, orderHash, String.class);
		return response.getStatusCode();

	}

	// KAFKA CONFIG
	// creates a bulk order request and sends to Kafka
	public void bulkOrderCreateRequest(ListPaymentData paymentInfo) {
		 bulkCreateOrderKafkaTemplate.send(bulkOrderCreateTopicName, paymentInfo);
	}

	// KAFKA CONFIG
	// cancels all the orders and sends to Kafka
	// KAFKA CONFIG
	// generate dummy orders for bulk carts created
	public void orderCancelRequest(Long orderId) {
		bulkOrderCancelKafkaTemplate.send(orderCancelTopicName, orderId);
	}

	// returns HTTP status
	public HttpStatus createBulkOrders(){
		ListPaymentData paymentData = new ListPaymentData();
		Random r = new Random();
		for(int i=0; i < 5; i++){
			Long id = Long.valueOf((r.nextInt((4 - 3) + 1) + 3));
			bulkOrderCreateRequest(paymentData.generateData(id));
		}
		return HttpStatus.OK;
	}

	// KAFKA CONFIG
	// cancels all the orders
	// set status = false
	// returns HTTP status
	public HttpStatus cancelBulkOrders(){
		Iterable<Order> itr = orderRepository.findAll();
		itr.forEach(i->orderCancelRequest(i.getId()));
		return HttpStatus.OK;
	}
}
