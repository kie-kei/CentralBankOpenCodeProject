package ru.bluewater.centralbankrestapi.api.dto.response.create;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;
@Setter
@Getter
public class PartInfoCreateResponseDTO {
    private UUID uuid;
    private Integer partNo;
    private Integer partQuantity;
    private BigInteger partAggregateID;
}
