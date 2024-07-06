package ru.bluewater.centralbankrestapi.api.dto.request.create;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SWBICSCreateRequestDTO {
    private String swbic;
    private Boolean defaultSWBIC;
}
