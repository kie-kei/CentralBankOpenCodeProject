package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;

@Getter
@Setter
public class LoginRequestDTO {
    private String username;
    private String password;
}
