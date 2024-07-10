package ru.bluewater.centralbankrestapi.api.dto.request.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class RstrListCreateRequestDTO {
    private String rstr;
    private LocalDate rstrDate;
}
