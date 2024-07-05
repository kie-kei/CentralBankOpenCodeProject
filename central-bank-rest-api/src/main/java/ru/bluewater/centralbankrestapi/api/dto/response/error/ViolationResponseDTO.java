package ru.bluewater.centralbankrestapi.api.dto.response.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViolationResponseDTO {

    private final String fieldName;
    private final String message;

    public ViolationResponseDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}