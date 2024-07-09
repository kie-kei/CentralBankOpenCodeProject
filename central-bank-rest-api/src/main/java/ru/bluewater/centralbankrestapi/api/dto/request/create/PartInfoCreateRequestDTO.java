package ru.bluewater.centralbankrestapi.api.dto.request.create;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
public class PartInfoCreateRequestDTO {
    private UUID ed807Uuid;
    private Integer partNo;
    private Integer partQuantity;
    private BigInteger partAggregateID;
}
