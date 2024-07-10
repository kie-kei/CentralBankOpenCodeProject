package ru.bluewater.centralbankrestapi.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class InitialEDRequestDTO {
    @Min(value = 1, message = "number of electronic message (EDNo) should be in 0 to 999999999 range")
    @Max(value = 999999999, message = "number of electronic message (EDNo) should be in 0 to 999999999 range")
    @NotNull(message = "edNo should be not null")
    private Integer edNo;
    @NotNull(message = "edDate should be not null")
    private LocalDate edDate;
    @Min(value = 1000000000L, message = "EDAuthor should be in 1000000000 to 9999999999")
    @Max(value = 9999999999L, message = "EDAuthor should be in 1000000000 to 9999999999")
    @NotNull(message = "edAuthor should be not null")
    private Long edAuthor;
}
