package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class SWBICSRequestDTO {
    private UUID uuid;
    private String swbic;
    private Boolean defaultSWBIC;
}
