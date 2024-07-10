package ru.bluewater.centralbankrestapi.api.dto.request.create;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class PartInfoCreateRequestDTO {
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
