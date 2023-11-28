package portfolio.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import portfolio.backend.model.Hobby;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HobbyDTO {
    private int id;
    @NotNull
    @Length(min = 3, max = 15)
    private String name;
    @NotBlank
    private String icon;
    private String description;

    public HobbyDTO(Hobby hobby){
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.icon = hobby.getIcon();
        this.description = hobby.getDescription();
    }
}
