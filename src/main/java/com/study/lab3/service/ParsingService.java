package com.study.lab3.service;

import org.springframework.web.multipart.MultipartFile;

public interface ParsingService {

    void parseXML(MultipartFile file);
}
