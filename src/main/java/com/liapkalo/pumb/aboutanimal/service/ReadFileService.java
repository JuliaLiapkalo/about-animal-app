package com.liapkalo.pumb.aboutanimal.service;

import jakarta.xml.bind.JAXBException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface ReadFileService {

    void readFile(MultipartFile file) throws JAXBException, FileNotFoundException;
}
