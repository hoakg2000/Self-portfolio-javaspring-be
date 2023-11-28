package portfolio.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.backend.dto.ExperienceDTO;
import portfolio.backend.service.IExperienceService;

import java.util.List;

@RestController
@RequestMapping("profile/{profileId}/experience")
public class ExperienceController {

    @Autowired
    private IExperienceService experienceService;

    @GetMapping
    public ResponseEntity<List<ExperienceDTO>> getExperienceById(@PathVariable Integer profileId) {
        List<ExperienceDTO> experienceList = experienceService.getByProfileId(profileId);
        return ResponseEntity.ok(experienceList);
    }

    @PostMapping
    public ResponseEntity<ExperienceDTO> createExperience(@PathVariable Integer profileId, @Valid @RequestBody ExperienceDTO ExperienceDTO) {
        ExperienceDTO createdExperience = experienceService.create(profileId, ExperienceDTO);
        return new ResponseEntity<>(createdExperience, HttpStatus.CREATED);
    }

    @PutMapping("/{expId}")
    public ResponseEntity<ExperienceDTO> updateExperience(@PathVariable Integer profileId, @PathVariable Integer expId, @Valid @RequestBody ExperienceDTO ExperienceDTO) {
        ExperienceDTO updatedExperience = experienceService.update(profileId, expId, ExperienceDTO);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/{expId}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Integer profileId, @PathVariable Integer expId) {
        experienceService.deleteById(profileId, expId);
        return ResponseEntity.ok().build();
    }
}
