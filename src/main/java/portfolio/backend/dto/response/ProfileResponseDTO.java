package portfolio.backend.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import portfolio.backend.model.*;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {

    private int id;
    private String name;
    private Date birth;
    private String address;
    public ProfileResponseDTO(Profile profile){
        this.id = profile.getId();
        this.name = profile.getName();
        this.birth = profile.getBirth();
        this.address = profile.getAddress();
    }
}
