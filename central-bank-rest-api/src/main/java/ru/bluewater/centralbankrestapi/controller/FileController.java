package ru.bluewater.centralbankrestapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.JAXBException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.FileRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.*;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.CbrException;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.IncorrectFileTypeException;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/file")
@Tag(name = "File")
public interface FileController {
    @Operation(summary = "Upload xml file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "XML file uploaded",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = FileUploadResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/upload")
    ResponseEntity<FileUploadResponseDTO> uploadXml(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "xml File upload request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FileRequestDTO.class)))
             FileRequestDTO fileRequestDTO, @Parameter(hidden = true) Principal principal) throws
            JAXBException, IOException, IncorrectFileTypeException;

    @Operation(summary = "Update data from cbr")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data is updated",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = FileUploadResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/cbr")
    ResponseEntity<FileUploadResponseDTO> uploadXmlFromCBR(@Parameter(hidden = true) Principal principal) throws
            JAXBException, IOException, CbrException, IncorrectFileTypeException;


    @Operation(summary = "Get xml file by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "XML received",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = Resource.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parse data to xml",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "XML File not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/download/{uuid}")
    ResponseEntity<Resource> getFile(@PathVariable("uuid") UUID uuid)
            throws RootNotFoundException, JAXBException;


}
