package ru.bluewater.centralbankrestapi.api.dto.request.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SWBICSUpdateRequestDTO {
    private String swbic;
    private Boolean defaultSWBIC;
}
