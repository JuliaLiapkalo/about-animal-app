package com.liapkalo.pumb.aboutanimal.service;

import com.liapkalo.pumb.aboutanimal.service.impl.ReadXmlFileServiceImpl;
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

import static com.liapkalo.pumb.aboutanimal.utils.XmlUtils.buildValidXml;
import static com.liapkalo.pumb.aboutanimal.utils.XmlUtils.buildXmlWithInvalidAnimal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ReadXmlFileServiceTests {

    @Mock
    AnimalService animalService;

    @InjectMocks
    ReadXmlFileServiceImpl readXmlFileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readFile_ValidXml() throws IOException {
        String xmlData = buildValidXml();
        InputStream inputStream = new ByteArrayInputStream(xmlData.getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "animals.xml", "/xml", inputStream);

        readXmlFileService.readFile(file);

        verify(animalService, times(2)).createAnimal(any());
    }

    @Test
    void readFile_InvalidXml() throws IOException {
        String xmlData = buildXmlWithInvalidAnimal();
        InputStream inputStream = new ByteArrayInputStream(xmlData.getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "animals.xml", "/xml", inputStream);

        readXmlFileService.readFile(file);

        verify(animalService, Mockito.never()).createAnimal(any());
    }
}
