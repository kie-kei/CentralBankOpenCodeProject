package ru.bluewater.centralbankrestapi.api.dto.response.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ViolationResponseDTO {

    private final String fieldName;
    private final String message;

}