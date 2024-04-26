package com.liapkalo.pumb.aboutanimal.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilteredAnimalDto {

    String type;
    String category;
    String gender;
}
