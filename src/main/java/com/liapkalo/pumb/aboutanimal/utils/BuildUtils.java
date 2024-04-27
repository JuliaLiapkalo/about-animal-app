package com.liapkalo.pumb.aboutanimal.utils;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;

import static com.liapkalo.pumb.aboutanimal.utils.ExtractEnumUtils.getSex;
import static com.liapkalo.pumb.aboutanimal.utils.ExtractEnumUtils.getType;

public class BuildUtils {

    public BuildUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static AnimalDto buildAnimalDto(String[] line) {
        return AnimalDto.builder()
                .name(line[0].trim())
                .type(line[1].trim())
                .sex(line[2].trim())
                .weight(Integer.parseInt(line[3].trim()))
                .cost(Integer.parseInt(line[4].trim()))
                .build();
    }

    public static Animal buildAnimal(AnimalDto animalDto) {
        return Animal.builder()
                .name(animalDto.getName())
                .type(getType(animalDto.getType()))
                .category(findCategory(animalDto.getCost()))
                .cost(animalDto.getCost())
                .sex(getSex(animalDto.getSex()))
                .weight(animalDto.getWeight())
                .build();
    }


    private static int findCategory(Integer cost) {
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
}
