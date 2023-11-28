package portfolio.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.backend.dto.ContactInfoDTO;
import portfolio.backend.service.IContactInfoService;

import java.util.List;

@RestController
@RequestMapping("profile/{profileId}/contactinfo")
public class ContactInfoController {

    @Autowired
    private IContactInfoService contactInfoService;

    @GetMapping
    public ResponseEntity<List<ContactInfoDTO>> getContactInfoById(@PathVariable Integer profileId) {
        List<ContactInfoDTO> contactInfoList = contactInfoService.getByProfileId(profileId);
        return ResponseEntity.ok(contactInfoList);
    }

    @PostMapping
    public ResponseEntity<ContactInfoDTO> createContactInfo(@PathVariable Integer profileId, @Valid @RequestBody ContactInfoDTO contactInfoDTO) {
        ContactInfoDTO createdContactInfo = contactInfoService.create(profileId, contactInfoDTO);
        return new ResponseEntity<>(createdContactInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{contactInfoId}")
    public ResponseEntity<ContactInfoDTO> updateContactInfo(@PathVariable Integer profileId, @PathVariable Integer contactInfoId, @Valid @RequestBody ContactInfoDTO contactInfoDTO) {
        ContactInfoDTO updatedContactInfo = contactInfoService.update(profileId, contactInfoId, contactInfoDTO);
        return ResponseEntity.ok(updatedContactInfo);
    }

    @DeleteMapping("/{contactInfoId}")
    public ResponseEntity<Void> deleteContactInfo(@PathVariable Integer profileId, @PathVariable Integer contactInfoId) {
        contactInfoService.deleteById(profileId, contactInfoId);
        return ResponseEntity.ok().build();
    }
}
