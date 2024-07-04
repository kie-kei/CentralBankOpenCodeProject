package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;



@Getter
@Setter
public class AccRstrListResponseDTO {
    private UUID uuid;
    private String accRstr;
    private LocalDate accRstrDate;
    private Integer successorBIC;
}
