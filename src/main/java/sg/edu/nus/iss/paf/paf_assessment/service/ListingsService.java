package sg.edu.nus.iss.paf.paf_assessment.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf.paf_assessment.model.DisplayListing;
import sg.edu.nus.iss.paf.paf_assessment.repository.ListingsRepository;

@Service
public class ListingsService {
    
    @Autowired
    ListingsRepository listingsRepo;

    public List<String> returnAllDistinctCountries(){
        return listingsRepo.returnAllDistinctCountries();
    }

    public List<Document> returnMatchedListings(String country, Integer noOfPerson, Float priceRangeMin, Float priceRangeMax){
        return listingsRepo.returnAllMatched(country, noOfPerson, priceRangeMin, priceRangeMax);
    }
    
    public List<DisplayListing> returnAllListingDisplay(String country, Integer noOfPerson, Float priceRangeMin, Float priceRangeMax){
        return listingsRepo.returnAllMatched(country, noOfPerson, priceRangeMin, priceRangeMax)
                .stream()
                .map(t->DisplayListing.JSONToObj(t))
                .toList();
    }

    public Document returnMatched(String _id){
        return listingsRepo.returnMatched(_id);
    }
}
