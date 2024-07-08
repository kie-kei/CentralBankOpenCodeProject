package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AccRstrListRequestDTO {
    private String accRstr;
    private LocalDate accRstrDate;
    private Integer successorBIC;
}
