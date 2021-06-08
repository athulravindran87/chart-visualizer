package com.iscout.excelreader.controller;

import com.iscout.excelreader.service.ParserService;
import com.iscout.excelreader.util.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@Slf4j
public class ParserController {

    private final ParserService parserService;

    @Value("${file.location:/tmp}")
    private String location;

    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        Path copyLocation = getCopyLocation(file);
        try {

            if (!org.apache.commons.lang3.StringUtils.containsAny(file.getOriginalFilename(), ".xlsx", ".xls")) {
                return new ResponseEntity<>("Only xlsx and xls extension files can be processed", HttpStatus.BAD_REQUEST);
            }
            this.copyDataToFile(file, copyLocation);
            return ResponseEntity.status(HttpStatus.OK).body(this.parserService.getJsonFromExcel(copyLocation.toString()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("file upload failed");
        }
        finally {
            this.cleanUp(copyLocation);
        }
    }

    private void cleanUp(Path copyLocation) {
        try {
            FileUtils.delete(FileUtils.getFile(copyLocation.toString()));
        }
        catch (IOException e) {
            log.error("Unable to clean up file: {}", copyLocation.getFileName());
        }
    }

    private void copyDataToFile(MultipartFile file, Path copyLocation) throws IOException {
        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
    }

    private Path getCopyLocation(MultipartFile file) {
        Path copyLocation = Paths
                .get(this.location + File.separator + StringUtils.cleanPath(FileHelper.getBaseNameFromFileName(file.getOriginalFilename())
                                                                            + "_" + System.currentTimeMillis() + FileHelper.getFileExtension(file.getOriginalFilename())));
        return copyLocation;
    }
}
