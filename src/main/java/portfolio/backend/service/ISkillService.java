package portfolio.backend.service;

import portfolio.backend.dto.SkillDTO;

import java.util.List;

public interface ISkillService {

    SkillDTO getById(Integer skillId);

    List<SkillDTO> getAll();

    SkillDTO create(SkillDTO skillDTO);

    SkillDTO update(Integer skillId, SkillDTO skillDTO);

    void deleteById(Integer skillId);
}
