package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.ApiRequestException;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.dto.ExperienceDTO;
import portfolio.backend.model.Experience;
import portfolio.backend.model.Profile;
import portfolio.backend.repository.IExperienceRepository;
import portfolio.backend.repository.IProfileRepository;
import portfolio.backend.service.IExperienceService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ExperienceService implements IExperienceService {

    private static final Logger logger = Logger.getLogger(ExperienceService.class.getName());

    @Autowired
    private IExperienceRepository experienceRepository;

    @Autowired
    private IProfileRepository profileRepository;

    @Override
    public List<ExperienceDTO> getByProfileId(Integer profileId) {
        logger.info("getByProfileId " + profileId);
        List<Experience> experienceList = experienceRepository.findByProfileId(profileId);
        return experienceList.stream()
                .map(ExperienceDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ExperienceDTO create(Integer profileId, ExperienceDTO ExperienceDTO) {
        logger.info("create: profileId " + profileId + ", experience: " + ExperienceDTO.getOrganization());
        Profile profile = getProfileById(profileId);

        Experience experience = new Experience()
                .setProfile(profile)
                .setOrganization(ExperienceDTO.getOrganization())
                .setPosition(ExperienceDTO.getPosition())
                .setDescription(ExperienceDTO.getDescription())
                .setStartDate(ExperienceDTO.getStartDate())
                .setEndDate(ExperienceDTO.getEndDate());

        Experience createdExperience = experienceRepository.save(experience);
        logger.info("create: Experience created - " + createdExperience.getId());
        return new ExperienceDTO(createdExperience);
    }

    @Override
    public ExperienceDTO update(Integer profileId, Integer expId, ExperienceDTO ExperienceDTO) {
        logger.info("update: profileId " + profileId + ", expId: " + expId + ", experience: " + ExperienceDTO.getOrganization());
        Experience experience = getExperienceById(profileId, expId);

        experience.setOrganization(ExperienceDTO.getOrganization())
                .setPosition(ExperienceDTO.getPosition())
                .setDescription(ExperienceDTO.getDescription())
                .setStartDate(ExperienceDTO.getStartDate())
                .setEndDate(ExperienceDTO.getEndDate());

        Experience updatedExperience = experienceRepository.save(experience);
        logger.info("update: Experience updated - " + updatedExperience.getId());
        return new ExperienceDTO(updatedExperience);
    }

    @Override
    public void deleteById(Integer profileId, Integer expId) {
        logger.info("deleteById: expId " + expId);
        getExperienceById(profileId, expId);
        experienceRepository.deleteById(expId);
        logger.info("deleteById: Experience deleted - " + expId);
    }

    private Experience getExperienceById(Integer profileId, Integer expId) {
        logger.info("getExperienceById: profileId " + profileId + ", expId: " + expId);
        Profile profile = getProfileById(profileId);

        Experience experience = experienceRepository.findById(expId)
                .orElseThrow(() -> new NotFoundException("Can't find Experience with id: " + expId));

        if (!experience.getProfile().equals(profile)) {
            logger.severe("getExperienceById: Profile and Experience not match!!!");
            throw new ApiRequestException("Profile and Experience not match!!!");
        }
        logger.info("getExperienceById: Experience found - " + expId);
        return experience;
    }

    private Profile getProfileById(Integer profileId) {
        logger.info("getProfileById: profileId " + profileId);
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Can't find Profile with id: " + profileId));
    }
}
