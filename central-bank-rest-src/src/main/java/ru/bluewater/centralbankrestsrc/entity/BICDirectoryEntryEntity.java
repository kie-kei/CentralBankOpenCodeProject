package ru.bluewater.centralbankrestsrc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bic_directory_entry")
public class BICDirectoryEntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Size(min = 9, max = 9, message = "BIC length should be 9")
    @NotNull(message = "BIC should be not null")
    private String BIC;

    @Size(min = 4, max = 4, message = "changeType length should be 4")
    private String changeType;

    @NotNull(message = "participantInfo should be not null")
    @OneToOne(mappedBy = "bicDirectoryEntry",cascade = CascadeType.ALL, optional = false)
    private ParticipantInfoEntity participantInfo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bic_directory_entry_uuid")
    private List<AccountsEntity> accounts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bic_directory_entry_uuid")
    private List<SWBICSEntity> swbics;

}
