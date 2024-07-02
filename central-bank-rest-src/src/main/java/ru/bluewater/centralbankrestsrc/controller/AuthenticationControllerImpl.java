package ru.bluewater.centralbankrestsrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.bluewater.centralbankrestapi.api.dto.request.LoginRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.RegistrationRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.LoginResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RegistrationResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.UsernameAlreadyExistsException;
import ru.bluewater.centralbankrestapi.controller.AuthenticationController;
import ru.bluewater.centralbankrestsrc.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationControllerImpl implements AuthenticationController {
    private final AuthenticationService authenticationService;
    @Autowired
    public AuthenticationControllerImpl(AuthenticationService authenticationService) {
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
