package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.ApiRequestException;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.dto.AboutDTO;
import portfolio.backend.model.About;
import portfolio.backend.model.Profile;
import portfolio.backend.repository.IAboutRepository;
import portfolio.backend.repository.IProfileRepository;
import portfolio.backend.service.IAboutService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AboutService implements IAboutService {
    private static final Logger logger = Logger.getLogger(AboutService.class.getName());

    @Autowired
    private IAboutRepository aboutRepository;

    @Autowired
    private IProfileRepository profileRepository;

    public List<AboutDTO> getByProfileId(Integer profileId) {
        logger.info("getByProfileId " + profileId);
        List<About> aboutList = aboutRepository.findByProfileId(profileId);
        return aboutList.stream()
                .map(AboutDTO::new)
                .collect(Collectors.toList());
    }

    public AboutDTO create(Integer profileId, AboutDTO aboutDTO) {
        logger.info("create: profileId " + profileId + ", about: " + aboutDTO.getName());
        Profile profile = getProfileById(profileId);

        About about = new About()
                .setProfile(profile)
                .setName(aboutDTO.getName())
                .setContent(aboutDTO.getContent());
        About createdAbout = aboutRepository.save(about);

        logger.info("create: About created - " + createdAbout.getId());
        return new AboutDTO(createdAbout);
    }

    public AboutDTO update(Integer profileId, Integer aboutId, AboutDTO aboutDTO) {
        logger.info("update: profileId " + profileId + ", aboutId: " + aboutId + ", about: " + aboutDTO.getName());
        About about = getAboutById(profileId, aboutId);

        about.setName(aboutDTO.getName())
                .setContent(aboutDTO.getContent());

        About updatedAbout = aboutRepository.save(about);
        logger.info("update: About updated - " + updatedAbout.getId());
        return new AboutDTO(updatedAbout);
    }

    public void deleteById(Integer profileId, Integer aboutId) {
        logger.info("deleteById: aboutId " + aboutId);
        aboutRepository.deleteById(aboutId);
        logger.info("deleteById: About deleted - " + aboutId);
    }

    private About getAboutById(Integer profileId, Integer aboutId) {
        logger.info("getAboutById: profileId " + profileId + ", aboutId: " + aboutId);
        Profile profile = getProfileById(profileId);

        About about = aboutRepository.findById(aboutId)
                .orElseThrow(() -> new NotFoundException("Can't find About with id: " + aboutId));

        if (!about.getProfile().equals(profile)) {
            logger.severe("getAboutById: Profile and About not match!!!");
            throw new ApiRequestException("Profile and About not match!!!");
        }
        logger.info("getAboutById: About found - " + aboutId);
        return about;
    }

    private Profile getProfileById(Integer profileId) {
        logger.info("getProfileById: profileId " + profileId);
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Can't find Profile with id: " + profileId));
    }
}
