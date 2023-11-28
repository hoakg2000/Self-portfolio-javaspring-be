package portfolio.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import portfolio.backend.datatype.UserRole;
import portfolio.backend.dto.request.UserRequestDTO;

import java.util.Date;

@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"})
})
@Data
@Accessors(chain = true)
public class User {
//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "update_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public User(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public User(UserRequestDTO userRequestDTO){
        this.email = userRequestDTO.getEmail();
        this.username = userRequestDTO.getUsername();
        this.password = userRequestDTO.getPassword();
        this.role = userRequestDTO.getRole();
    }
}
