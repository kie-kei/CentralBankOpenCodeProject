package ru.bluewater.centralbankopencodeproject.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import ru.bluewater.centralbankopencodeproject.util.adapter.LocalDateAdapter;
import ru.bluewater.centralbankopencodeproject.util.adapter.ZonedDateTimeAdapter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@XmlRootElement(name = "ED807", namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ed807")
public class RootEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @XmlTransient
    private UUID uuid;

    @XmlTransient
    private String fileName;

    @XmlElement(name = "BICDirectoryEntry")
    @OneToMany(mappedBy = "rootEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BICDirectoryEntry> bicDirectoryEntry;

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
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate EDDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD

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
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    @NotNull(message = "creationDateTime should be not null")
    private ZonedDateTime creationDateTime; // electronicMessageCreationDateTime format YYYY-MM-DDThh:mm:ssZ

    @XmlAttribute(name = "InfoTypeCode")
    @Column(nullable = false, length = 4)
    @Pattern(regexp = "[A-Za-z0-9]{4}", message = "infoTypeCode length should be 4")
    private String infoTypeCode; // typeOfInformationPresentationCode

    @XmlAttribute(name = "BusinessDay")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "businessDay should be not null")
    private LocalDate businessDay; // format YYYY-MM-DD

    @XmlAttribute(name = "DirectoryVersion")
    @Column
    @Min(value = 0, message = "directory BIC Version (DirectoryVersion) should be in range 0 to 99")
    @Max(value = 99, message = "directory BIC Version (DirectoryVersion) should be in range 0 to 99")
    private Integer directoryVersion; // directoryBICVersion, nullable since [0..1]

}
