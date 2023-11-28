package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.ApiRequestException;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.dto.InterestDTO;
import portfolio.backend.model.Interest;
import portfolio.backend.model.Profile;
import portfolio.backend.repository.IInterestRepository;
import portfolio.backend.repository.IProfileRepository;
import portfolio.backend.service.IInterestService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class InterestService implements IInterestService {

    private static final Logger logger = Logger.getLogger(InterestService.class.getName());

    @Autowired
    private IInterestRepository interestRepository;

    @Autowired
    private IProfileRepository profileRepository;

    @Override
    public List<InterestDTO> getByProfileId(Integer profileId) {
        logger.info("getByProfileId " + profileId);
        List<Interest> interestList = interestRepository.findByProfileId(profileId);
        return interestList.stream()
                .map(InterestDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public InterestDTO create(Integer profileId, InterestDTO interestDTO) {
        logger.info("create: profileId " + profileId + ", interest: " + interestDTO.getName());
        Profile profile = getProfileById(profileId);

        Interest interest = new Interest()
                .setProfile(profile)
                .setName(interestDTO.getName());

        Interest createdInterest = interestRepository.save(interest);
        logger.info("create: Interest created - " + createdInterest.getId());
        return new InterestDTO(createdInterest);
    }

    @Override
    public InterestDTO update(Integer profileId, Integer interestId, InterestDTO interestDTO) {
        logger.info("update: profileId " + profileId + ", interestId: " + interestId + ", interest: " + interestDTO.getName());
        Interest interest = getInterestById(profileId, interestId);

        interest.setName(interestDTO.getName());

        Interest updatedInterest = interestRepository.save(interest);
        logger.info("update: Interest updated - " + updatedInterest.getId());
        return new InterestDTO(updatedInterest);
    }

    @Override
    public void deleteById(Integer profileId, Integer interestId) {
        logger.info("deleteById: interestId " + interestId);
        getInterestById(profileId, interestId);
        interestRepository.deleteById(interestId);
        logger.info("deleteById: Interest deleted - " + interestId);
    }

    private Interest getInterestById(Integer profileId, Integer interestId) {
        logger.info("getInterestById: profileId " + profileId + ", interestId: " + interestId);
        Profile profile = getProfileById(profileId);

        Interest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new NotFoundException("Can't find Interest with id: " + interestId));

        if (!interest.getProfile().equals(profile)) {
            logger.severe("getInterestById: Profile and Interest not match!!!");
            throw new ApiRequestException("Profile and Interest not match!!!");
        }
        logger.info("getInterestById: Interest found - " + interestId);
        return interest;
    }

    private Profile getProfileById(Integer profileId) {
        logger.info("getProfileById: profileId " + profileId);
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Can't find Profile with id: " + profileId));
    }
}
