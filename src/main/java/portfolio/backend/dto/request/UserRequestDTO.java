package portfolio.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import portfolio.backend.datatype.UserRole;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank
    @Length(min = 5, max = 12)
    private String username;

    @NotBlank
    @Length(min = 5, max = 15)
    private String password;

    @NotNull
    private UserRole role;

    @Email
    private String email;
}
