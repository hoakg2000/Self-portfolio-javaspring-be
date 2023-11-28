package portfolio.backend.dto.response;

import lombok.Getter;
import lombok.Setter;
import portfolio.backend.datatype.UserRole;
import portfolio.backend.model.User;

import java.util.Date;

@Getter
@Setter
public class UserResponseDTO {
    private String username;
    private UserRole role;
    private String email;
    private String accessToken;
    private String refreshToken;
    private Date createdAt;
    private Date updatedAt;

    public UserResponseDTO(User user){
        this.username = user.getUsername();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.accessToken = user.getAccessToken();
        this.refreshToken = user.getRefreshToken();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
