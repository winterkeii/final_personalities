package com.medina.personalities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Replace with your React app's origin
@RequestMapping("/medina/personalities") // Updated the request mapping
public class PersonalitiesController {

    @Autowired
    private PersonalitiesRepository personalitiesRepository;

    @GetMapping
    public ResponseEntity<List<Personalities>> getAllPersonalities() { // Updated method name
        List<Personalities> personalities = personalitiesRepository.findAll();
        return new ResponseEntity<>(personalities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personalities> getPersonalityById(@PathVariable Long id) { // Updated method name
        Optional<Personalities> personality = personalitiesRepository.findById(id);
        return personality.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Personalities> createPersonality(@RequestBody Personalities personality) { // Updated method name
        Personalities savedPersonality = personalitiesRepository.save(personality);
        return new ResponseEntity<>(savedPersonality, HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Personalities>> createBulkPersonalities(@RequestBody List<Personalities> personalities) {
        List<Personalities> savedPersonalities = personalitiesRepository.saveAll(personalities);
        return new ResponseEntity<>(savedPersonalities, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personalities> updatePersonality(@PathVariable Long id, @RequestBody Personalities updatedPersonality) { // Updated method name
        Optional<Personalities> existingPersonality = personalitiesRepository.findById(id);
        if (existingPersonality.isPresent()) {
            updatedPersonality.setId(id);
            Personalities savedPersonality = personalitiesRepository.save(updatedPersonality);
            return new ResponseEntity<>(savedPersonality, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePersonality(@PathVariable Long id) { // Updated method name
        if (personalitiesRepository.existsById(id)) {
            personalitiesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}