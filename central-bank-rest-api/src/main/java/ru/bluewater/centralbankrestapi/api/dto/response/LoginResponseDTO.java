package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class LoginResponseDTO {
    private List<String> authorities;
    private String jwt;
}
