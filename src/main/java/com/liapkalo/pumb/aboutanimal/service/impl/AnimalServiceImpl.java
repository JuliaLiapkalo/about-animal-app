package com.liapkalo.pumb.aboutanimal.service.impl;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;
import com.liapkalo.pumb.aboutanimal.entity.dto.FilteredAnimalDto;
import com.liapkalo.pumb.aboutanimal.enums.Sex;
import com.liapkalo.pumb.aboutanimal.enums.Type;
import com.liapkalo.pumb.aboutanimal.repository.AnimalRepository;
import com.liapkalo.pumb.aboutanimal.service.AnimalService;
import com.liapkalo.pumb.aboutanimal.utils.ListUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    private static final int PAYMENT_BATCH_SIZE = 200;

    @Override
    public void createAnimal(AnimalDto animalDto) {
       animalRepository.save(buildAnimal(animalDto));
    }

    @Override
    public AnimalDto buildAnimalDto(String[] line) {
        return AnimalDto.builder()
                .name(line[0].trim())
                .type(line[1].trim())
                .sex(line[2].trim())
                .weight(Integer.parseInt(line[3].trim()))
                .cost(Integer.parseInt(line[4].trim()))
                .build();
    }

    @Override
    public List<Animal> getFilteredAnimals(String type, String category, String gender, String sortBy) {
        List<Long> allAnimalIds = animalRepository.findAllAnimalIds();
        List<Animal> filteredAnimals = new ArrayList<>();

        for (List<Long> animalIdsBatch : ListUtils.splitList(allAnimalIds, PAYMENT_BATCH_SIZE)) {
            filteredAnimals(
                    new FilteredAnimalDto(type, category, gender),
                    filteredAnimals,
                    getAnimalsBatch(animalIdsBatch));
        }

        if (sortBy != null) {
            sortAnimals(sortBy, filteredAnimals);
        }

        return filteredAnimals;
    }

    private static void sortAnimals(String sortBy, List<Animal> filteredAnimals) {
        filteredAnimals.sort((animal1, animal2) -> {
            return switch (sortBy.toLowerCase()) {
                case "name" -> animal1.getName().compareTo(animal2.getName());
                case "weight" -> Integer.compare(animal1.getWeight(), animal2.getWeight());
                case "type" -> animal1.getType().compareTo(animal2.getType());
                case "category" -> Integer.compare(animal1.getCategory(), animal2.getCategory());
                case "gender" -> animal1.getSex().compareTo(animal2.getSex());
                case "cost" -> Integer.compare(animal1.getCost(), animal2.getCost());
                default -> throw new IllegalArgumentException("Invalid sortBy value: " + sortBy);
            };
        });
    }

    private void filteredAnimals(FilteredAnimalDto filter, List<Animal> filteredAnimals, List<Animal> animalList) {
        filteredAnimals.addAll(animalList.stream()
                .filter(animal -> filter.getType() == null || Objects.equals(animal.getType(), getType(filter.getType())))
                .filter(animal -> filter.getCategory() == null || animal.getCategory() == Integer.parseInt(filter.getCategory()))
                .filter(animal -> filter.getGender() == null || Objects.equals(animal.getSex(), getSex(filter.getGender())))
                .toList());
    }

    private List<Animal> getAnimalsBatch(List<Long> animalIdsBatch) {
        return animalIdsBatch.stream()
                .map(id -> animalRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }


    private Sex getSex(String sex) {
        return Sex.valueOf("MALE").getSex().equals(sex) ? Sex.MALE : Sex.FEMALE;
    }

    private Type getType(String type) {
        return Type.valueOf("CAT").getAnimalType().equals(type) ? Type.CAT : Type.DOG;
    }

    private int findCategory(Integer cost) {
        int category;
        if (cost > 60) {
            category = 4;
        } else if (cost > 40) {
            category = 3;
        } else if (cost > 20) {
            category = 2;
        } else {
            category = 1;
        }
        return category;
    }

    private Animal buildAnimal(AnimalDto animalDto) {
        return Animal.builder()
                .name(animalDto.getName())
                .type(getType(animalDto.getType()))
                .category(findCategory(animalDto.getCost()))
                .cost(animalDto.getCost())
                .sex(getSex(animalDto.getSex()))
                .weight(animalDto.getWeight())
                .build();
    }
}
