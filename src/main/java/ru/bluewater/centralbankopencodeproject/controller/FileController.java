package ru.bluewater.centralbankopencodeproject.controller;

import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bluewater.centralbankopencodeproject.api.dto.response.FileUploadResponseDTO;
import ru.bluewater.centralbankopencodeproject.api.dto.service.FileResourceWithNameDTO;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.service.FileService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadXml(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        try {

            return ResponseEntity.ok(fileService.createRootFromFile(file));

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error parsing XML file: " + e.getMessage());
        }
    }
    @GetMapping("/download/{uuid}")
    public ResponseEntity<Resource> getFile(@PathVariable("uuid") UUID uuid) {
        FileResourceWithNameDTO fileResourceWithNameDTO = fileService.getFileByUuid(uuid);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResourceWithNameDTO.getFileName() + "\"")
                .body(fileResourceWithNameDTO.getResource());
    }
    @GetMapping(value = "{uuid}")
    public RootEntity getFileContent(@PathVariable("uuid") UUID uuid){
        return fileService.getFileContentByUuid(uuid);
    }
}
