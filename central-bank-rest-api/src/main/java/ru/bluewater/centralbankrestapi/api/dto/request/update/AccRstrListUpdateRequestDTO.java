package ru.bluewater.centralbankrestapi.api.dto.request.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccRstrListUpdateRequestDTO {
    @Size(min = 4, max = 4, message = "accRstr length should be 4")
    @NotNull(message = "accRstr should be not null")
    private String accRstr;
    @NotNull(message = "accRstrDate should be not null")
    private LocalDate accRstrDate;
    @Min(value = 100000000, message = "successorBIC range should be from 100000000 to 999999999")
    @Max(value = 999999999, message = "successorBIC range should be from 100000000 to 999999999")
    private Integer successorBIC;
}
