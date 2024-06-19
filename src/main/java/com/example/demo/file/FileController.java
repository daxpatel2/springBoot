package com.example.demo.file;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileMetaDataRepository fileMetaDataRepository;

    @Autowired
    public FileController(FileMetaDataRepository fileMetadataRepository) {
        this.fileMetaDataRepository = fileMetadataRepository;
    }

    @GetMapping("/download")
    public ResponseEntity<Object> downloadFile() throws IOException {
        String filename = "/Users/daxpatel/Desktop/demo/myTestLog.log";
        File file = new File(filename);
        if (!file.exists()) {
            return ResponseEntity.status(404).body("File not founds");
        }
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body("File download in progress");
    }

    /**
     The @RequestParam("file") MultipartFile file annotation is a powerful feature in
     Spring Boot that simplifies handling file uploads in web applications.
     It allows you to easily bind an uploaded file to a method parameter, process the file,
     and handle any related operations, such as saving the file on the server or storing metadata in a database.
     *
     */
    @PostMapping
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        FileMetaData fileMetaData = new FileMetaData();
        File fileObject = new File("../"+file.getOriginalFilename());
        try {
            if(fileObject.createNewFile()) {
                try(FileOutputStream fout = new FileOutputStream(fileObject)) {
                    fout.write(file.getBytes());
                };
                // Save file metadata to the database
                FileMetaData fileMetadata = new FileMetaData(
                        file.getOriginalFilename(),
                        file.getContentType(),
                        file.getSize()
                );
                fileMetaDataRepository.save(fileMetadata);
                return ResponseEntity.ok("File is uploaded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed for some reason");
            }
        } catch (IOException io) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file upload failed due to an error: "+io.getMessage());
        }
    }
}
