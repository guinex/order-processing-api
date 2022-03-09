package ecommerce.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	public List<Product> getAllProducts(Long ListingId){
		return new ArrayList<>(repository.findByListingId(ListingId));
	}
	
	public Product getProduct(Long id) {
		try {
			return repository.findById(id).get();
		}catch(Exception exc){
			return new Product();
		}	

	}
	
	public boolean addProduct(Product p) {
		try {
			repository.save(p);
			return true;
		}catch(Exception exc){
			return false;
		}	

	}

	public boolean updateProduct(Product product) {
		try {
			repository.save(product);
			return true;
		}catch(Exception exc){
			return false;
		}	
	}
	
	public boolean deleteProduct(Long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch(Exception exc){
			return false;
		}	
		
	}


}
