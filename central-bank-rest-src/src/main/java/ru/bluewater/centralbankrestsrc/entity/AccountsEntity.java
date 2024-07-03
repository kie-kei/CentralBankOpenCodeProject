package ru.bluewater.centralbankrestsrc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "bicDirectoryEntry")
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "accounts")
public class AccountsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Size(min = 20, max = 20, message = "Account length should be 20")
    @NotNull(message = "account should be not null")
    private String account;

    @Size(min = 4, max = 4, message = "RegulationAccountType length should be 4")
    @NotNull(message = "regulationAccountType should be not null")
    private String regulationAccountType;

    @Size(min = 2, max = 2, message = "CK length should be 2")
    private String CK;

    @Size(min = 9, max = 9, message = "AccountCBRBIC length should be 9")
    @NotNull(message = "accountCBRBIC should be not null")
    @Column(name = "account_CBRBIC")
    private String accountCBRBIC;

    @NotNull(message = "DateIn should be not null")
    private LocalDate dateIn;

    private LocalDate dateOut;

    @Size(min = 4, max = 4, message = "AccountStatus length should be 4")
    private String accountStatus;

    @OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
    private List<AccRstrListEntity> accRstrList;

    @ManyToOne
    @JoinColumn(name = "bic_directory_entry_uuid")
    private BICDirectoryEntryEntity bicDirectoryEntry;

}
