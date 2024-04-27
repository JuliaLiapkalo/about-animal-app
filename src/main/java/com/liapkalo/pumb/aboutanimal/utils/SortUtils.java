package com.liapkalo.pumb.aboutanimal.utils;

import com.liapkalo.pumb.aboutanimal.entity.Animal;

public class SortUtils {

    public SortUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static int sortAnimalByName(Animal animal1, Animal animal2) {
        return animal1.getName().compareTo(animal2.getName());
    }

    public static int sortAnimalByWeight(Animal animal1, Animal animal2) {
        return Integer.compare(animal1.getWeight(), animal2.getWeight());
    }

    public static int sortAnimalByType(Animal animal1, Animal animal2) {
        return animal1.getType().compareTo(animal2.getType());
    }

    public static int sortAnimalByCategory(Animal animal1, Animal animal2) {
        return Integer.compare(animal1.getCategory(), animal2.getCategory());
    }

    public static int sortAnimalByGender(Animal animal1, Animal animal2) {
        return animal1.getSex().compareTo(animal2.getSex());
    }

    public static int sortAnimalByCost(Animal animal1, Animal animal2) {
        return Integer.compare(animal1.getCost(), animal2.getCost());
    }


}
