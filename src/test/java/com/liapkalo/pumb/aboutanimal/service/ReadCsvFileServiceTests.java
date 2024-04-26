package com.liapkalo.pumb.aboutanimal.service;

import com.liapkalo.pumb.aboutanimal.entity.dto.AnimalDto;
import com.liapkalo.pumb.aboutanimal.service.impl.ReadCsvFileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.liapkalo.pumb.aboutanimal.utils.CsvUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReadCsvFileServiceTests {


    @Mock
    private AnimalService animalService;

    @InjectMocks
    private ReadCsvFileServiceImpl readCsvFileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadFile_ValidCsv() throws IOException {
        String csvData = buildCsvWithTwoAnimals();
        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "animals.csv", "text/csv", inputStream);

        when(animalService.buildAnimalDto(any())).thenReturn(new AnimalDto());

        readCsvFileService.readFile(file);

        verify(animalService, times(2)).createAnimal(any());
    }

    @Test
    void testReadFile_InvalidCsv() throws IOException {
        String csvData = buildInvalidCsv();
        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "animals.csv", "text/csv", inputStream);

        readCsvFileService.readFile(file);

        verify(animalService, Mockito.never()).createAnimal(any());
    }

    @Test
    void testReadFile_ValidCsvInvalidAnimal() throws IOException {
        String csvData = buildCsvWithInvalidAnimal();
        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "animals.csv", "text/csv", inputStream);

        when(animalService.buildAnimalDto(any())).thenReturn(new AnimalDto());

        readCsvFileService.readFile(file);

        verify(animalService, times(1)).createAnimal(any());
    }
}
