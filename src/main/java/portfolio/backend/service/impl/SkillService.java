package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.dto.SkillDTO;
import portfolio.backend.model.Skill;
import portfolio.backend.repository.ISkillRepository;
import portfolio.backend.service.ISkillService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SkillService implements ISkillService {

    private static final Logger logger = Logger.getLogger(SkillService.class.getName());

    @Autowired
    private ISkillRepository skillRepository;

    @Override
    public SkillDTO getById(Integer skillId) {
        logger.info("getById: skillId " + skillId);
        Skill skill = getSkillById(skillId);
        return new SkillDTO(skill);
    }

    @Override
    public List<SkillDTO> getAll() {
        logger.info("getAll");
        List<Skill> skillList = skillRepository.findAll();
        return skillList.stream()
                .map(SkillDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public SkillDTO create(SkillDTO skillDTO) {
        logger.info("create: skill: " + skillDTO.getName());
        Skill skill = new Skill()
                .setName(skillDTO.getName())
                .setIcon(skillDTO.getIcon());

        Skill createdSkill = skillRepository.save(skill);
        logger.info("create: Skill created - " + createdSkill.getId());
        return new SkillDTO(createdSkill);
    }

    @Override
    public SkillDTO update(Integer skillId, SkillDTO skillDTO) {
        logger.info("update: skillId " + skillId + ", skill: " + skillDTO.getName());
        Skill skill = getSkillById(skillId);

        skill.setName(skillDTO.getName())
                .setIcon(skillDTO.getIcon());

        Skill updatedSkill = skillRepository.save(skill);
        logger.info("update: Skill updated - " + updatedSkill.getId());
        return new SkillDTO(updatedSkill);
    }

    @Override
    public void deleteById(Integer skillId) {
        logger.info("deleteById: skillId " + skillId);
        Skill skill = getSkillById(skillId);
        skillRepository.deleteById(skill.getId());
        logger.info("deleteById: Skill deleted - " + skillId);
    }

    private Skill getSkillById(Integer skillId) {
        logger.info("getSkillById: skillId " + skillId);
        return skillRepository.findById(skillId)
                .orElseThrow(() -> new NotFoundException("Can't find Skill with id: " + skillId));
    }
}
