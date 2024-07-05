package ru.bluewater.centralbankrestapi.api.dto.response.error;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse {

    private final List<ViolationResponseDTO> violations;


    public ValidationErrorResponse(List<ViolationResponseDTO> violations) {
        this.violations = violations;
    }
}