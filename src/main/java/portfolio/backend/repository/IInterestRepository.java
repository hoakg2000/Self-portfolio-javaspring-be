package portfolio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.backend.model.Interest;

import java.util.List;

@Repository
public interface IInterestRepository extends JpaRepository<Interest, Integer> {
    List<Interest> findByProfileId(Integer profileId);
}
