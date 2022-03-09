package ecommerce.service;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

// Payment Service with Payment related logic
// connected with KafkaTemplate to create single payment request
// defines the topic names for the same
@Service
public class PaymentService {
    private RestTemplate restTemplate = new RestTemplate();
//    paymentCreate.topic.name=payment
//    paymentCancel.topic.name=paymentCancel

    // creates a single payment
    // 1. using the restful API
    // 2. using kafka topic payment
    // returns HTTP status
    public HttpStatus addPayment(String orderId, JSONObject paymentHash) {
        String url = "http://ecommerce-order-processing:3001/api/v1/payments/orders/"+orderId;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, paymentHash, String.class);
        return response.getStatusCode();

    }

    // cancels a single payment
    // 1. using the restful API
    // 2. using kafka topic paymentCancel
    // returns HTTP status
    public HttpStatus cancelPayment(JSONObject paymentHash) {
        String url = "http://ecommerce-order-processing:3001/api/v1/payments/cancel";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, paymentHash, String.class);
        return response.getStatusCode();

    }
}