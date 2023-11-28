package portfolio.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import portfolio.backend.model.Skill;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {

    private Integer id;

    @NotNull
    @NotBlank
    @Length(min = 3)
    private String name;

    @NotNull
    @NotBlank
    private String icon;

    public SkillDTO(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
        this.icon = skill.getIcon();
    }
}
