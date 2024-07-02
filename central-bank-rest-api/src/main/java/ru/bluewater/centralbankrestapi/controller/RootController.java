package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bluewater.centralbankrestapi.api.dto.response.FileUploadResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RootResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/root")
@Tag(name = "Root")
public interface RootController {
    @Operation(summary = "Get root by uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Root is received",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = FileUploadResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "root by uuid not found",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @GetMapping(value = "/{uuid}")
    RootResponseDTO findRootByUuid(@PathVariable("uuid") UUID uuid) throws RootNotFoundException;
}