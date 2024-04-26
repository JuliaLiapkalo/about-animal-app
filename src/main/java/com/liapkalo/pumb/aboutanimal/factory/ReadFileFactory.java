package com.liapkalo.pumb.aboutanimal.factory;

import com.liapkalo.pumb.aboutanimal.service.AnimalService;
import com.liapkalo.pumb.aboutanimal.service.ReadFileService;
import com.liapkalo.pumb.aboutanimal.service.impl.ReadCsvFileServiceImpl;
import com.liapkalo.pumb.aboutanimal.service.impl.ReadXmlFileServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

//add lombok
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReadFileFactory {

    AnimalService animalService;

    String CSV_TYPE = "/csv";
    String XML_TYPE = "/xml";

    public ReadFileService getFileReader(MultipartFile file) {
        if (Objects.requireNonNull(file.getContentType()).contains(CSV_TYPE)) {
            return new ReadCsvFileServiceImpl(animalService);
        } else if (Objects.requireNonNull(file.getContentType()).contains(XML_TYPE)) {
            return new ReadXmlFileServiceImpl(animalService);
        }
        throw new IllegalArgumentException("Unsupported file type");
    }

}
