package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
public class RegistrationRequestDTO {
    private String username;
    private String password;
}
