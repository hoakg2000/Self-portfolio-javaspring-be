package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.dto.ProfileSkillDTO;
import portfolio.backend.dto.request.ProfileRequestDTO;
import portfolio.backend.dto.response.FullProfileInfoResponseDTO;
import portfolio.backend.dto.response.ProfileResponseDTO;
import portfolio.backend.model.Profile;
import portfolio.backend.model.Skill;
import portfolio.backend.repository.IProfileRepository;
import portfolio.backend.repository.ISkillRepository;
import portfolio.backend.service.IProfileService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProfileService implements IProfileService {
    private static final Logger logger = Logger.getLogger(ProfileService.class.getName());

    @Autowired
    private IProfileRepository profileRepository;
    @Autowired
    private ISkillRepository skillRepository;

    @Override
    public ProfileResponseDTO getById(Integer profileId) {
        logger.info("getById" + profileId);
        Profile profile = getProfileById(profileId);
        return new ProfileResponseDTO(profile);
    }

    @Override
    public List<ProfileResponseDTO> getAll() {
        logger.info("getAll");
        List<Profile> profileList = profileRepository.findAll();
        return profileList.stream()
                .map(about -> new ProfileResponseDTO(about))
                .collect(Collectors.toList());
    }

    @Override
    public ProfileResponseDTO create(ProfileRequestDTO profileRequestDTO) {
        logger.info("create" + profileRequestDTO.getName());
        Profile profile = new Profile()
                .setName(profileRequestDTO.getName())
                .setBirth(profileRequestDTO.getBirth())
                .setAddress(profileRequestDTO.getAddress());
        Profile createdProfile = profileRepository.save(profile);

        return new ProfileResponseDTO(createdProfile);
    }

    @Override
    public ProfileResponseDTO update(Integer profileId, ProfileRequestDTO profileRequestDTO) {
        logger.info("update" + profileRequestDTO.getName());
        Profile profile = getProfileById(profileId);

        profile.setName(profileRequestDTO.getName())
                .setBirth(profileRequestDTO.getBirth())
                .setAddress(profileRequestDTO.getAddress());

        Profile updatedProfile = profileRepository.save(profile);
        return new ProfileResponseDTO(updatedProfile);
    }

    @Override
    public void deleteById(Integer profileId) {
        logger.info("deleteById" + profileId);
        Profile profile = getProfileById(profileId);
        profileRepository.delete(profile);
    }

    @Override
    public FullProfileInfoResponseDTO viewById(Integer profileId) {
        logger.info("viewById " + profileId);
        Profile profile = getProfileById(profileId);
        return new FullProfileInfoResponseDTO(profile);
    }

    @Override
    public FullProfileInfoResponseDTO addSkill(Integer profileId, ProfileSkillDTO profileSkillDTO) {
        logger.info("addSkill " + profileId);
        Profile profile = getProfileById(profileId);
        Skill skill = skillRepository.findById(profileSkillDTO.getSkillId())
                .orElseThrow(() -> new NotFoundException("Can't find Skill with id: " + profileSkillDTO.getSkillId()));
        profile.addSkill(skill);
        profileRepository.save(profile);
        return new FullProfileInfoResponseDTO(profile);
    }

    @Override
    public FullProfileInfoResponseDTO removeSkill(Integer profileId, Integer skillId) {
        logger.info("removeSkill " + profileId);
        Profile profile = getProfileById(profileId);
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new NotFoundException("Can't find Skill with id: " + skillId));
        profile.removeSkill(skill);
        profileRepository.save(profile);
        return new FullProfileInfoResponseDTO(profile);
    }

    private Profile getProfileById(Integer profileId){
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found with id: " + profileId));
    }
}
