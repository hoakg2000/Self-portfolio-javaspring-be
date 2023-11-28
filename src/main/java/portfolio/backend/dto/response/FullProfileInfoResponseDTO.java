package portfolio.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import portfolio.backend.model.*;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class FullProfileInfoResponseDTO {
    private int id;
    private String name;
    private Date birth;
    private String address;

    private List<Award> awards;
    private List<Experience> experiences;
    private List<Skill> skills;
    private List<Hobby> hobbies;
    private List<Interest> interests;
    private List<Project> projects;
    private List<ContactInfo> contactInfos;
    private List<About> abouts;

    public FullProfileInfoResponseDTO(Profile profile){
        this.id = profile.getId();
        this.name = profile.getName();
        this.birth = profile.getBirth();
        this.address = profile.getAddress();

        this.awards = profile.getAwards();
        this.experiences = profile.getExperiences();
        this.skills = profile.getSkills();
        this.hobbies = profile.getHobbies();
        this.projects = profile.getProjects();
        this.contactInfos = profile.getContacts();
        this.interests = profile.getInterests();
        this.abouts = profile.getAbouts();
    }
}
