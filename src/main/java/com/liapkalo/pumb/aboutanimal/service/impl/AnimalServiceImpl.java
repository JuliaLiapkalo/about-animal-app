package com.liapkalo.pumb.aboutanimal.service.impl;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;
import com.liapkalo.pumb.aboutanimal.entity.dto.FilterAnimalDto;
import com.liapkalo.pumb.aboutanimal.repository.AnimalRepository;
import com.liapkalo.pumb.aboutanimal.service.AnimalService;
import com.liapkalo.pumb.aboutanimal.specification.AnimalSpecifications;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.liapkalo.pumb.aboutanimal.utils.BuildUtils.buildAnimal;

import static com.liapkalo.pumb.aboutanimal.utils.SortUtils.*;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnimalServiceImpl implements AnimalService {

    AnimalRepository animalRepository;

    @Override
    public void createAnimal(AnimalDto animalDto) {
        log.info("Creating animal: {}", animalDto);
        animalRepository.save(buildAnimal(animalDto));
        log.info("Animal was created: {}", animalDto);
    }

    @Override
    public List<Animal> getFilteredAnimals(FilterAnimalDto filterAnimalDto, String sortBy) {
        log.info("Start filter animals");
        List<Animal> filteredAnimals =
                animalRepository.findAll(AnimalSpecifications.filterByTypeCategoryAndGender(filterAnimalDto));

        return sortBy != null ? sortAnimals(sortBy, filteredAnimals) : filteredAnimals;
    }

    private List<Animal> sortAnimals(String sortBy, List<Animal> filteredAnimals) {
        log.info("Start sorting animals");
        List<Animal> sortedAnimals = new ArrayList<>(filteredAnimals);
        sortedAnimals.sort((animal1, animal2) ->
                switch (sortBy.toLowerCase()) {
                    case "name" -> sortAnimalByName(animal1, animal2);
                    case "weight" -> sortAnimalByWeight(animal1, animal2);
                    case "type" -> sortAnimalByType(animal1, animal2);
                    case "category" -> sortAnimalByCategory(animal1, animal2);
                    case "gender" -> sortAnimalByGender(animal1, animal2);
                    case "cost" -> sortAnimalByCost(animal1, animal2);
                    default -> throw new IllegalArgumentException("Invalid sortBy value: " + sortBy);
                });
        log.info("Successfully filter and sorting animals");
            return sortedAnimals;
    }

}
