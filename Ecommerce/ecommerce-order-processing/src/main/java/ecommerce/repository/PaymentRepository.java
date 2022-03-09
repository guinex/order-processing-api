package ecommerce.repository;


import java.util.List;
import java.util.Optional;

//import java.util.List;


import ecommerce.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	public List<Payment> findByCustomerId(Long customerId);
	public List<Payment> findByOrderId(Long orderId);


	
}
