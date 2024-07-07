package ru.bluewater.centralbankrestsrc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "bicDirectoryEntry")
@Entity(name = "participant_info")
public class ParticipantInfoEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Size(min = 1, max = 160, message = "NameP length should be from 1 to 160")
    @NotNull(message = "nameP should be not null")
    @Column(name = "name_p")
    private String nameP;

    @Size(min = 1, max = 140, message = "EnglName should be from 1 to 140")
    private String englName;

    @Size(min = 1, max = 9, message = "RegN length should be from 1 to 9")
    @Column(name = "reg_n")
    private String regN;

    @Size(min = 2, max = 2, message = "CntrCd length should be 2")
    private String cntrCd;

    @Size(min = 1, max = 2, message = "Rgn length should be from 1 to 2")
    @NotNull(message = "rgn should be not null")
    private String rgn;

    @Size(min = 1, max = 6, message = "Ind length should be from 1 to 6")
    private String ind;

    @Size(min = 1, max = 5, message = "Tnp length should be from 1 to 5")
    private String tnp;

    @Size(min = 1, max = 25, message = "Nnp length should be from 1 to 25")
    private String nnp;

    @Size(min = 1, max = 160, message = "adr length should be from 1 to 160")
    private String adr;

    @Size(min = 9, max = 9, message = "prntBIC length should be 9")
    @Column(name = "prnt_bic")
    private String prntBIC;

    @NotNull(message = "dateIn should be not null")
    private LocalDate dateIn;

    private LocalDate dateOut;

    @Size(min = 1, max = 2, message = "PtType length should be from 1 to 2")
    @NotNull(message = "ptType should be not null")
    private String ptType;

    @Size(min = 1, max = 1, message = "Srvcs length should be 1")
    @NotNull(message = "srvcs should be not null")
    private String srvcs;

    @Size(min = 1, max = 1, message = "XchType length should be 1")
    @NotNull(message = "xchType should be not null")
    private String xchType;

    @NotNull(message = "uid should be not null")
    @Pattern(regexp = "[0-9]{10}", message = "uid should constraint only numbers or length should be 10")
    private String uid;

    @Size(max = 4, message = "ParticipantStatus length should be 4")
    private String participantStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_info_uuid")
    private List<RstrListEntity> rstrList;

    @OneToOne
    @MapsId
    @JoinColumn(name = "uuid")
//    @JoinColumn(name = "bic_directory_entry_uuid")
    private BICDirectoryEntryEntity bicDirectoryEntry;
}