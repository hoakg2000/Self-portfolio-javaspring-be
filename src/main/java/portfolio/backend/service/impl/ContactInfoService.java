package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.ApiRequestException;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.dto.ContactInfoDTO;
import portfolio.backend.model.ContactInfo;
import portfolio.backend.model.Profile;
import portfolio.backend.repository.IContactInfoRepository;
import portfolio.backend.repository.IProfileRepository;
import portfolio.backend.service.IContactInfoService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ContactInfoService implements IContactInfoService {

    private static final Logger logger = Logger.getLogger(ContactInfoService.class.getName());

    @Autowired
    private IContactInfoRepository contactInfoRepository;

    @Autowired
    private IProfileRepository profileRepository;

    @Override
    public List<ContactInfoDTO> getByProfileId(Integer profileId) {
        logger.info("getByProfileId " + profileId);
        List<ContactInfo> contactInfoList = contactInfoRepository.findByProfileId(profileId);
        return contactInfoList.stream()
                .map(ContactInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ContactInfoDTO create(Integer profileId, ContactInfoDTO contactInfoDTO) {
        logger.info("create: profileId " + profileId + ", contactInfo: " + contactInfoDTO.getType());
        Profile profile = getProfileById(profileId);

        ContactInfo contactInfo = new ContactInfo()
                .setProfile(profile)
                .setType(contactInfoDTO.getType())
                .setAddress(contactInfoDTO.getAddress())
                .setLink(contactInfoDTO.getLink())
                .setIcon(contactInfoDTO.getIcon());

        ContactInfo createdContactInfo = contactInfoRepository.save(contactInfo);
        logger.info("create: ContactInfo created - " + createdContactInfo.getId());
        return new ContactInfoDTO(createdContactInfo);
    }

    @Override
    public ContactInfoDTO update(Integer profileId, Integer contactInfoId, ContactInfoDTO contactInfoDTO) {
        logger.info("update: profileId " + profileId + ", contactInfoId: " + contactInfoId + ", contactInfo: " + contactInfoDTO.getType());
        ContactInfo contactInfo = getContactInfoById(profileId, contactInfoId);

        contactInfo.setType(contactInfoDTO.getType())
                .setAddress(contactInfoDTO.getAddress())
                .setLink(contactInfoDTO.getLink())
                .setIcon(contactInfoDTO.getIcon());

        ContactInfo updatedContactInfo = contactInfoRepository.save(contactInfo);
        logger.info("update: ContactInfo updated - " + updatedContactInfo.getId());
        return new ContactInfoDTO(updatedContactInfo);
    }

    @Override
    public void deleteById(Integer profileId, Integer contactInfoId) {
        logger.info("deleteById: contactInfoId " + contactInfoId);
        getContactInfoById(profileId, contactInfoId);
        contactInfoRepository.deleteById(contactInfoId);
        logger.info("deleteById: ContactInfo deleted - " + contactInfoId);
    }

    private ContactInfo getContactInfoById(Integer profileId, Integer contactInfoId) {
        logger.info("getContactInfoById: profileId " + profileId + ", contactInfoId: " + contactInfoId);
        Profile profile = getProfileById(profileId);

        ContactInfo contactInfo = contactInfoRepository.findById(contactInfoId)
                .orElseThrow(() -> new NotFoundException("Can't find ContactInfo with id: " + contactInfoId));

        if (!contactInfo.getProfile().equals(profile)) {
            logger.severe("getContactInfoById: Profile and ContactInfo not match!!!");
            throw new ApiRequestException("Profile and ContactInfo not match!!!");
        }
        logger.info("getContactInfoById: ContactInfo found - " + contactInfoId);
        return contactInfo;
    }

    private Profile getProfileById(Integer profileId) {
        logger.info("getProfileById: profileId " + profileId);
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Can't find Profile with id: " + profileId));
    }
}
