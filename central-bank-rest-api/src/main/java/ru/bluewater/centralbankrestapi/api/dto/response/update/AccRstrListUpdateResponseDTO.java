package ru.bluewater.centralbankrestapi.api.dto.response.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AccRstrListUpdateResponseDTO {
    private UUID uuid;
    private String accRstr;
    private LocalDate accRstrDate;
    private Integer successorBIC;
}
