package ru.bluewater.centralbankrestapi.api.dto.request.update;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccRstrListUpdateRequestDTO {
    private String accRstr;
    private LocalDate accRstrDate;
    private Integer successorBIC;
}
