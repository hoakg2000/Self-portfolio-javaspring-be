package portfolio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.backend.model.Hobby;

import java.util.List;

@Repository
public interface IHobbyRepository extends JpaRepository<Hobby, Integer> {
    List<Hobby> findByProfileId(Integer profileId);
}
