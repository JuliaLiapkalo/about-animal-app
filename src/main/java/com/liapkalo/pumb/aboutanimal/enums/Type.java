package com.liapkalo.pumb.aboutanimal.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Type {

    CAT("cat"),
    DOG("dog");

    private final String animalType;

    Type(String animalType) {
        this.animalType = animalType;
    }
}
