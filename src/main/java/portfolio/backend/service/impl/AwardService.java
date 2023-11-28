package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.ApiRequestException;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.dto.AwardDTO;
import portfolio.backend.model.Award;
import portfolio.backend.model.Profile;
import portfolio.backend.repository.IAwardRepository;
import portfolio.backend.repository.IProfileRepository;
import portfolio.backend.service.IAwardService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AwardService implements IAwardService {
    private static final Logger logger = Logger.getLogger(AwardService.class.getName());

    @Autowired
    private IAwardRepository awardRepository;

    @Autowired
    private IProfileRepository profileRepository;

    public List<AwardDTO> getByProfileId(Integer profileId) {
        logger.info("getByProfileId " + profileId);
        List<Award> awardList = awardRepository.findByProfileId(profileId);
        return awardList.stream()
                .map(AwardDTO::new)
                .collect(Collectors.toList());
    }

    public AwardDTO create(Integer profileId, AwardDTO awardDTO) {
        logger.info("create: profileId " + profileId + ", award: " + awardDTO.getName());
        Profile profile = getProfileById(profileId);

        Award award = new Award()
                .setProfile(profile)
                .setName(awardDTO.getName())
                .setLocation(awardDTO.getLocation())
                .setRank(awardDTO.getRank())
                .setYear(awardDTO.getYear());

        Award createdAward = awardRepository.save(award);
        logger.info("create: Award created - " + createdAward.getId());
        return new AwardDTO(createdAward);
    }

    public AwardDTO update(Integer profileId, Integer awardId, AwardDTO awardDTO) {
        logger.info("update: profileId " + profileId + ", awardId: " + awardId + ", award: " + awardDTO.getName());
        Award award = getAwardById(profileId, awardId);

        award.setName(awardDTO.getName())
                .setLocation(awardDTO.getLocation())
                .setRank(awardDTO.getRank())
                .setYear(awardDTO.getYear());

        Award updatedAward = awardRepository.save(award);
        logger.info("update: Award updated - " + updatedAward.getId());
        return new AwardDTO(updatedAward);
    }

    public void deleteById(Integer profileId, Integer awardId) {
        logger.info("deleteById: awardId " + awardId);
        getAwardById(profileId, awardId);
        awardRepository.deleteById(awardId);
        logger.info("deleteById: Award deleted - " + awardId);
    }

    private Award getAwardById(Integer profileId, Integer awardId) {
        logger.info("getAwardById: profileId " + profileId + ", awardId: " + awardId);
        Profile profile = getProfileById(profileId);

        Award award = awardRepository.findById(awardId)
                .orElseThrow(() -> new NotFoundException("Can't find Award with id: " + awardId));

        if (!award.getProfile().equals(profile)){
            logger.severe("getAwardById: Profile and Award not match!!!");
            throw new ApiRequestException("Profile and Award not match!!!");
        }
        logger.info("getAwardById: Award found - " + awardId);
        return award;
    }

    private Profile getProfileById(Integer profileId) {
        logger.info("getProfileById: profileId " + profileId);
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Can't find Profile with id: " + profileId));
    }
}
