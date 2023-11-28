package portfolio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.backend.model.Experience;

import java.util.List;

public interface IExperienceRepository extends JpaRepository<Experience, Integer> {
    List<Experience> findByProfileId(Integer profileId);
}
