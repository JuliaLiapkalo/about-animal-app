package com.liapkalo.pumb.aboutanimal.controller;

import com.liapkalo.pumb.aboutanimal.factory.ReadFileFactory;
import com.liapkalo.pumb.aboutanimal.service.AnimalService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/animals")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnimalController {

    ReadFileFactory readFileFactory;
    AnimalService animalService;

    @PostMapping("/uploads")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> processAnimalFile(@RequestParam("file") MultipartFile file) {
        readFileFactory.getFileReader(file).readFile(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getFiltersAnimals(@RequestParam(required = false) String type,
                                               @RequestParam(required = false) String category,
                                               @RequestParam(required = false) String gender,
                                               @RequestParam(required = false) String sortBy
    ) {
        return ResponseEntity.ok().body(animalService.getFilteredAnimals(type, category, gender, sortBy));
    }
}
