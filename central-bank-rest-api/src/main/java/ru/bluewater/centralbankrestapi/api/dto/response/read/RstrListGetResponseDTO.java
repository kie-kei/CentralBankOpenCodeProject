package ru.bluewater.centralbankrestapi.api.dto.response.read;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class RstrListGetResponseDTO {
    private UUID uuid;
    private String rstr;
    private LocalDate rstrDate;
}
