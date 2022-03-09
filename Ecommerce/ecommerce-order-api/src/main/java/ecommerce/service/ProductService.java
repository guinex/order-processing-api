package ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;

// Product Service with Product related business logic
// connected to product repository
@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;

	// gets the List of Products
	// if successful returns lists of Products else returns empty list of Products
	public List<Product> getAllProducts(Long ListingId){
		return new ArrayList<>(repository.findByListingId(ListingId));
	}

	// gets the Product based on id
	// if successful returns product else returns new instance of product
	public Product getProduct(Long id) {
		try {
			return repository.findById(id).get();
		}catch(Exception exc){
			return new Product();
		}	

	}

	// create new product
	// flushes the data
	// if successful returns true else returns false
	public boolean addProduct(Product p) {
		try {
			repository.save(p);
			repository.flush();
			return true;
		}catch(Exception exc){
			return false;
		}	

	}

	// updates the listing
	// if successful returns true else returns false
	public boolean updateProduct(Product product) {
		try {
			repository.save(product);
			return true;
		}catch(Exception exc){
			return false;
		}	
	}

	// deletes the product
	// if successful returns true else returns false
	public boolean deleteProduct(Long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception exc){
			return false;
		}	
		
	}


}
