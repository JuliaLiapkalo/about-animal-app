package com.liapkalo.pumb.aboutanimal.utils;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.enums.Sex;
import com.liapkalo.pumb.aboutanimal.enums.Type;

public class AnimalUtils {

    public static Animal buildCatAnimal() {
        return Animal.builder()
                .id(1L)
                .name("Max")
                .sex(Sex.MALE)
                .category(1)
                .cost(10)
                .weight(15)
                .type(Type.CAT)
                .build();
    }

    public static Animal buildDogAnimal() {
        return Animal.builder()
                .id(1L)
                .name("Fred")
                .sex(Sex.MALE)
                .category(2)
                .cost(30)
                .weight(15)
                .type(Type.DOG)
                .build();
    }
}
