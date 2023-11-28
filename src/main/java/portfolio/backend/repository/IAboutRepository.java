package portfolio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.backend.dto.AboutDTO;
import portfolio.backend.model.About;

import java.util.List;

@Repository
public interface IAboutRepository extends JpaRepository<About, Integer> {
    List<About> findByProfileId(Integer profileId);
}
