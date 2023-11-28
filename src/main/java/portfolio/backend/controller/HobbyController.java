package portfolio.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.backend.dto.HobbyDTO;
import portfolio.backend.service.IHobbyService;

import java.util.List;

@RestController
@RequestMapping("/profile/{profileId}/hobby")
public class HobbyController {

    @Autowired
    private IHobbyService hobbyService;

    @GetMapping
    public ResponseEntity<List<HobbyDTO>> getHobbiesByProfileId(@PathVariable Integer profileId) {
        List<HobbyDTO> hobbies = hobbyService.getByProfileId(profileId);
        return ResponseEntity.ok(hobbies);
    }

    @PostMapping
    public ResponseEntity<HobbyDTO> createHobbyForProfile(
            @PathVariable Integer profileId,
            @Valid @RequestBody HobbyDTO hobbyDTO
    ) {
        HobbyDTO createdHobby = hobbyService.create(profileId, hobbyDTO);
        return new ResponseEntity<>(createdHobby, HttpStatus.CREATED);
    }

    @PutMapping("/{hobbyId}")
    public ResponseEntity<HobbyDTO> updateHobby(
            @PathVariable Integer profileId,
            @PathVariable Integer hobbyId,
            @RequestBody HobbyDTO hobbyDTO
    ) {
        HobbyDTO updatedHobby = hobbyService.update(profileId, hobbyId, hobbyDTO);
        return ResponseEntity.ok(updatedHobby);
    }

    @DeleteMapping("/{hobbyId}")
    public ResponseEntity<Void> deleteHobby(
            @PathVariable Integer profileId,
            @PathVariable Integer hobbyId
    ) {
        hobbyService.deleteById(profileId, hobbyId);
        return ResponseEntity.ok().build();
    }
}
