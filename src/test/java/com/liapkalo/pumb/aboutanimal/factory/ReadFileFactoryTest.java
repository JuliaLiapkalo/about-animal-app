package com.liapkalo.pumb.aboutanimal.factory;

import com.liapkalo.pumb.aboutanimal.service.AnimalService;
import com.liapkalo.pumb.aboutanimal.service.ReadFileService;
import com.liapkalo.pumb.aboutanimal.service.impl.ReadCsvFileServiceImpl;
import com.liapkalo.pumb.aboutanimal.service.impl.ReadXmlFileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ReadFileFactoryTest {


    @Mock
    private AnimalService animalService;

    @Mock
    private MultipartFile multipartFile;

    private ReadFileFactory readFileFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        readFileFactory = new ReadFileFactory(animalService);
    }

    @Test
    void testGetFileReaderWithCsvFile() {
        when(multipartFile.getContentType()).thenReturn("text/csv");

        ReadFileService fileReader = readFileFactory.getFileReader(multipartFile);

        assertInstanceOf(ReadCsvFileServiceImpl.class, fileReader);
    }

    @Test
    void testGetFileReaderWithXmlFile() {
        when(multipartFile.getContentType()).thenReturn("application/xml");

        ReadFileService fileReader = readFileFactory.getFileReader(multipartFile);

        assertInstanceOf(ReadXmlFileServiceImpl.class, fileReader);
    }

    @Test
    void testGetFileReaderWithUnsupportedFile() {
        when(multipartFile.getContentType()).thenReturn("image/jpeg");

        assertThrows(IllegalArgumentException.class, () -> {
            readFileFactory.getFileReader(multipartFile);
        });
    }
}
