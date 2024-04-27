package com.liapkalo.pumb.aboutanimal.service.impl;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;
import com.liapkalo.pumb.aboutanimal.entity.dto.FilteredAnimalDto;
import com.liapkalo.pumb.aboutanimal.repository.AnimalRepository;
import com.liapkalo.pumb.aboutanimal.service.AnimalService;
import com.liapkalo.pumb.aboutanimal.utils.ListUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.liapkalo.pumb.aboutanimal.utils.BuildUtils.buildAnimal;
import static com.liapkalo.pumb.aboutanimal.utils.FilterUtils.*;
import static com.liapkalo.pumb.aboutanimal.utils.SortUtils.*;

@Slf4j
@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    private static final int ANIMAL_BATCH_SIZE = 200;

    @Override
    public void createAnimal(AnimalDto animalDto) {
        log.info("Creating animal: {}", animalDto);
        animalRepository.save(buildAnimal(animalDto));
        log.info("Animal was created: {}", animalDto);
    }

    @Override
    public List<Animal> getFilteredAnimals(String type, String category, String gender, String sortBy) {
        List<Animal> filteredAnimals = new ArrayList<>();
        filteredAnimalsByBatch(new FilteredAnimalDto(type, category, gender), filteredAnimals);

        log.info("Start sorting animals");
        if (sortBy != null) {
            sortAnimals(sortBy, filteredAnimals);
        }
        log.info("Successfully sorting animals");
        return filteredAnimals;
    }

    private void filteredAnimalsByBatch(FilteredAnimalDto filter, List<Animal> filteredAnimals) {
        log.info("Start getting and filter animals");
        List<Long> allAnimalIds = animalRepository.findAllAnimalIds();
        for (List<Long> animalIdsByBatch : ListUtils.splitList(allAnimalIds, ANIMAL_BATCH_SIZE)) {
            filteredAnimals(filter, filteredAnimals, getAnimalsByBatch(animalIdsByBatch));
        }
        log.info("Successfully get filtered animals");
    }

    private void filteredAnimals(FilteredAnimalDto filter, List<Animal> filteredAnimals, List<Animal> animalList) {
        filteredAnimals.addAll(animalList.stream()
                .filter(animal -> filterByType(filter, animal))
                .filter(animal -> filterByCategory(filter, animal))
                .filter(animal -> filterByGender(filter, animal))
                .toList());
    }

    private void sortAnimals(String sortBy, List<Animal> filteredAnimals) {
        filteredAnimals.sort((animal1, animal2) ->
                switch (sortBy.toLowerCase()) {
            case "name" -> sortAnimalByName(animal1, animal2);
            case "weight" -> sortAnimalByWeight(animal1, animal2);
            case "type" -> sortAnimalByType(animal1, animal2);
            case "category" -> sortAnimalByCategory(animal1, animal2);
            case "gender" -> sortAnimalByGender(animal1, animal2);
            case "cost" -> sortAnimalByCost(animal1, animal2);
            default -> throw new IllegalArgumentException("Invalid sortBy value: " + sortBy);
        });
    }

    private List<Animal> getAnimalsByBatch(List<Long> animalIdsBatch) {
        return animalIdsBatch.stream()
                .map(id -> animalRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }
}
