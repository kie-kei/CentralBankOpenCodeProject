package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@Getter
@Setter
public class RstrListRequestDTO {
    private UUID uuid;
    private String rstr;
    private LocalDate rstrDate;
}
