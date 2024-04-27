package com.liapkalo.pumb.aboutanimal.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class XmlUtils {

    public static String buildValidXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<animals>" +
                "    <animal>" +
                "        <name>Max</name>" +
                "        <type>Cat</type>" +
                "        <sex>Male</sex>" +
                "        <weight>5</weight>" +
                "        <cost>50</cost>" +
                "    </animal>" +
                "    <animal>" +
                "        <name>Luna</name>" +
                "        <type>Dog</type>" +
                "        <sex>Female</sex>" +
                "        <weight>10</weight>" +
                "        <cost>70</cost>" +
                "    </animal>" +
                "</animals>";
    }

    public static String buildXmlWithInvalidAnimal() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<animals>" +
                "    <animal>" +
                "        <name>Max</name>" +
                "        <type>Cat</type>" +
                "        <sex>Male</sex>" +
                "        <cost>50</cost>" +
                "    </animal>" +
                "</animals>";
    }
}
