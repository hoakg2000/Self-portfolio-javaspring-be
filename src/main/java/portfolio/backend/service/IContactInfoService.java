package portfolio.backend.service;

import portfolio.backend.dto.ContactInfoDTO;

import java.util.List;

public interface IContactInfoService {

    List<ContactInfoDTO> getByProfileId(Integer profileId);

    ContactInfoDTO create(Integer profileId, ContactInfoDTO contactInfoDTO);

    ContactInfoDTO update(Integer profileId, Integer contactInfoId, ContactInfoDTO contactInfoDTO);

    void deleteById(Integer profileId, Integer contactInfoId);
}
