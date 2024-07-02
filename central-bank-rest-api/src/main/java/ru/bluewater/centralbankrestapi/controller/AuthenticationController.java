package ru.bluewater.centralbankrestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.LoginRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.RegistrationRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.error.ErrorResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.LoginResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RegistrationResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.UsernameAlreadyExistsException;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication")
public interface AuthenticationController {
    @Operation(summary = "User registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is registered",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = RegistrationResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/register")
    ResponseEntity<RegistrationResponseDTO> registerCustomerUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User registration request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegistrationRequestDTO.class)))
            @RequestBody RegistrationRequestDTO body) throws UsernameAlreadyExistsException;

    @Operation(summary = "User logining")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is logged in",
                    content = { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = LoginResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content =  { @Content(mediaType = "application/hal+json",
                            schema = @Schema(implementation = ErrorResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "text/plain"))})
    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> loginUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User logining request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequestDTO.class)))
            @RequestBody LoginRequestDTO body) throws AuthenticationException;

}