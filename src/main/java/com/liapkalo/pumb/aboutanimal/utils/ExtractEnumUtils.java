package com.liapkalo.pumb.aboutanimal.utils;

import com.liapkalo.pumb.aboutanimal.enums.Sex;
import com.liapkalo.pumb.aboutanimal.enums.Type;

public class ExtractEnumUtils {

    public ExtractEnumUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Sex getSex(String sex) {
        return Sex.valueOf("MALE").getSex().equals(sex) ? Sex.MALE : Sex.FEMALE;
    }

    public static Type getType(String type) {
        return Type.valueOf("CAT").getAnimalType().equals(type) ? Type.CAT : Type.DOG;
    }
}
