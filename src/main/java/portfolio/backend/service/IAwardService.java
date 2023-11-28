package portfolio.backend.service;

import portfolio.backend.dto.AwardDTO;

import java.util.List;

public interface IAwardService {

    List<AwardDTO> getByProfileId(Integer profileId);

    AwardDTO create(Integer profileId, AwardDTO awardDTO);

    AwardDTO update(Integer profileId, Integer awardId, AwardDTO awardDTO);

    void deleteById(Integer profileId, Integer awardId);
}
