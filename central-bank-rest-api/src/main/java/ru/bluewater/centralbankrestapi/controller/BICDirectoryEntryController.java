package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.JAXBException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.FileRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BicDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.FileUploadResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BicDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.IncorrectFileTypeException;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bicDirectoryEntry")
@Tag(name = "BICDirectoryEntry")
public interface BICDirectoryEntryController {
    @Operation(summary = "Create BIC directory entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BIC directory entry created",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = BicDirectoryEntryCreateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping
    ResponseEntity<BicDirectoryEntryCreateResponseDTO> createBICDirectoryEntry(
            @RequestBody(description = "BIC directory entry create request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BicDirectoryEntryCreateRequestDTO.class)))
            BicDirectoryEntryCreateRequestDTO requestDTO, @Parameter(hidden = true) Principal principal) throws
            RootNotFoundException;


    @Operation(summary = "Update BIC directory entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BIC directory entry updated",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = BicDirectoryEntryUpdateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PutMapping("/{uuid}")
    ResponseEntity<BicDirectoryEntryUpdateResponseDTO> updateBICDirectoryEntry(
            @PathVariable("uuid") UUID uuid,
            @RequestBody(description = "BIC directory entry update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BicDirectoryEntryUpdateRequestDTO.class)))
            BicDirectoryEntryUpdateRequestDTO requestDTO, @Parameter(hidden = true) Principal principal) throws
            BicDirectoryEntryNotFoundException, RootNotFoundException;

    @Operation(summary = "Delete BIC directory entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BIC directory entry deleted",
                    content = { @Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @DeleteMapping("/{uuid}")
    ResponseEntity<?> deleteBICDirectoryEntry(@PathVariable("uuid") UUID uuid) throws BicDirectoryEntryNotFoundException;
}
