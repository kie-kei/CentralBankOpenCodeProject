package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BICDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BICDirectoryEntryFullUpdateRequestListDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryFullUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BICDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.full.BICDirectoryEntryFullResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.BICDirectoryEntryListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.BICDirectoryEntryGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bicDirectoryEntry")
@Tag(name = "BICDirectoryEntry")
public interface BICDirectoryEntryController {
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
    @PutMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}")
    BicDirectoryEntryUpdateResponseDTO updateBicDirectoryEntry(
            @PathVariable("bic_directory_entry_uuid") UUID uuid,
            @RequestBody(description = "BIC directory entry update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BicDirectoryEntryUpdateRequestDTO.class)))
            BicDirectoryEntryUpdateRequestDTO requestDTO) throws
            BicDirectoryEntryNotFoundException;

    @Operation(summary = "Delete BIC directory entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BIC directory entry deleted",
                    content = { @Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @DeleteMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}")
    void deleteBicDirectoryEntry(@PathVariable("bic_directory_entry_uuid") UUID uuid)
            throws BicDirectoryEntryNotFoundException;

    @Operation(summary = "Find BIC Directory Entry by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BIC directory entry found",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = BICDirectoryEntryGetResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "BIC Directory Entry not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}")
    BICDirectoryEntryGetResponseDTO findBicDirectoryEntryByUuid(
            @PathVariable("bic_directory_entry_uuid") UUID uuid
    ) throws BicDirectoryEntryNotFoundException;

    @Operation(summary = "Find all Bic Directory Entry by ED807 uuid. Returns complete information with all nested entities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Bic Directory Entry found",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = BICDirectoryEntryFullResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "ED807 not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/ed807/{ed807_uuid}/bicDirectoryEntry/full")
    BICDirectoryEntryFullResponseDTO findFullBicDirectoryEntryByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid) throws ED807NotFoundException;

    @Operation(summary = "Find all Bic Directory Entry by ED807 uuid. Returns main information without nested entities.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Bic Directory Entry found",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = BICDirectoryEntryListResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "ED807 not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/ed807/{ed807_uuid}/bicDirectoryEntry")
    BICDirectoryEntryListResponseDTO findBicDirectoryEntryListByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid) throws ED807NotFoundException;


    @Operation(summary = "Create BIC directory entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BIC directory entry created",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = BICDirectoryEntryCreateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/ed807/{ed807_uuid}/bicDirectoryEntry")
    BICDirectoryEntryCreateResponseDTO createBicDirectoryEntry(
            @PathVariable("ed807_uuid") UUID uuid,
            @RequestBody(description = "BIC directory entry create request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BICDirectoryEntryCreateRequestDTO.class)))
            BICDirectoryEntryCreateRequestDTO requestDTO
    ) throws ED807NotFoundException;

    @Operation(summary = "Full update several Bic Directory Entry with nested entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BIC directory entry updated",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = BICDirectoryEntryFullResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PutMapping("/bicDirectoryEntry")
    BICDirectoryEntryFullResponseDTO updateFullBicDirectoryEntry(
            @RequestBody(description = "BIC directory entry full update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BICDirectoryEntryFullUpdateRequestListDTO.class)))
            BICDirectoryEntryFullUpdateRequestListDTO requestDTO);
}
