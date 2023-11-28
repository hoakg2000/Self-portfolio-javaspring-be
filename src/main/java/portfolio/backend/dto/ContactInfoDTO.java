package portfolio.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import portfolio.backend.model.ContactInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ContactInfoDTO {

    private Integer id;

    @NotNull
    @Length(min=3)
    private String type;

    private String address;

    private String link;

    private String icon;

    public ContactInfoDTO(ContactInfo contactInfo){
        this.id = contactInfo.getId();
        this.type = contactInfo.getType();
        this.address = contactInfo.getAddress();
        this.link = contactInfo.getLink();
        this.icon = contactInfo.getIcon();
    }
}
