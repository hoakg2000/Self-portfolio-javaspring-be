package portfolio.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.backend.dto.SkillDTO;
import portfolio.backend.service.ISkillService;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private ISkillService skillService;

    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Integer skillId) {
        SkillDTO skill = skillService.getById(skillId);
        return ResponseEntity.ok(skill);
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<SkillDTO> skills = skillService.getAll();
        return ResponseEntity.ok(skills);
    }

    @PostMapping
    public ResponseEntity<SkillDTO> createSkill(@Valid @RequestBody SkillDTO skillDTO) {
        SkillDTO createdSkill = skillService.create(skillDTO);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable Integer skillId, @Valid @RequestBody SkillDTO skillDTO) {
        SkillDTO updatedSkill = skillService.update(skillId, skillDTO);
        return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Integer skillId) {
        skillService.deleteById(skillId);
        return ResponseEntity.ok().build();
    }
}
