package ecommerce.controller;
import java.util.List;

import javax.persistence.Table;

import ecommerce.lib.FilterParams;
import ecommerce.lib.PaymentData;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ecommerce.model.Payment;
import ecommerce.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
@Table(name = "payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}
		
	@GetMapping("/customers/{id}/")
	public List<Payment> getAllPayments(@PathVariable String id){
		return paymentService.getAllPayments(convertToLong(id));
	}

	@PostMapping("/orders/{orderId}")
	public HttpStatus createPayment(@RequestBody JSONObject entity){
		try{
			System.out.println(entity.toString());
			PaymentData paymentData = FilterParams.filterPaymentParams(entity);
			paymentService.createPayment(Long.parseLong(paymentData.id), paymentData.paymentDetails);
			return HttpStatus.OK;
		}catch(Exception exc){
			System.out.println(exc);
			return HttpStatus.BAD_REQUEST;
		}
	}

	@PostMapping(value="/cancel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus cancelPayment(@RequestBody JSONObject entity){
		try{
			System.out.println(entity.toString());
			Long paymentId = FilterParams.filterById(entity);
			paymentService.cancelPayment(paymentId);
			return HttpStatus.OK;
		}catch(Exception exc){
			System.out.println(exc);
			return HttpStatus.BAD_REQUEST;
		}
	}

	
}
