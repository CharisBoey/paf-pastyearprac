package sg.edu.nus.iss.paf.paf_assessment.controller;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.paf.paf_assessment.model.Booking;
import sg.edu.nus.iss.paf.paf_assessment.model.Details;
import sg.edu.nus.iss.paf.paf_assessment.model.DisplayListing;
import sg.edu.nus.iss.paf.paf_assessment.model.Listings;
import sg.edu.nus.iss.paf.paf_assessment.service.ListingsService;
import sg.edu.nus.iss.paf.paf_assessment.service.mybnbService;

@Controller
@RequestMapping("/")
public class ListingsController {
    
    @Autowired
    ListingsService listingSvc;

    @Autowired
    mybnbService mybnbSvc;
    
    @GetMapping("/landingpage")
    public String landingpage(Model model, HttpSession sess){
        Listings listings = new Listings();
        List<String> listOfCountries = listingSvc.returnAllDistinctCountries();
        model.addAttribute("listings", listings);
        model.addAttribute("listOfCountries", listOfCountries);
        sess.setAttribute("listOfCountries", listOfCountries);
        return "landingpage";
    }

    
    @PostMapping("/landingpage")
    public String landingpage(@Valid @ModelAttribute Listings listings, BindingResult result, Model model, HttpSession sess){
        //System.out.println("########################" + result.toString());
        List<String> listOfCountries = (List<String>) sess.getAttribute("listOfCountries");
        if(result.hasErrors()){
            model.addAttribute("listOfCountries", listOfCountries);
            return "landingpage";
        }
        sess.setAttribute("listings", listings);
        return "redirect:/search";
    }

    @GetMapping("/search")
    public String searchResults(Model model, HttpSession sess){
        Listings matchListings = (Listings) sess.getAttribute("listings");
        
        /* List<Document> resultList = listingSvc.returnMatchedListings(matchListings.getCountry(), matchListings.getNoOfPersons(), matchListings.getPriceRangeMin(), matchListings.getPriceRangeMax());*/
        List<DisplayListing> dispList = (List<DisplayListing>) sess.getAttribute("dispList");
        
        if(sess.getAttribute("dispList") == null){

            dispList = listingSvc.returnAllListingDisplay(matchListings.getCountry(), matchListings.getNoOfPersons(), matchListings.getPriceRangeMin(), matchListings.getPriceRangeMax());
            //System.out.println("!!!!"+dispList);
            
            
            model.addAttribute("dispList", dispList);
            sess.setAttribute("dispList", dispList);
            
        } else if (sess.getAttribute("dispList")!=null){
            model.addAttribute("dispList", dispList);
        }

        model.addAttribute("listings", matchListings);

        return "results";
    }

    @GetMapping("/details/{_id}")
    public String detailsPage(@PathVariable("_id") String _id, Model model, HttpSession sess){
        Document returnMatched = listingSvc.returnMatched(_id);
        Details returnDetails = new Details();
        returnDetails = Details.JSONToObj(returnMatched);
        model.addAttribute("returnDetails", returnDetails);

        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        sess.setAttribute("returnDetails", returnDetails);
        return "details";
    }

    @PostMapping("/booking/{acc_id}")
    public String bookingPage(@Valid @ModelAttribute Booking booking, BindingResult result,@PathVariable("acc_id") String acc_id, HttpSession sess, Model model){

        Details returnDetails = (Details) sess.getAttribute("returnDetails");
        //basic validation
        if(result.hasErrors()){
            model.addAttribute("returnDetails", returnDetails);
            return "details";
        }

        int reqDays = booking.getDays();
        int availDays = mybnbSvc.findVacancy(Integer.parseInt(acc_id));

        //own validation
        if(reqDays>availDays){
            FieldError err = new FieldError("returnDetails", "days", String.format("Only %d days available for booking, please try again", availDays));
            result.addError(err);
            model.addAttribute("returnDetails", returnDetails);
            return "details";
        } 

        //if successful, minus off days from acc_occupancy
        int updatedVacancy = availDays-reqDays;
        mybnbSvc.updateVacancy(updatedVacancy, acc_id);

        //if successful, store details in reservations + generate unique id
        //String name, String email, String acc_id, Date arrival_date, Integer duration
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        mybnbSvc.insertReservation(
                    uuid,
                    booking.getName(),
                    booking.getEmail(),
                    acc_id,
                    booking.getArrival(),
                    booking.getDays()      
        );
        sess.setAttribute("resv_id", uuid);

        return "redirect:/reservation";
    }

    @GetMapping("/reservation")
    public String reservation(HttpSession sess, Model model){
        String resvRef = (String) sess.getAttribute("resv_id");
        model.addAttribute("resvRef", resvRef);
        return "reservationreference";
    }

    
}
