package ru.bluewater.centralbankopencodeproject.api.dto.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bluewater.centralbankopencodeproject.entity.AccountsEntity;

import java.time.LocalDate;
import java.util.UUID;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccRstrListResponseDTO {
    private UUID uuid;
    private String accRstr;
    private LocalDate accRstrDate;
    private Integer successorBIC;
}
