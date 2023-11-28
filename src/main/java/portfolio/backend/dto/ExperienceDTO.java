package portfolio.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import portfolio.backend.model.Experience;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDTO {

    private Integer id;

    @NotNull
    @NotBlank
    @Length(min = 3)
    private String organization;

    @NotNull
    @NotBlank
    @Length(min = 3)
    private String position;

    private String description;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    public ExperienceDTO(Experience experience) {
        this.id = experience.getId();
        this.organization = experience.getOrganization();
        this.position = experience.getPosition();
        this.description = experience.getDescription();
        this.startDate = experience.getStartDate();
        this.endDate = experience.getEndDate();
    }
}
