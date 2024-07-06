package ru.bluewater.centralbankrestapi.api.dto.request.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AccRstrListCreateRequestDTO {
    private String accRstr;
    private LocalDate accRstrDate;
    private Integer successorBIC;
}
