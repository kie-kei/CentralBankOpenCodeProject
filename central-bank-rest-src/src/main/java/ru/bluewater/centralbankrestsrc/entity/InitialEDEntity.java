package ru.bluewater.centralbankrestsrc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "ed807Entity")
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "initial_ed")
public class InitialEDEntity {
    @Id
    private UUID uuid;

    @Min(value = 1, message = "number of electronic message (EDNo) should be in 0 to 999999999 range")
    @Max(value = 999999999, message = "number of electronic message (EDNo) should be in 0 to 999999999 range")
    @NotNull(message = "edNo should be not null")
    private Integer edNo; // numberOfElectronicMessage

    @Temporal(TemporalType.DATE)
    @NotNull(message = "edDate should be not null")
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD

    @Column(unique = true)
    @Min(value = 1000000000L, message = "EDAuthor should be in 1000000000 to 9999999999")
    @Max(value = 9999999999L, message = "EDAuthor should be in 1000000000 to 9999999999")
    @NotNull(message = "edAuthor should be not null")
    private Long edAuthor; // electronicMessageAuthorId unique

    @OneToOne
    @MapsId
    @JoinColumn(name = "uuid")
    private ED807Entity ed807Entity;
}
