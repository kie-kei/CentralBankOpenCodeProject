package ru.bluewater.centralbankopencodeproject.api.dto.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;

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