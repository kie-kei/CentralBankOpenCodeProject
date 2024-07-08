package ru.bluewater.centralbankrestapi.api.dto.response.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SWBICSUpdateResponseDTO {
    private UUID uuid;
    private String swbic;
    private Boolean defaultSWBIC;
}
