package ecommerce.producer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minidev.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ecommerce.lib.PaymentHash;
import ecommerce.model.Order;

@Service
public class OrderProducer {
    private RestTemplate restTemplate = new RestTemplate();
    
	public OrderProducer (RestTemplateBuilder restTemplateBuilder) {
	  this.restTemplate = restTemplateBuilder.build();
	}
	
	public HttpStatus placeOrder(JSONObject orderHash) {
		String url = "http://ecommerce-order-processing:3001/api/v1/orders";
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    ResponseEntity<String> response = this.restTemplate.postForEntity(url, orderHash, String.class);
	    return response.getStatusCode();
	   }
	public HttpStatus cancelOrder(JSONObject orderHash) {
		String url = "http://ecommerce-order-processing:3001/api/v1/orders/cancel";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = this.restTemplate.postForEntity(url, orderHash, String.class);
		return response.getStatusCode();

	}

	public HttpStatus addPayment(String orderId, JSONObject orderHash) {
		String url = "http://ecommerce-order-processing:3001/api/v1/payments/orders/"+orderId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = this.restTemplate.postForEntity(url, orderHash, String.class);
		return response.getStatusCode();

	}
}
