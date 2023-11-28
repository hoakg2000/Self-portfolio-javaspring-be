package portfolio.backend.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDTO {

    private int id;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 256)
    private String name;

    @Past
    private Date birth;
    @NotBlank
    private String address;
}
