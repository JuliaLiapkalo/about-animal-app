package com.liapkalo.pumb.aboutanimal.entity.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.util.List;

@Getter
@XmlRootElement(name = "animals")
public class AnimalsDto {
    @XmlElement(name = "animal")
    List<AnimalDto> animals;
 }
