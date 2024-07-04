package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class FileUploadResponseDTO {
    private UUID uuid;
}
