package ru.bluewater.centralbankrestsrc.controller;

import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.FileRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.FileUploadResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.service.FileResourceWithNameDTO;
import ru.bluewater.centralbankrestapi.api.exception.CbrException;
import ru.bluewater.centralbankrestapi.api.exception.IncorrectFileTypeException;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestapi.controller.FileController;
import ru.bluewater.centralbankrestsrc.service.FileService;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin
public class FileControllerImpl implements FileController {
    private final FileService fileService;

    @Autowired
    public FileControllerImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponseDTO> uploadXml(FileRequestDTO file, Principal principal) throws
            JAXBException, IOException, IncorrectFileTypeException
    {
        return ResponseEntity.ok(fileService.createRootFromFile(file, principal));
    }

    @PostMapping("/cbr")
    public ResponseEntity<FileUploadResponseDTO> uploadXmlFromCBR(Principal principal) throws
            JAXBException, IOException, CbrException, IncorrectFileTypeException
    {
        return ResponseEntity.ok(fileService.createRootFromCBR(principal));
    }

    @GetMapping("/download/{uuid}")
    public ResponseEntity<Resource> getFile(@PathVariable("uuid") UUID uuid) throws
            ED807NotFoundException, JAXBException
    {
        FileResourceWithNameDTO fileResourceWithNameDTO = fileService.getFileByRootUuid(uuid);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResourceWithNameDTO.getFileName() + "\"")
                .body(fileResourceWithNameDTO.getResource());
    }

}
