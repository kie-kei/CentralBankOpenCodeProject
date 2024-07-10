package ru.bluewater.centralbankrestapi.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class SWBICSRequestDTO {
    private UUID uuid;
    @NotNull(message = "swbic should be not null")
    @Size(min = 1, max = 11, message = "swbic length should be from 1 to 11")
    private String swbic;

    @NotNull(message = "defaultSWBIC should be not null")
    private Boolean defaultSWBIC;
}
