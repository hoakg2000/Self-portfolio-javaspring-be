package portfolio.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.backend.dto.AwardDTO;
import portfolio.backend.service.IAwardService;

import java.util.List;

@RestController
@RequestMapping("profile/{profileId}/award")
public class AwardController {

    @Autowired
    private IAwardService awardService;

    @GetMapping
    public ResponseEntity<List<AwardDTO>> getAwardsById(@PathVariable Integer profileId) {
        List<AwardDTO> awards = awardService.getByProfileId(profileId);
        return ResponseEntity.ok(awards);
    }

    @PostMapping
    public ResponseEntity<AwardDTO> createAward(@PathVariable Integer profileId, @Valid @RequestBody AwardDTO awardDTO) {
        AwardDTO createdAward = awardService.create(profileId, awardDTO);
        return new ResponseEntity<>(createdAward, HttpStatus.CREATED);
    }

    @PutMapping("/{awardId}")
    public ResponseEntity<AwardDTO> updateAward(@PathVariable Integer profileId, @PathVariable Integer awardId, @Valid @RequestBody AwardDTO awardDTO) {
        AwardDTO updatedAward = awardService.update(profileId, awardId, awardDTO);
        return ResponseEntity.ok(updatedAward);
    }

    @DeleteMapping("/{awardId}")
    public ResponseEntity<Void> deleteAward(@PathVariable Integer profileId, @PathVariable Integer awardId) {
        awardService.deleteById(profileId, awardId);
        return ResponseEntity.ok().build();
    }
}
