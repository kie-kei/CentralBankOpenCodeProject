package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
