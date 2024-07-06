package ru.bluewater.centralbankrestapi.api.dto.response.list;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SWBICSListResponseDTO {
    private UUID uuid;
    private String swbic;
    private Boolean defaultSWBIC;
}
