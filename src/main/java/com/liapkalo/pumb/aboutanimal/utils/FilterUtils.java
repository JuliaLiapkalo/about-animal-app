package com.liapkalo.pumb.aboutanimal.utils;

import com.liapkalo.pumb.aboutanimal.entity.Animal;
import com.liapkalo.pumb.aboutanimal.entity.dto.FilteredAnimalDto;

import java.util.Objects;

import static com.liapkalo.pumb.aboutanimal.utils.ExtractEnumUtils.getSex;
import static com.liapkalo.pumb.aboutanimal.utils.ExtractEnumUtils.getType;

public class FilterUtils {

    public FilterUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean filterByGender(FilteredAnimalDto filter, Animal animal) {
        return filter.getGender() == null || Objects.equals(animal.getSex(), getSex(filter.getGender()));
    }

    public static boolean filterByCategory(FilteredAnimalDto filter, Animal animal) {
        return filter.getCategory() == null || animal.getCategory() == Integer.parseInt(filter.getCategory());
    }

    public static boolean filterByType(FilteredAnimalDto filter, Animal animal) {
        return filter.getType() == null || Objects.equals(animal.getType(), getType(filter.getType()));
    }
}
