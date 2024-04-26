package com.liapkalo.pumb.aboutanimal.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Sex {

    MALE("male"),
    FEMALE("female");

    private final String sex;

    Sex(String sex) {
        this.sex = sex;
    }
}
