package portfolio.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import portfolio.backend.dto.AboutDTO;

@Entity
@Table(name = "about")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "profilel_id", referencedColumnName = "id")
    private Profile profile;


    public About(AboutDTO aboutDTO, Profile profile){
        this.id = aboutDTO.getId();
        this.name = aboutDTO.getName();
        this.content = aboutDTO.getContent();
        this.profile = profile;
    }

}
