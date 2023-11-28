package portfolio.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.backend.dto.AboutDTO;
import portfolio.backend.service.IAboutService;

import java.util.List;

@RestController
@RequestMapping("profile/{profileId}/about")
public class AboutController {

    @Autowired
    private IAboutService aboutService;

    @GetMapping
    public ResponseEntity<List<AboutDTO>> getAboutById(@PathVariable Integer profileId) {
        List<AboutDTO> about = aboutService.getByProfileId(profileId);
        return ResponseEntity.ok(about);
    }

    @PostMapping
    public ResponseEntity<AboutDTO> createAbout(@PathVariable Integer profileId, @Valid @RequestBody AboutDTO aboutDTO) {
        AboutDTO createdAbout = aboutService.create(profileId, aboutDTO);
        return new ResponseEntity<>(createdAbout, HttpStatus.CREATED);
    }

    @PutMapping("/{aboutId}")
    public ResponseEntity<AboutDTO> updateAbout(@PathVariable Integer profileId, @PathVariable Integer aboutId, @Valid @RequestBody AboutDTO aboutDTO) {
        AboutDTO updatedAbout = aboutService.update(profileId, aboutId, aboutDTO);
        return ResponseEntity.ok(updatedAbout);
    }

    @DeleteMapping("/{aboutId}")
    public ResponseEntity<Void> deleteAbout(@PathVariable Integer profileId, @PathVariable Integer aboutId) {
        aboutService.deleteById(profileId, aboutId);
        return ResponseEntity.ok().build();
    }
}
