package portfolio.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import portfolio.backend.model.Interest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterestDTO {

    private Integer id;
    @NotNull
    @Length(min = 3)
    private String name;

    public InterestDTO(Interest interest){
        this.id = interest.getId();
        this.name = interest.getName();
    }
}
