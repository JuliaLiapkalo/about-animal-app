package com.liapkalo.pumb.aboutanimal.service;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;
import com.liapkalo.pumb.aboutanimal.entity.dto.FilterAnimalDto;

import java.util.List;

public interface AnimalService {

    void createAnimal(AnimalDto animalDto);

    List<Animal> getFilteredAnimals(FilterAnimalDto filterAnimalDto, String sortBy);
}
