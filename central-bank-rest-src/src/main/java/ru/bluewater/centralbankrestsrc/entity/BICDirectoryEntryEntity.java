package ru.bluewater.centralbankrestsrc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "bic_directory_entry")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bic_directory_entry_uuid")
    private ParticipantInfoEntity participantInfo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bic_directory_entry_uuid")
    private List<AccountsEntity> accounts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bic_directory_entry_uuid")
    private List<SWBICSEntity> swbics;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "ed807_uuid")
//    private ED807Entity ed807Entity;

}
