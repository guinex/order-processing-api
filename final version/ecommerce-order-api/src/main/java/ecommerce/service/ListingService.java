package ecommerce.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.Listing;
import ecommerce.repository.ListingRepository;

@Service
public class ListingService {
	@Autowired
	private ListingRepository listingRepository;

	public List<Listing> getAllListings(){
		List<Listing> listings= new ArrayList<>();
		listingRepository.findAll()
			.forEach(listings::add);
		return listings;
	}
	
	public Listing getListing(Long id) {
		try {
			return listingRepository.findById(id).get();
		}catch(Exception exc){
			return new Listing();
		}		
		
	}
	
	public boolean addListing(Listing l) {
		try {
			listingRepository.save(l);
			return true;
		}catch(Exception exc){
			return false;
		}		
		
	}

	public boolean updateListing(Listing listing) {
		try {
			listingRepository.save(listing);
			return true;
		}catch(Exception exc){
			return false;
		}		
		
	}

	public boolean deleteListing(Long id) {
		try {
			listingRepository.deleteById(id);
			return true;
		}catch(Exception exc){
			return false;
		}		
	}

	
}