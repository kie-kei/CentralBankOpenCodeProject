package ru.bluewater.centralbankrestapi.api.dto.request.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class RstrListCreateRequestDTO {
    @Size(min = 4, max = 4, message = "Rstr length should be 4")
    @NotNull(message = "rstr should be not null")
    private String rstr;

    @NotNull(message = "rstrDate should be not null")
    private LocalDate rstrDate;
}
