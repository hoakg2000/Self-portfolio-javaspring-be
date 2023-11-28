package portfolio.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import portfolio.backend.model.Award;

import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AwardDTO {


    private Integer id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String location;
    @NotNull
    @NotBlank
    private String rank;
    @Min(value = 1900, message = "Year must be after 1900")
    @Max(value = 2100)
    private Integer year;

    public AwardDTO(Award award) {
        this.id = award.getId();
        this.name = award.getName();
        this.location = award.getLocation();
        this.rank = award.getRank();
        this.year = award.getYear();
    }

}
