package ru.bluewater.centralbankrestapi.api.dto.response.read;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AccRstrListGetResponseDTO {
    private UUID uuid;
    private String accRstr;
    private LocalDate accRstrDate;
    private Integer successorBIC;
}
