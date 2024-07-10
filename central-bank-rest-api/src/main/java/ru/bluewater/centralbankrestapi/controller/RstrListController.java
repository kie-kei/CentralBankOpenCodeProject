package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.PartInfoCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.RstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.PartInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.RstrListUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.PartInfoCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.RstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.RstrListListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.PartInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.RstrListGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.PartInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.RstrListUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ParticipantInfoNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.RstrListNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "RstrList")
public interface RstrListController {
    @Operation(summary = "Find Rstr List by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rstr List found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = RstrListGetResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Part Info not found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/rstrList/{rstr_list_uuid}")
    RstrListGetResponseDTO findRstrListByUuid(
            @PathVariable("rstr_list_uuid") UUID uuid
    ) throws RstrListNotFoundException;

    @Operation(summary = "Find all Rstr List by Participant Info uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rstr List found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = RstrListListResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Rstr List not found",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/participantInfo/{participant_info_uuid}/rstrList")
    RstrListListResponseDTO findListOfRstrList(
            @PathVariable("participant_info_uuid") UUID uuid
    ) throws ParticipantInfoNotFoundException;

    @Operation(summary = "Create Rstr List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rstr List created",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = RstrListCreateResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/participantInfo/{participant_info_uuid}/rstrList")
    RstrListCreateResponseDTO createRstrList(
            @PathVariable("participant_info_uuid") UUID uuid,
            @RequestBody(description = "Rstr List create request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RstrListCreateRequestDTO.class)))
            RstrListCreateRequestDTO requestDTO
    ) throws ParticipantInfoNotFoundException;


    @Operation(summary = "Update Rstr List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rstr List updated",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = RstrListUpdateResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = {@Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PutMapping("/rstrList/{rstr_list_uuid}")
    RstrListUpdateResponseDTO updateRstrList(
            @PathVariable("rstr_list_uuid") UUID uuid,
            @RequestBody(description = "Rstr List update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RstrListUpdateRequestDTO.class)))
            RstrListUpdateRequestDTO requestDTO
    ) throws RstrListNotFoundException;


    @Operation(summary = "Delete Rstr List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rstr List deleted",
                    content = { @Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @DeleteMapping("/rstrList/{uuid}")
    void deleteRstrList(@PathVariable("uuid") UUID uuid)
            throws RstrListNotFoundException;
}
