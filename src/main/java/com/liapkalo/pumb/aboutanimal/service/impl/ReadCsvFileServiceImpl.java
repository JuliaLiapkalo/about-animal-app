package com.liapkalo.pumb.aboutanimal.service.impl;

import com.liapkalo.pumb.aboutanimal.service.AnimalService;
import com.liapkalo.pumb.aboutanimal.service.ReadFileService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;

import static com.liapkalo.pumb.aboutanimal.utils.DtoUtils.buildAnimalDto;


@Service
@AllArgsConstructor
public class ReadCsvFileServiceImpl implements ReadFileService {

    AnimalService animalService;

    @Override
    public void readFile(MultipartFile file) {

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] csvHeader = reader.readNext();
            if (isCvsValid(csvHeader)) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    createAnimal(nextLine);
                }
            }

            } catch(IOException | CsvValidationException e){
                throw new RuntimeException(e);
            }
    }

    private void createAnimal(String[] nextLine) {
        if (isLineValid(nextLine)) {
            animalService.createAnimal(buildAnimalDto(nextLine));
        }
    }

    private boolean isCvsValid(String[] csvHeader) {
        return csvHeader[0].equalsIgnoreCase("name") &&
                csvHeader[1].equalsIgnoreCase("type") &&
                csvHeader[2].equalsIgnoreCase("sex") &&
                csvHeader[3].equalsIgnoreCase("weight") &&
                csvHeader[4].equalsIgnoreCase("cost");
    }

    private boolean isLineValid(String[] nextLine) {
        return nextLine.length == 5 && isObjectFull(nextLine);
    }

    private boolean isObjectFull(String[] array) {
        for (String str : array) {
            if (str.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

