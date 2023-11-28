package portfolio.backend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "profileskill")
public class ProfileSkill {

    @EmbeddedId
    private Integer id;

    @ManyToOne
    @MapsId("profileId")
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(name = "experience", columnDefinition = "TEXT")
    private String experience;

    // Constructors, getters, setters, and other methods...
}
