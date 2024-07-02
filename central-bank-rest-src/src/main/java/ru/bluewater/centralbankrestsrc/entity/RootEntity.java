package ru.bluewater.centralbankrestsrc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ed807")
public class RootEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    private String fileName;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private String createdBy;

    private LocalDate updatedAt;

    private String updatedBy;

    @OneToMany(mappedBy = "rootEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BICDirectoryEntryEntity> bicDirectoryEntry;

    @OneToOne(mappedBy = "rootEntity")
    private PartInfoEntity partInfo;

    @OneToOne(mappedBy = "rootEntity")
    private InitialEDEntity initialED;

    @Column(nullable = false)
//    @Min(value = 1, message = "number of electronic message (EDNo) should be in 0 to 999999999 range")
//    @Max(value = 999999999, message = "number of electronic message (EDNo) should be in 0 to 999999999 range")
    @Digits(integer = 9, fraction = 0, message = "EDNo should have up to 9 digits")
    @NotNull(message = "EDNo should be not null")
    private Integer edNo; // numberOfElectronicMessage

    @Temporal(TemporalType.DATE)
    @NotNull(message = "EDDate should be not null")
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD

    @Column(unique = true)
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

}
