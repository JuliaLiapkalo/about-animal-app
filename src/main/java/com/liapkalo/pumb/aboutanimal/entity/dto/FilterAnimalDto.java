package com.liapkalo.pumb.aboutanimal.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterAnimalDto {

    String type;
    String category;
    String sex;
}
