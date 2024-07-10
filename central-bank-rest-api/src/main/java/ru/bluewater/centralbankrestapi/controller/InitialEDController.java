package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccRstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.InitialEDCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.InitialEDUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccRstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.InitialEDCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ED807GetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.InitialEDGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.InitialEDUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.InitialEDNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "InitialED")
public interface InitialEDController {
    @Operation(summary = "Find InitialED by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "InitialED found",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = InitialEDGetResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "InitialED not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/ed807/{ed807_uuid}/initialEd")
    InitialEDGetResponseDTO findInitialEdByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid
    ) throws InitialEDNotFoundException;

    @Operation(summary = "Update InitialED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "InitialED updated",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = InitialEDUpdateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PutMapping("/initialEd/{uuid}")
    InitialEDUpdateResponseDTO updateInitialEd(
            @PathVariable("uuid") UUID uuid,
            @RequestBody(description = "InitialED update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InitialEDUpdateRequestDTO.class)))
            InitialEDUpdateRequestDTO requestDTO
    ) throws InitialEDNotFoundException;
    @Operation(summary = "Create InitialED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "InitialED created",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = InitialEDCreateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/ed807/{ed807_uuid}/initialEd")
    InitialEDCreateResponseDTO createInitialEDByEd807Uuid(
            @PathVariable("ed807_uuid") UUID ed807Uuid,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "InitialED create request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InitialEDCreateRequestDTO.class)))
            InitialEDCreateRequestDTO requestDTO
    ) throws ED807NotFoundException;
    @Operation(summary = "Delete InitialED")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "InitialED deleted",
                    content = { @Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @DeleteMapping("/initialEd/{uuid}")
    void deleteInitialED(@PathVariable("uuid") UUID uuid)
            throws InitialEDNotFoundException;
}
