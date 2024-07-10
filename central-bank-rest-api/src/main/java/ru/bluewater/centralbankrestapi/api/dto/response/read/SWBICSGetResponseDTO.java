package ru.bluewater.centralbankrestapi.api.dto.response.read;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SWBICSGetResponseDTO {
    private UUID uuid;
    private String swbic;
    private Boolean defaultSWBIC;
}
