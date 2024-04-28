package com.liapkalo.pumb.aboutanimal.service.impl;

import com.liapkalo.pumb.aboutanimal.service.AnimalService;
import com.liapkalo.pumb.aboutanimal.service.ReadFileService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static com.liapkalo.pumb.aboutanimal.utils.BuildUtils.buildAnimalDto;

@Slf4j
@Service
@AllArgsConstructor
public class ReadCsvFileServiceImpl implements ReadFileService {

    AnimalService animalService;

    @Override
    public void readFile(MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            if (!isCvsValid(reader.readNext())) {
                log.error("Invalid CSV format");
                return;
            }

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                createAnimal(nextLine);
            }

        } catch (IOException | CsvValidationException e) {
            log.error("Error reading CSV file", e);
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
       return Arrays.stream(array).noneMatch(String::isEmpty);
    }
}

