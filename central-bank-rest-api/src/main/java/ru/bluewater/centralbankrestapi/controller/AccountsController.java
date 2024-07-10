package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccountsCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BICDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccountsUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccountsCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BICDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.AccountsListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccountsGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.BICDirectoryEntryGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccountsUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.AccountsNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Accounts")
public interface AccountsController {
    @Operation(summary = "Find accounts by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts found",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = AccountsGetResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Accounts not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/accounts/{accounts_uuid}")
    AccountsGetResponseDTO findAccountsByUuid(
            @PathVariable("accounts_uuid") UUID uuid
    ) throws AccountsNotFoundException;
    @Operation(summary = "Update accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts updated",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = AccountsUpdateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PutMapping("/accounts/{accounts_uuid}")
    AccountsUpdateResponseDTO updateAccountsByUuid(
            @PathVariable("accounts_uuid") UUID uuid,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Accounts update request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountsUpdateRequestDTO.class)))
            AccountsUpdateRequestDTO requestDTO
    ) throws AccountsNotFoundException;

    @Operation(summary = "Find all accounts by BIC Directory Entry uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts found",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = AccountsListResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Accounts not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/accounts")
    AccountsListResponseDTO findListAccounts(
            @PathVariable("bic_directory_entry_uuid") UUID uuid
    ) throws BicDirectoryEntryNotFoundException;

    @Operation(summary = "Create accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts created",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = AccountsCreateResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/bicDirectoryEntry/{bic_directory_entry_uuid}/accounts")
    AccountsCreateResponseDTO createAccounts(
            @PathVariable("bic_directory_entry_uuid") UUID uuid,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Accounts create request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountsCreateRequestDTO.class)))
            AccountsCreateRequestDTO requestDTO
    ) throws BicDirectoryEntryNotFoundException;

    @Operation(summary = "Delete accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts deleted",
                    content = { @Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @DeleteMapping("/accounts/{accounts_uuid}")
    void deleteAccounts(@PathVariable("accounts_uuid") UUID uuid)
            throws AccountsNotFoundException;
}
