package portfolio.backend.service;

import portfolio.backend.dto.ProfileSkillDTO;
import portfolio.backend.dto.request.ProfileRequestDTO;
import portfolio.backend.dto.response.FullProfileInfoResponseDTO;
import portfolio.backend.dto.response.ProfileResponseDTO;

import java.util.List;

public interface IProfileService {
    ProfileResponseDTO getById(Integer id);
    List<ProfileResponseDTO> getAll();
    ProfileResponseDTO create(ProfileRequestDTO profileRequestDTO);
    ProfileResponseDTO update(Integer profileId, ProfileRequestDTO profileRequestDTO);
    void deleteById(Integer id);
    FullProfileInfoResponseDTO viewById(Integer id);

    FullProfileInfoResponseDTO addSkill(Integer profileId, ProfileSkillDTO profileSkillDTO);
    FullProfileInfoResponseDTO removeSkill(Integer profileId, Integer skillId);
}
