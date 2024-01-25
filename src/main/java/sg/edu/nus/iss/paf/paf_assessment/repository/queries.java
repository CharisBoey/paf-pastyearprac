package sg.edu.nus.iss.paf.paf_assessment.repository;


public class queries {

    
    public static final String SQL_Find_Vacancy = """

    select vacancy 
        from acc_occupancy 
	    where acc_id = ?;
    
    """;

    // NOTE! no comma before where!
    public static final String SQL_Update_Vacancy = """
    
    update acc_occupancy 
        set vacancy = ?
        where acc_id = ? ;
    
    """;

    public static final String SQL_Insert_Reservation = """
        insert into reservations 
            (resv_id, name, email, acc_id, arrival_date, duration)
        values
            (?, ?, ?, ?, ?, ?);
    """;
}
