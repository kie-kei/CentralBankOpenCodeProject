package ru.bluewater.centralbankrestsrc.controller;

import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.FileRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.FileUploadResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RootResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.service.FileResourceWithNameDTO;
import ru.bluewater.centralbankrestapi.api.exception.CbrException;
import ru.bluewater.centralbankrestapi.api.exception.FileNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.IncorrectFileTypeException;
import ru.bluewater.centralbankrestapi.controller.FileController;
import ru.bluewater.centralbankrestsrc.service.FileService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/file")
public class FileControllerImpl implements FileController {
    private final FileService fileService;

    @Autowired
    public FileControllerImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponseDTO> uploadXml(FileRequestDTO file) throws
            JAXBException, IOException, IncorrectFileTypeException
    {
        return ResponseEntity.ok(fileService.createRootFromFile(file));
    }

    @PostMapping("/cbr")
    public ResponseEntity<FileUploadResponseDTO> uploadXmlFromCBR() throws
            JAXBException, IOException, CbrException, IncorrectFileTypeException
    {
        return ResponseEntity.ok(fileService.createRootFromCBR());
    }

    @GetMapping("/download/{uuid}")
    public ResponseEntity<Resource> getFile(@PathVariable("uuid") UUID uuid) throws FileNotFoundException, JAXBException {
        FileResourceWithNameDTO fileResourceWithNameDTO = fileService.getFileByUuid(uuid);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResourceWithNameDTO.getFileName() + "\"")
                .body(fileResourceWithNameDTO.getResource());
    }

    @GetMapping(value = "/{uuid}")
    public RootResponseDTO getFileContent(@PathVariable("uuid") UUID uuid) throws FileNotFoundException {
        return fileService.getFileContentByUuid(uuid);
    }
}
