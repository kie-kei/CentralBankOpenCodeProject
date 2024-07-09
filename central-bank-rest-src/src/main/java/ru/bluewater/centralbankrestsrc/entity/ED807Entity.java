package ru.bluewater.centralbankrestsrc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ed807")
public class ED807Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "ed807_uuid")
    private UUID uuid;

    @NotNull
    private String fileName;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private String createdBy;


    @Column(nullable = false)
    @Digits(integer = 9, fraction = 0, message = "EDNo should have up to 9 digits")
    @NotNull(message = "EDNo should be not null")
    private Integer edNo; // numberOfElectronicMessage

    @Temporal(TemporalType.DATE)
    @NotNull(message = "EDDate should be not null")
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD

    @NotNull(message = "EDAuthor should be not null")
    @Min(value = 1000000000L, message = "EDAuthor should be in 1000000000 to 9999999999")
    @Max(value = 9999999999L, message = "EDAuthor should be in 1000000000 to 9999999999")
    private Long edAuthor; // electronicMessageAuthorId unique

    @Min(value = 1000000000L, message = "EDAuthor should be in 1000000000 to 9999999999")
    @Max(value = 9999999999L, message = "EDAuthor should be in 1000000000 to 9999999999")
    private Long edReceiver;

    @NotNull(message = "creationReason sholud be not null")
    @Pattern(regexp = "[A-Za-z0-9]{4}", message = "creationReason length should be 4")
    private String creationReason; // creationReasonCode

    @NotNull(message = "creationDateTime should be not null")
    private ZonedDateTime creationDateTime; // electronicMessageCreationDateTime format YYYY-MM-DDThh:mm:ssZ

    @NotNull(message = "infoTypeCode should be not null")
    @Pattern(regexp = "[A-Za-z0-9]{4}", message = "infoTypeCode length should be 4")
    private String infoTypeCode; // typeOfInformationPresentationCode

    @Temporal(TemporalType.DATE)
    @NotNull(message = "businessDay should be not null")
    private LocalDate businessDay; // format YYYY-MM-DD

    @Column
    @Min(value = 0, message = "directory BIC Version (DirectoryVersion) should be in range 0 to 99")
    @Max(value = 99, message = "directory BIC Version (DirectoryVersion) should be in range 0 to 99")
    private Integer directoryVersion; // directoryBICVersion, nullable since [0..1]

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ed807_uuid")
    private List<BICDirectoryEntryEntity> bicDirectoryEntry;

//    @OneToOne(mappedBy = "ed807Entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private PartInfoEntity partInfo;
//
//    @OneToOne(mappedBy = "ed807Entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private InitialEDEntity initialED;
}
