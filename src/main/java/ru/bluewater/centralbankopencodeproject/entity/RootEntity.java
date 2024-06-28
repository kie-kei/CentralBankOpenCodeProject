package ru.bluewater.centralbankopencodeproject.entity;

import jakarta.persistence.*;
import javax.validation.constraints.*;
import jakarta.xml.bind.annotation.*;
import lombok.*;
import ru.bluewater.centralbankopencodeproject.util.ValidateRootEntityUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@XmlRootElement(name = "ED807")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
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

    @XmlAttribute(name = "EDNo")
    @Column(nullable = false)
    @Min(1)
    @Max(999999999)
    private int EDNo; // numberOfElectronicMessage

    @XmlAttribute(name = "EDDate")
    @XmlSchemaType(name = "date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date EDDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD

    @XmlAttribute(name = "EDAuthor")
    @Column(unique = true, nullable = false)
    @Digits(integer = 10, fraction = 0)
    @Size(min = 10, max = 10)
    private Long EDAuthor; // electronicMessageAuthorId unique

    @XmlAttribute(name = "CreationReason")
    @Column(nullable = false, length = 4)
    @Pattern(regexp = "[A-Za-z0-9]{4}")
    private String creationReason; // creationReasonCode

    @XmlAttribute(name = "CreationDateTime")
    @XmlSchemaType(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date creationDateTime; // electronicMessageCreationDateTime format YYYY-MM-DDThh:mm:ssZ

    @XmlAttribute(name = "InfoTypeCode")
    @Column(nullable = false, length = 4)
    @Pattern(regexp = "[A-Za-z0-9]{4}")
    private String infoTypeCode; // typeOfInformationPresentationCode

    @XmlAttribute(name = "BusinessDay")
    @XmlSchemaType(name = "date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date businessDay; // format YYYY-MM-DD

    @XmlAttribute(name = "DirectoryVersion")
    @Column
    @Min(0)
    @Max(99)
    private Integer directoryVersion; // directoryBICVersion, nullable since [0..1]

    @lombok.Builder(toBuilder = true)
    public RootEntity(String xmlns, int EDNo, Date EDDate, Long EDAuthor, String creationReason,
                      Date creationDateTime, String infoTypeCode, Date businessDay, Integer directoryVersion) {
        validate(EDAuthor, EDNo, creationReason, infoTypeCode, directoryVersion);
        this.xmlns = xmlns;
        this.EDNo = EDNo;
        this.EDDate = EDDate;
        this.EDAuthor = EDAuthor;
        this.creationReason = creationReason;
        this.creationDateTime = creationDateTime;
        this.infoTypeCode = infoTypeCode;
        this.businessDay = businessDay;
        this.directoryVersion = directoryVersion;
    }

    private void validate(Long EDAuthor, int EDNo, String creationReason, String infoTypeCode, int directoryVersion){
        if (!ValidateRootEntityUtil.isRequestCodeType(infoTypeCode))
            throw ValidateRootEntityUtil.infoTypeCodeException;

        if(!ValidateRootEntityUtil.isErIDType(EDAuthor))
            throw ValidateRootEntityUtil.EDAuthorException;

        if(!ValidateRootEntityUtil.isEDNumberType(EDNo))
            throw ValidateRootEntityUtil.EDNoException;

        if(!ValidateRootEntityUtil.isReasonCodeType(creationReason))
            throw ValidateRootEntityUtil.creationReasonException;

        if(!ValidateRootEntityUtil.isMax2NumberType(directoryVersion))
            throw ValidateRootEntityUtil.directoryVersionException;
    }

    public void setEDAuthor(Long EDAuthor){
        if(ValidateRootEntityUtil.isErIDType(EDAuthor))
            this.EDAuthor = EDAuthor;
        else throw ValidateRootEntityUtil.EDAuthorException;
    }

    public void setEDNo(int EDNo) {
        if(ValidateRootEntityUtil.isEDNumberType(EDNo))
            this.EDNo = EDNo;
        else throw ValidateRootEntityUtil.EDNoException;
    }
    public void setCreationReason(String creationReason){
        if (ValidateRootEntityUtil.isReasonCodeType(creationReason))
            this.creationReason = creationReason;
        else throw ValidateRootEntityUtil.creationReasonException;
    }
    public void setInfoTypeCode(String infoTypeCode){
        if (ValidateRootEntityUtil.isRequestCodeType(infoTypeCode))
            this.infoTypeCode = infoTypeCode;
        else throw ValidateRootEntityUtil.infoTypeCodeException;
    }
    public void setDirectoryVersion(int directoryVersion){
        if(ValidateRootEntityUtil.isMax2NumberType(directoryVersion))
            this.directoryVersion = directoryVersion;
        else throw ValidateRootEntityUtil.directoryVersionException;
    }


}
