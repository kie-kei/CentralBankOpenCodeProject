package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccRstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccountsCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccRstrListUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccountsUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccRstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccountsCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.AccRstrListListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccRstrListGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccountsGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccRstrListUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccountsUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.AccRstrListNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.AccountsNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "AccRstrList")
public interface AccRstrListController {
    @Operation(summary = "Find Acc Rstr List by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acc Rstr List found",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = AccRstrListGetResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Acc Rstr List not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/accRstrList/{acc_rstr_list_uuid}")
    AccRstrListGetResponseDTO findAccRstrListByUuid(
            @PathVariable("acc_rstr_list_uuid") UUID uuid
    ) throws AccRstrListNotFoundException;

    @Operation(summary = "Update Acc Rstr List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acc Rstr List updated",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = AccRstrListUpdateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PutMapping("/accRstrList/{acc_rstr_list_uuid}")
    AccRstrListUpdateResponseDTO updateAccRstrListByUuid(
            @PathVariable("acc_rstr_list_uuid") UUID uuid,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Acc Rstr List update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccRstrListUpdateRequestDTO.class)))
            @RequestBody AccRstrListUpdateRequestDTO requestDTO
    ) throws AccRstrListNotFoundException;

    @Operation(summary = "Find all Acc Rstr List by Accounts uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acc Rstr List found",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = AccRstrListListResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Acc Rstr List not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/accounts/{accounts_uuid}/accRstrList")
    AccRstrListListResponseDTO findListAccRstrList(
            @PathVariable("accounts_uuid") UUID uuid
    ) throws AccountsNotFoundException;

    @Operation(summary = "Create Acc Rstr List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acc Rstr List created",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = AccRstrListCreateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/accounts/{accounts_uuid}/accRstrList")
    AccRstrListCreateResponseDTO createAccRstrList(
            @PathVariable("accounts_uuid") UUID uuid,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Acc Rstr List create request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccRstrListCreateRequestDTO.class)))
            AccRstrListCreateRequestDTO requestDTO
    ) throws AccountsNotFoundException;

    @Operation(summary = "Delete Acc Rstr List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acc Rstr List deleted",
                    content = { @Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @DeleteMapping("/accRstrList/{acc_rstr_list_uuid}")
    void deleteAccRstrList(@PathVariable("acc_rstr_list_uuid") UUID uuid)
            throws AccRstrListNotFoundException;
}
