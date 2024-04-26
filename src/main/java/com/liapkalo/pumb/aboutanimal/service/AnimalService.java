package com.liapkalo.pumb.aboutanimal.service;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;

import java.util.List;

public interface AnimalService {

    void createAnimal(AnimalDto animalDto);
    AnimalDto buildAnimalDto(String[] line);
    List<Animal> getFilteredAnimals(String type, String category, String gender, String sortBy);
}
