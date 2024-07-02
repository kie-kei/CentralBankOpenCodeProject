package ru.bluewater.centralbankrestapi.api.dto.response.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {

    private final List<ViolationResponseDTO> violations;


}