package portfolio.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.backend.dto.ProfileSkillDTO;
import portfolio.backend.dto.request.ProfileRequestDTO;
import portfolio.backend.dto.response.ProfileResponseDTO;
import portfolio.backend.dto.response.FullProfileInfoResponseDTO;
import portfolio.backend.model.Profile;
import portfolio.backend.service.IProfileService;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private IProfileService profileService;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Integer profileId) {
        ProfileResponseDTO profile = profileService.getById(profileId);
        return ResponseEntity.ok(profile);
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {
        List<ProfileResponseDTO> profiles = profileService.getAll();
        return ResponseEntity.ok(profiles);
    }

    @PostMapping("/create")
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO createdProfile = profileService.create(profileRequestDTO);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable Integer profileId, @RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO updatedProfile = profileService.update(profileId, profileRequestDTO);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity deleteProfile(@PathVariable Integer profileId) {
        profileService.deleteById(profileId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{profileId}/view")
    public ResponseEntity<FullProfileInfoResponseDTO> viewFullProfile(@PathVariable Integer profileId) {
        FullProfileInfoResponseDTO profile = profileService.viewById(profileId);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/{profileId}/skill")
    public ResponseEntity<FullProfileInfoResponseDTO> addSkillToProfile(@PathVariable Integer profileId, @Valid @RequestBody ProfileSkillDTO profileSkillDTO) {
        FullProfileInfoResponseDTO profile = profileService.addSkill(profileId, profileSkillDTO);
        return ResponseEntity.ok(profile);
    }
    @DeleteMapping("/{profileId}/skill/{skillId}")
    public ResponseEntity<FullProfileInfoResponseDTO> removeSkillFromProfile(@PathVariable Integer profileId, @PathVariable Integer skillId) {
        FullProfileInfoResponseDTO profile = profileService.removeSkill(profileId, skillId);
        return ResponseEntity.ok(profile);
    }
}
