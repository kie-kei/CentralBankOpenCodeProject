package ru.bluewater.centralbankrestapi.api.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;
@Getter
@Setter
public class PartInfoRequestDTO {
    private UUID uuid;
    @Digits(integer = 6, fraction = 0, message = "partNo should be up to 6 digits")
    @NotNull(message = "partNo should be not null")
    private Integer partNo;

    @Digits(integer = 6, fraction = 0, message = "partNo should be up to 6 digits")
    @NotNull(message = "partQuantity should be not null")
    private Integer partQuantity;

    @Digits(integer = 27, fraction = 0)
    @NotNull(message = "partAggregateID should be not null")
    private BigInteger partAggregateID;
}
