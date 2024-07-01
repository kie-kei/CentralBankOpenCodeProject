package ru.bluewater.centralbankopencodeproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "acc_rstr_list")
public class AccRstrListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Size(min = 4, max = 4, message = "accRstr length should be 4")
    @NotNull(message = "accRstr should be not null")
    private String accRstr;

    @NotNull(message = "accRstrDate should be not null")
    private LocalDate accRstrDate;

    @Min(value = 100000000, message = "successorBIC range should be from 100000000 to 999999999")
    @Max(value = 999999999, message = "successorBIC range should be from 100000000 to 999999999")
    private Integer successorBIC;

    @ManyToOne
    @JoinColumn(name = "accounts_uuid")
    private AccountsEntity accounts;
}
