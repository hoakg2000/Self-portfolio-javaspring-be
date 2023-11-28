package portfolio.backend.service;

import portfolio.backend.dto.ExperienceDTO;

import java.util.List;

public interface IExperienceService {

    List<ExperienceDTO> getByProfileId(Integer profileId);

    ExperienceDTO create(Integer profileId, ExperienceDTO ExperienceDTO);

    ExperienceDTO update(Integer profileId, Integer expId, ExperienceDTO ExperienceDTO);

    void deleteById(Integer profileId, Integer expId);
}
