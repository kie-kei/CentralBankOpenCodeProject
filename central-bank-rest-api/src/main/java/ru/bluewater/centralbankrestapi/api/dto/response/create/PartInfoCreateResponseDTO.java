package ru.bluewater.centralbankrestapi.api.dto.response.create;

import java.math.BigDecimal;
import java.util.UUID;

public class PartInfoCreateResponseDTO {
    private UUID uuid;
    private Integer partNo;
    private Integer partQuantity;
    private BigDecimal partAggregateID;
}
