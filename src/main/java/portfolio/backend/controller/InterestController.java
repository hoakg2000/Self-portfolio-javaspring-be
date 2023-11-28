package portfolio.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.backend.dto.InterestDTO;
import portfolio.backend.service.IInterestService;

import java.util.List;

@RestController
@RequestMapping("profile/{profileId}/interest")
public class InterestController {

    @Autowired
    private IInterestService interestService;

    @GetMapping
    public ResponseEntity<List<InterestDTO>> getInterestsById(@PathVariable Integer profileId) {
        List<InterestDTO> interests = interestService.getByProfileId(profileId);
        return ResponseEntity.ok(interests);
    }

    @PostMapping
    public ResponseEntity<InterestDTO> createInterest(@PathVariable Integer profileId, @Valid @RequestBody InterestDTO interestDTO) {
        InterestDTO createdInterest = interestService.create(profileId, interestDTO);
        return new ResponseEntity<>(createdInterest, HttpStatus.CREATED);
    }

    @PutMapping("/{interestId}")
    public ResponseEntity<InterestDTO> updateInterest(@PathVariable Integer profileId, @PathVariable Integer interestId, @Valid @RequestBody InterestDTO interestDTO) {
        InterestDTO updatedInterest = interestService.update(profileId, interestId, interestDTO);
        return ResponseEntity.ok(updatedInterest);
    }

    @DeleteMapping("/{interestId}")
    public ResponseEntity<Void> deleteInterest(@PathVariable Integer profileId, @PathVariable Integer interestId) {
        interestService.deleteById(profileId, interestId);
        return ResponseEntity.ok().build();
    }
}
