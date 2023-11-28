package portfolio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.backend.model.ContactInfo;

import java.util.List;

@Repository
public interface IContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
    List<ContactInfo> findByProfileId(Integer profileId);
}
