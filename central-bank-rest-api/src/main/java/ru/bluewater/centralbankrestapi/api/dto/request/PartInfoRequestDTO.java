package ru.bluewater.centralbankrestapi.api.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
public class PartInfoRequestDTO {
    private UUID uuid;
    private Integer partNo;
    private Integer partQuantity;
    private BigDecimal partAggregateID;
}
