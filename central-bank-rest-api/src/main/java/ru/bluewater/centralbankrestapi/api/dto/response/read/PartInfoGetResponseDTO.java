package ru.bluewater.centralbankrestapi.api.dto.response.read;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
public class PartInfoGetResponseDTO {
    private UUID uuid;
    private Integer partNo;
    private Integer partQuantity;
    private BigInteger partAggregateID;
}
