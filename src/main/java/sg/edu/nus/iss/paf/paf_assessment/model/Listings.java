package sg.edu.nus.iss.paf.paf_assessment.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Listings {
    
    @NotEmpty(message="Country must not be empty")
    @NotNull(message="Country must not be null")
    private String country;
    
    @Min(value=1, message="Number of person must be between 1 and 10")
    @Max(value=10, message="Number of person must be between 1 and 10")
    private Integer noOfPersons;

    @Min(value=1, message="Price range must be between 1 and 10000")
    @Max(value=10000, message="Price range must be between 1 and 10000")
    private Float priceRangeMin;

    @Min(value=1, message="Price range must be between 1 and 10000")
    @Max(value=10000, message="Price range must be between 1 and 10000")
    private Float priceRangeMax;
   
}
