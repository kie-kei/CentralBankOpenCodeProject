package ru.bluewater.centralbankrestapi.api.dto.response.update;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
public class PartInfoUpdateResponseDTO {
    private UUID uuid;
    private Integer partNo;
    private Integer partQuantity;
    private BigDecimal partAggregateID;
}
