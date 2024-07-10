package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.InitialEDCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.PartInfoCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.PartInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ParticipantInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.InitialEDCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.PartInfoCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.PartInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ParticipantInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.PartInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ParticipantInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.PartInfoNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "PartInfo")
public interface PartInfoController {

    @Operation(summary = "Find Part Info by ED807 uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Part Info found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = PartInfoGetResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Part Info not found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/ed807/{ed807_uuid}/partInfo")
    PartInfoGetResponseDTO findPartInfoByEd807Uuid(
            @PathVariable("ed807_uuid") UUID uuid
    ) throws PartInfoNotFoundException;

    @Operation(summary = "Update Part Info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Part Info updated",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = PartInfoUpdateResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PutMapping("/partInfo/{uuid}")
    PartInfoUpdateResponseDTO updatePartInfoByEd807Uuid(
            @PathVariable("uuid") UUID uuid,
            @RequestBody(description = "Part Info update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PartInfoUpdateRequestDTO.class)))
            PartInfoUpdateRequestDTO requestDTO
    ) throws PartInfoNotFoundException;

    @Operation(summary = "Create Part Info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Part Info created",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = PartInfoCreateResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/ed807/{ed807_uuid}/partInfo")
    PartInfoCreateResponseDTO createPartInfoByEd807Uuid(
            @PathVariable("ed807_uuid") UUID uuid,
            @RequestBody(description = "Part Info create request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PartInfoCreateRequestDTO.class)))
            PartInfoCreateRequestDTO requestDTO
    ) throws ED807NotFoundException;

    @Operation(summary = "Delete Part Info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Part Info deleted",
                    content = { @Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @DeleteMapping("/partInfo/{uuid}")
    void deletePartInfo(@PathVariable("uuid") UUID uuid)
            throws PartInfoNotFoundException;
}
