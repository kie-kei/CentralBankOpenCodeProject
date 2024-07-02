package ru.bluewater.centralbankrestapi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartInfoResponseDTO {
    private UUID uuid;
    private Integer partNo;
    private Integer partQuantity;
    private BigDecimal partAggregateID;
}