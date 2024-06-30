package ru.bluewater.centralbankopencodeproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankopencodeproject.api.dto.request.LoginRequestDTO;
import ru.bluewater.centralbankopencodeproject.api.dto.request.RegistrationRequestDTO;
import ru.bluewater.centralbankopencodeproject.api.dto.response.LoginResponseDTO;
import ru.bluewater.centralbankopencodeproject.api.dto.response.RegistrationResponseDTO;
import ru.bluewater.centralbankopencodeproject.api.exception.UsernameAlreadyExistsException;
import ru.bluewater.centralbankopencodeproject.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> registerCustomerUser(@RequestBody RegistrationRequestDTO body) throws UsernameAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.registerUser(body));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO body) throws AuthenticationException {
        return ResponseEntity.ok(authenticationService.loginUser(body.getUsername(), body.getPassword()));
    }
}
