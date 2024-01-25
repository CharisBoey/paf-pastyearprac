package sg.edu.nus.iss.paf.paf_assessment.service;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf.paf_assessment.repository.mybnbRepository;

@Service
public class mybnbService {
    
    @Autowired
    mybnbRepository mybnbRepo;

    public int findVacancy(Integer days){
        return mybnbRepo.findVacancy(days);
    }

    public void updateVacancy(int vacancy, String acc_id){
        mybnbRepo.updateVacancy(vacancy, acc_id);
    }

    public void insertReservation(String resv_id, String name, String email, String acc_id, Date arrival_date, Integer duration){
        mybnbRepo.insertReservation(resv_id, name, email, acc_id, arrival_date, duration);
    }
}
