package sg.edu.nus.iss.paf.paf_assessment.repository;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf.paf_assessment.service.mybnbService;

@Repository
public class mybnbRepository {

    @Autowired
    private JdbcTemplate template;

    /* @Autowired
    mybnbService mybnbSvc; */

    //NOTE THE DIFFERENCE BETWEEN SELECT RESULTS & UPDATE DATA
    //.queryForRowSet vs .update
    public int findVacancy(Integer days){
        SqlRowSet rs = template.queryForRowSet(queries.SQL_Find_Vacancy, days);
		
		int resultInt = 0;

        while (rs.next()){
            resultInt = rs.getInt("vacancy");
        }

        return resultInt;
    }

    public void updateVacancy(int vacancy, String acc_id){
        template.update(queries.SQL_Update_Vacancy, vacancy, acc_id);
    }

    public void insertReservation(String resv_id, String name, String email, String acc_id, Date arrival_date, Integer duration){
        template.update(queries.SQL_Insert_Reservation, resv_id, name, email, acc_id, arrival_date, duration);
    }
}
