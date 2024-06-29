package ru.bluewater.centralbankopencodeproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@XmlRootElement(name = "ED807")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RootEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @XmlAttribute(name = "BicDirectoryEntry")
    @OneToMany
    @JoinColumn(name = "bic_directory_entry_uuid")
    private List<BICDirectoryEntry> bicDirectoryEntry;

    @XmlAttribute(name = "xmlns")
    private String xmlns;

    @JsonProperty("EDNo")
    @XmlAttribute(name = "EDNo")
    @Column(nullable = false)
    @Min(value = 1, message = "number of electronic message (EDNo) should be in 0 to 999999999 range")
    @Max(value = 999999999, message = "number of electronic message (EDNo) should be in 0 to 999999999 range")
    private Integer EDNo; // numberOfElectronicMessage

    @JsonProperty("EDDate")
    @XmlAttribute(name = "EDDate")
    @XmlSchemaType(name = "date")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "EDDate should be not null")
    private Date EDDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD

    @JsonProperty("EDAuthor")
    @XmlAttribute(name = "EDAuthor")
    @Column(unique = true, nullable = false)
    @Min(value = 1000000000L, message = "EDAuthor should be in 999999999 to 9999999999")
    @Max(value = 9999999999L, message = "EDAuthor should be in 999999999 to 9999999999")
    private Long EDAuthor; // electronicMessageAuthorId unique

    @XmlAttribute(name = "CreationReason")
    @Column(nullable = false, length = 4)
    @Pattern(regexp = "[A-Za-z0-9]{4}", message = "creationReason length should be 4")
    private String creationReason; // creationReasonCode

    @XmlAttribute(name = "CreationDateTime")
    @XmlSchemaType(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "creationDateTime should be not null")
    private Date creationDateTime; // electronicMessageCreationDateTime format YYYY-MM-DDThh:mm:ssZ

    @XmlAttribute(name = "InfoTypeCode")
    @Column(nullable = false, length = 4)
    @Pattern(regexp = "[A-Za-z0-9]{4}", message = "infoTypeCode length should be 4")
    private String infoTypeCode; // typeOfInformationPresentationCode

    @XmlAttribute(name = "BusinessDay")
    @XmlSchemaType(name = "date")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "businessDay should be not null")
    private Date businessDay; // format YYYY-MM-DD

    @XmlAttribute(name = "DirectoryVersion")
    @Column
    @Min(value = 0, message = "directory BIC Version (DirectoryVersion) should be in range 0 to 99")
    @Max(value = 99, message = "directory BIC Version (DirectoryVersion) should be in range 0 to 99")
    private Integer directoryVersion; // directoryBICVersion, nullable since [0..1]

}
