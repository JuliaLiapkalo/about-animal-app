package com.liapkalo.pumb.aboutanimal.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CsvUtils {

    public static String buildCsvWithTwoAnimals() {
        return """
                name,type,sex,weight,cost
                Max,Cat,Male,5,50
                Luna,Dog,Female,10,70
                """;
    }

    public static String buildInvalidCsv() {
        return "invalid,format,csv,file\n";
    }

    public static String buildCsvWithInvalidAnimal() {
        return """
                name,type,sex,weight,cost
                Max,,Male,5,50
                """;
    }

}
