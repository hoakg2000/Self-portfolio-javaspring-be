package portfolio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.backend.model.Skill;

public interface ISkillRepository extends JpaRepository<Skill, Integer> {
}
