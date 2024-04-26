package com.liapkalo.pumb.aboutanimal.utils;

import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;

public class DtoUtils {

    public static AnimalDto buildAnimalDto(String[] line) {
        return AnimalDto.builder()
                .name(line[0].trim())
                .type(line[1].trim())
                .sex(line[2].trim())
                .weight(Integer.parseInt(line[3].trim()))
                .cost(Integer.parseInt(line[4].trim()))
                .build();
    }
}
