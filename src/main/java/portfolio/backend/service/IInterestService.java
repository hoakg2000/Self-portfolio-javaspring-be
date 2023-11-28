package portfolio.backend.service;

import portfolio.backend.dto.InterestDTO;

import java.util.List;

public interface IInterestService {

    List<InterestDTO> getByProfileId(Integer profileId);

    InterestDTO create(Integer profileId, InterestDTO interestDTO);

    InterestDTO update(Integer profileId, Integer interestId, InterestDTO interestDTO);

    void deleteById(Integer profileId, Integer interestId);
}
