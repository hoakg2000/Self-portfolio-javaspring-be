package portfolio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.backend.model.Profile;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, Integer> {
}
