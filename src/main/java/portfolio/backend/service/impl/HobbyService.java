package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.ApiRequestException;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.dto.HobbyDTO;
import portfolio.backend.model.Hobby;
import portfolio.backend.model.Profile;
import portfolio.backend.repository.IHobbyRepository;
import portfolio.backend.repository.IProfileRepository;
import portfolio.backend.service.IHobbyService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class HobbyService implements IHobbyService {

    private static final Logger logger = Logger.getLogger(HobbyService.class.getName());

    @Autowired
    private IHobbyRepository hobbyRepository;

    @Autowired
    private IProfileRepository profileRepository;

    @Override
    public List<HobbyDTO> getByProfileId(Integer profileId) {
        logger.info("getByProfileId " + profileId);
        List<Hobby> hobbyList = hobbyRepository.findByProfileId(profileId);
        return hobbyList.stream()
                .map(hobby -> new HobbyDTO(hobby.getId(), hobby.getName(), hobby.getIcon(), hobby.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public HobbyDTO create(Integer profileId, HobbyDTO hobbyDTO) {
        logger.info("create: profileId " + profileId + " hobby: " + hobbyDTO.getName());
        Profile profile = getProfileById(profileId);

        Hobby hobby = new Hobby()
                .setProfile(profile)
                .setName(hobbyDTO.getName())
                .setIcon(hobbyDTO.getIcon())
                .setDescription(hobbyDTO.getDescription());

        Hobby createdHobby = hobbyRepository.save(hobby);
        logger.info("create: Hobby created - " + createdHobby.getId());
        return new HobbyDTO(createdHobby);
    }

    @Override
    public HobbyDTO update(Integer profileId, Integer hobbyId, HobbyDTO hobbyDTO) {
        logger.info("update: profileId " + profileId + " hobbyId: " + hobbyId + " hobby: " + hobbyDTO.getName());
        Hobby hobby = getHobbyById(profileId, hobbyId);

        hobby.setName(hobbyDTO.getName())
                .setIcon(hobbyDTO.getIcon())
                .setDescription(hobbyDTO.getDescription());

        Hobby updatedHobby = hobbyRepository.save(hobby);
        logger.info("update: Hobby updated - " + updatedHobby.getId());
        return new HobbyDTO(updatedHobby.getId(), updatedHobby.getName(), updatedHobby.getIcon(), updatedHobby.getDescription());
    }

    @Override
    public void deleteById(Integer profileId, Integer hobbyId) {
        logger.info("deleteById: profileId " + profileId + " hobbyId: " + hobbyId);
        //check if hobbyID and profile has a relationship
        getHobbyById(profileId, hobbyId);
        hobbyRepository.deleteById(hobbyId);
        logger.info("deleteById: Hobby deleted - " + hobbyId);
    }

    private Hobby getHobbyById(Integer profileId, Integer hobbyId) {
        logger.info("getHobbyById: profileId " + profileId + " hobbyId: " + hobbyId);
        Profile profile = getProfileById(profileId);

        Hobby hobby = hobbyRepository.findById(hobbyId)
                .orElseThrow(() -> new NotFoundException("Can't find Hobby with id: " + hobbyId));

        if (!hobby.getProfile().equals(profile)) {
            logger.log(Level.SEVERE, "getHobbyById: Profile and Hobby not match!!!");
            throw new ApiRequestException("Profile and Hobby not match!!!");
        }
        logger.info("getHobbyById: Hobby found - " + hobbyId);
        return hobby;
    }

    private Profile getProfileById(Integer profileId) {
        logger.info("getProfileById: profileId " + profileId);
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Can't find Profile with id: " + profileId));
    }
}
