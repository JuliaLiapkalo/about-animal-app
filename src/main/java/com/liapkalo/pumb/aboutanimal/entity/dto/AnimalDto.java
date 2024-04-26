package com.liapkalo.pumb.aboutanimal.entity.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlRootElement(name = "animal")
public class AnimalDto {

    @XmlElement
    String name;
    @XmlElement
    String type;
    @XmlElement
    String sex;
    @XmlElement
    Integer weight;
    @XmlElement
    Integer cost;
    @XmlElement
    Integer category;

}
