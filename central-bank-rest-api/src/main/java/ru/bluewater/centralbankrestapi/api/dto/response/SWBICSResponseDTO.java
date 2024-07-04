package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class SWBICSResponseDTO {
    private UUID uuid;
    private String swbic;
    private Boolean defaultSWBIC;
}
