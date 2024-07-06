package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ED807UpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ED807ResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.ED807ListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ED807UpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ed807")
@Tag(name = "ED807")
public interface ED807Controller {
    @Operation(summary = "Get ed807 by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ed807 is received",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ED807ResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "root by uuid not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping(value = "/{uuid}")
    ED807ResponseDTO findED807ByUuid(@PathVariable("uuid") UUID uuid)
            throws RootNotFoundException;

    @Operation(summary = "Update ed807 by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ed807 is updated",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ED807UpdateResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "ed807 by uuid not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping(value = "/{uuid}")
    ED807UpdateResponseDTO updateED807(@PathVariable("uuid") UUID uuid, @RequestBody ED807UpdateRequestDTO requestDTO)
            throws RootNotFoundException;

    @Operation(summary = "List ed807")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ed807 list is received",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ED807UpdateResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping
    ED807ListResponseDTO findED807List() throws RootNotFoundException;
}