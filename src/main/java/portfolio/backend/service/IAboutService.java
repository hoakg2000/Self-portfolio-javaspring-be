package portfolio.backend.service;

import portfolio.backend.dto.AboutDTO;

import java.util.List;

public interface IAboutService {

    List<AboutDTO> getByProfileId(Integer aboutId);

    AboutDTO create(Integer profileId, AboutDTO aboutDTO);

    AboutDTO update(Integer profileId, Integer aboutId, AboutDTO aboutDTO);

    void deleteById(Integer profileId, Integer aboutId);
}
