package com.liapkalo.pumb.aboutanimal.service.impl;

import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;
import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalsDto;
import com.liapkalo.pumb.aboutanimal.service.AnimalService;
import com.liapkalo.pumb.aboutanimal.service.ReadFileService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ReadXmlFileServiceImpl implements ReadFileService {

    AnimalService animalService;

    @Override
    public void readFile(MultipartFile file) {
        try {
            JAXBContext context = JAXBContext.newInstance(AnimalsDto.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            AnimalsDto animals = (AnimalsDto) unmarshaller.unmarshal(file.getInputStream());
            isObjectFull(animals.getAnimals()).forEach(a -> animalService.createAnimal(a));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<AnimalDto> isObjectFull(List<AnimalDto> animals) {
        return animals.stream().filter(a -> Objects.nonNull(a.getName()))
                        .filter(a -> Objects.nonNull(a.getType()))
                        .filter(a -> Objects.nonNull(a.getSex()))
                        .filter(a -> Objects.nonNull(a.getCost()))
                        .filter(a -> Objects.nonNull(a.getWeight()))
                        .filter(a -> Objects.isNull(a.getCategory()))
                .collect(Collectors.toList());

    }

}
