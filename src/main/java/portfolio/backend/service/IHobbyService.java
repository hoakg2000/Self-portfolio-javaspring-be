package portfolio.backend.service;

import portfolio.backend.dto.HobbyDTO;

import java.util.List;

public interface IHobbyService {

    List<HobbyDTO> getByProfileId(Integer profileId);

    HobbyDTO create(Integer profileId, HobbyDTO hobbyDTO);

    HobbyDTO update(Integer profileId, Integer hobbyId, HobbyDTO hobbyDTO);

    void deleteById(Integer profileId, Integer hobbyId);
}
