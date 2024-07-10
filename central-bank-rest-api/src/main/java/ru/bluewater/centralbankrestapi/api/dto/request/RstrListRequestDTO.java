package ru.bluewater.centralbankrestapi.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@Getter
@Setter
public class RstrListRequestDTO {
    private UUID uuid;
    @Size(min = 4, max = 4, message = "Rstr length should be 4")
    @NotNull(message = "rstr should be not null")
    private String rstr;

    @NotNull(message = "rstrDate should be not null")
    private LocalDate rstrDate;
}
