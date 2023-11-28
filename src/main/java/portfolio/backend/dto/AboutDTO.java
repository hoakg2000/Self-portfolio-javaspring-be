package portfolio.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import portfolio.backend.model.About;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AboutDTO {

    private int id;
    @NotNull
    @NotBlank
    @Length(min = 3)
    private String name;
    @NotBlank
    private String content;

    public AboutDTO(About about){
        this.id = about.getId();
        this.name = about.getName();
        this.content = about.getContent();
    }
}
