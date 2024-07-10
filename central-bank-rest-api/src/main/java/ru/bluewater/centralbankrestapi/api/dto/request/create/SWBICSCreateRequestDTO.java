package ru.bluewater.centralbankrestapi.api.dto.request.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SWBICSCreateRequestDTO {
    @NotNull(message = "swbic should be not null")
    @Size(min = 1, max = 11, message = "swbic length should be from 1 to 11")
    private String swbic;

    @NotNull(message = "defaultSWBIC should be not null")
    private Boolean defaultSWBIC;
}
