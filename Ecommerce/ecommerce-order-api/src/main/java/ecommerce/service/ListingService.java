package ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.model.Listing;
import ecommerce.repository.ListingRepository;

// Listing Service with Listing related business logic
// connected with Listing repository
@Service
public class ListingService {
	@Autowired
	private ListingRepository listingRepository;

	// gets the List of Listings
	// if successful returns lists of Listings else returns empty list of Listings
	public List<Listing> getAllListings(){
		List<Listing> listings= new ArrayList<>();
		listingRepository.findAll()
			.forEach(listings::add);
		return listings;
	}

	// gets the Listing based on the Listing id
	// if successful returns Listing instance else print cause and returns new Listing instance
	public Listing getListing(Long id) {
		try {
			return listingRepository.findById(id).get();
		}catch(Exception exc){
			System.out.println(exc.getCause());
			return new Listing();
		}		
		
	}

	// creates new listing and flushes the data
	// if successful returns true else returns false
	public boolean addListing(Listing l) {
		try {
			listingRepository.save(l);
			listingRepository.flush();
			return true;
		}catch(Exception exc){
			return false;
		}
	}

	// updates new listing and flushes the data
	// if successful returns true else returns false
	public boolean updateListing(Listing listing) {
		try {
			listingRepository.save(listing);
			listingRepository.flush();
			return true;
		}catch(Exception exc){
			return false;
		}		
		
	}

	// deletes the listing
	// if successful returns true else returns false
	public boolean deleteListing(Long id) {
		try {
			listingRepository.deleteById(id);
			return true;
		}catch(Exception exc){
			return false;
		}		
	}

	
}