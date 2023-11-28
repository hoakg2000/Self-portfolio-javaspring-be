package portfolio.backend.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginRequestDTO {

    @NotBlank
    @Length(min = 5, max = 12)
    String username;

    @NotBlank
    @Length(min = 5, max = 15)
    String password;

}
