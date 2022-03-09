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
		
	@GetMapping("")
	public ResponseEntity<List<Listing>> getAllList(){ 
		List<Listing> listings = listingService.getAllListings();
		if(listings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listings, HttpStatus.OK);
		
	}
	
	@GetMapping("/listings/{id}")
	public ResponseEntity<Listing> getListing(@PathVariable String id){
		Listing listing = listingService.getListing(convertToLong(id));
		if(listing.getId() > 0) {
			 return new ResponseEntity<>(listing, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 }
	}	
	
//	@PostMapping("/listings")
//	public ResponseEntity<String> addListing(@RequestBody Listing listing){
//		boolean success = listingService.addListing(listing);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
	
//	@PutMapping("/listings/{id}")
//	public ResponseEntity<String> updateListing(@RequestBody Listing listing, @PathVariable String id){
//		boolean success = listingService.updateListing(listing);
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
//
//	@DeleteMapping("/listings/{id}")
//	public ResponseEntity<String> deleteListing(@PathVariable String id){
//		boolean success = listingService.deleteListing(convertToLong(id));
//		if(success) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
		
}
