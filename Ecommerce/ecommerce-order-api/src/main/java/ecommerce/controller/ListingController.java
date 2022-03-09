package ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ecommerce.model.Listing;
import ecommerce.service.ListingService;

@RestController
@RequestMapping("/api/v1/listings")
public class ListingController {
	
	@Autowired
	private ListingService listingService;
	
	
	private Long convertToLong(String id) {
		return Long.parseLong(id);
	}

	// accepts Get request at localhost:3000/api/v1/listings
	// returns all the listings
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("")
	public ResponseEntity<List<Listing>> getAllList(){ 
		List<Listing> listings = listingService.getAllListings();
		if(listings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(listings, HttpStatus.OK);
		
	}

	// accepts Get request at localhost:3000/api/v1/listings/{id}
	// returns the  listing with id={id}
	// if successful returns Http status OK else returns NO_CONTENT
	@GetMapping("/listings/{id}")
	public ResponseEntity<Listing> getListing(@PathVariable String id){
		Listing listing = listingService.getListing(convertToLong(id));
		if(listing.getId() > 0) {
			 return new ResponseEntity<>(listing, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }
	}
	// accepts Post request at localhost:3000/api/v1/listings
	// with payload of listing type
	// if successful returns Http status CREATED else returns BAD_REQUEST
	@PostMapping("/listings")
	public ResponseEntity<String> addListing(@RequestBody Listing listing){
		boolean success = listingService.addListing(listing);
		if(success) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}


}
