package ru.bluewater.centralbankrestapi.api.dto.response.error;

import lombok.*;

@Getter
@Setter
public class ErrorResponseDTO {
    private String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }

    public ErrorResponseDTO() {
    }
}