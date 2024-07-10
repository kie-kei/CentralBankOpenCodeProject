package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class RstrListResponseDTO {
    private UUID uuid;
    private String rstr;
    private LocalDate rstrDate;
}