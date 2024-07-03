package ru.bluewater.centralbankrestsrc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "rootEntity")
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "part_info")
public class PartInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

//    @Min(1)
//    @Max(999999)
    @Digits(integer = 6, fraction = 0, message = "partNo should be up to 6 digits")
    @NotNull(message = "partNo should be not null")
    private Integer partNo;

//    @Min(1)
//    @Max(999999)
    @Digits(integer = 6, fraction = 0, message = "partNo should be up to 6 digits")
    @NotNull(message = "partQuantity should be not null")
    private Integer partQuantity;

    @Digits(integer = 27, fraction = 0)
    @NotNull(message = "partAggregateID should be not null")
    @Column(name = "part_aggregate_id")
    private BigDecimal partAggregateID;

    @OneToOne
    @JoinColumn(name = "ed807_uuid")
    private RootEntity rootEntity;
}
