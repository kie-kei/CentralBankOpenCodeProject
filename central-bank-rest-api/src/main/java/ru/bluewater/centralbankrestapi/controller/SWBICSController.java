package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.RstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.SWBICSCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.RstrListUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.SWBICSUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.RstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.SWBICSCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.SWBICSListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.RstrListGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.SWBICSGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.RstrListUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.SWBICSUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.SWBICSNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "SWBICS")
public interface SWBICSController {

    @Operation(summary = "Find SWBICS by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SWBICS found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = SWBICSGetResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "SWBICS not found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/swbics/{swbics_uuid}")
    SWBICSGetResponseDTO findSwbicsByUuid(
            @PathVariable("swbics_uuid") UUID uuid
    ) throws SWBICSNotFoundException;

    @Operation(summary = "Update SWBICS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SWBICS updated",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = SWBICSUpdateResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PutMapping("/swbics/{swbics_uuid}")
    SWBICSUpdateResponseDTO updateSwbicsByUuid(
            @PathVariable("swbics_uuid") UUID uuid,
            @RequestBody(description = "SWBICS update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SWBICSUpdateRequestDTO.class)))
            SWBICSUpdateRequestDTO requestDTO
    ) throws SWBICSNotFoundException;


    @Operation(summary = "Find all SWBICS by BIC Directory Entry uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SWBICS found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = SWBICSListResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "SWBICS not found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/swbics")
    SWBICSListResponseDTO findListSwbics(
            @PathVariable("bic_directory_entry_uuid") UUID uuid) throws BicDirectoryEntryNotFoundException;


    @Operation(summary = "Create SWBICS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SWBICS created",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = SWBICSCreateResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/swbics")
    SWBICSCreateResponseDTO createSwbics(
            @PathVariable("bic_directory_entry_uuid") UUID uuid,
            @RequestBody(description = "SWBICS create request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SWBICSCreateRequestDTO.class)))
            SWBICSCreateRequestDTO requestDTO
    ) throws BicDirectoryEntryNotFoundException;

    @Operation(summary = "Delete SWBICS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SWBICS deleted",
                    content = { @Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @DeleteMapping("/swbics/{uuid}")
    void deleteSWBICS(@PathVariable("uuid") UUID uuid)
            throws SWBICSNotFoundException;
}
