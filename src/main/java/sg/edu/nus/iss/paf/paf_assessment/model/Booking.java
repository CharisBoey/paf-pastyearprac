package sg.edu.nus.iss.paf.paf_assessment.model;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Booking {

    @NotEmpty(message="Name must not be empty")
    private String name;

    @NotEmpty(message="Email must not be empty")
    @Email(message="Email must follow email format")
    private String email;

    @NotNull(message="Arrival date must not be empty")
    @Future(message="Arrival date must be a future")
    private Date arrival;

    @NotNull(message="Days must not be empty")
    @Min(value=1, message="Must be at least 1 day")
    private Integer days;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getArrival() {
        return arrival;
    }
    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
    public Integer getDays() {
        return days;
    }
    public void setDays(Integer days) {
        this.days = days;
    }
    public Booking(String name, String email, Date arrival, Integer days) {
        this.name = name;
        this.email = email;
        this.arrival = arrival;
        this.days = days;
    }
    public Booking() {
    }
}
