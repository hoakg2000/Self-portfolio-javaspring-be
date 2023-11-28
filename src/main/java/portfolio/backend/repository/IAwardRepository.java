package portfolio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.backend.model.Award;

import java.util.List;

@Repository
public interface IAwardRepository extends JpaRepository<Award, Integer> {
    List<Award> findByProfileId(Integer profileId);
}
