package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.update.InitialEDUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ParticipantInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.InitialEDGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ParticipantInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.InitialEDUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ParticipantInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ParticipantInfoNotFoundException;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1")
@Tag(name = "ParticipantInfo")
public interface ParticipantInfoController {

    @Operation(summary = "Find Participant Info by BIC Directory Entry uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participant Info found",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ParticipantInfoGetResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "InitialED not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/participantInfo")
    ParticipantInfoGetResponseDTO findParticipantInfoByUuid(@PathVariable("bic_directory_entry_uuid") UUID uuid)
            throws ParticipantInfoNotFoundException;

    @Operation(summary = "Update Participant Info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participant Info updated",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ParticipantInfoUpdateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PutMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/participantInfo")
   ParticipantInfoUpdateResponseDTO updateParticipantInfoByUuid(
            @PathVariable("bic_directory_entry_uuid") UUID uuid,
            @RequestBody(description = "Participant Info update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParticipantInfoUpdateRequestDTO.class)))
            ParticipantInfoUpdateRequestDTO requestDTO
    ) throws ParticipantInfoNotFoundException;
}
