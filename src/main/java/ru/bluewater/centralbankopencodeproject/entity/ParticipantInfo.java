package ru.bluewater.centralbankopencodeproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import org.springframework.cglib.core.Local;
import ru.bluewater.centralbankopencodeproject.util.adapter.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "bicDirectoryEntry")
@Entity(name = "participant_info")
public class ParticipantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @XmlTransient
    private UUID uuid;

    @XmlAttribute(name = "NameP")
    @Size(min = 1, max = 160, message = "NameP length should be from 1 to 160")
    @NotEmpty
    private String nameP;

    @XmlAttribute(name = "EnglName")
    @Size(min = 1, max = 140, message = "EnglName should be from 1 to 140")
    private String englName;

    @XmlAttribute(name = "RegN")
    @Size(min = 1, max = 9, message = "RegN length should be from 1 to 9")
    private String regN;

    @XmlAttribute(name = "CntrCd")
    @Size(min = 2, max = 2, message = "CntrCd length should be 2")
    private String cntrCd;

    @XmlAttribute(name = "Rgn")
    @Size(min = 1, max = 2, message = "Rgn length should be from 1 to 2")
    private String rgn;

    @XmlAttribute(name = "Ind")
    @Size(min = 1, max = 6, message = "Ind length should be from 1 to 6")
    private String ind;

    @XmlAttribute(name = "Tnp")
    @Size(min = 1, max = 5, message = "Tnp length should be from 1 to 5")
    private String tnp;

    @XmlAttribute(name = "Nnp")
    @Size(min = 1, max = 25, message = "Nnp length should be from 1 to 25")
    private String nnp;

    @XmlAttribute(name = "Adr")
    @Size(min = 1, max = 160, message = "adr length should be from 1 to 160")
    private String adr;

    @XmlAttribute(name = "DateIn")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull(message = "dateIn should be not null")
    private LocalDate dateIn;

    @XmlAttribute(name = "DateOut")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOut;

    @XmlAttribute(name = "PtType")
    @Size(min = 1, max = 2, message = "PtType length should be from 1 to 2")
    @NotEmpty(message = "PtType should be not empty")
    private String ptType;

    @XmlAttribute(name = "Srvcs")
    @Size(min = 1, max = 1, message = "Srvcs length should be 1")
    @NotEmpty(message = "Srvcs should be not empty")
    private String srvcs;

    @XmlAttribute(name = "XchType")
    @Size(min = 1, max = 1, message = "XchType length should be 1")
    @NotEmpty(message = "XchType should be not empty")
    private String xchType;

    @XmlAttribute(name = "UID")
    @JsonProperty("UID")
//    @Size(min = 10, max = 10, message = "UID length should be 10")
    @NotEmpty
    @Pattern(regexp = "[0-9]{10}", message = "uid should constraint only numbers")
    private String UID;

    @XmlAttribute(name = "ParticipantStatus")
    @Size(max = 4, message = "ParticipantStatus length should be 4")
    private String participantStatus;

    @XmlElement(name = "RstrList")
    @OneToOne(mappedBy = "participantInfo")
    private RstrList rstrList;

    @JsonIgnore
    @XmlTransient
    @OneToOne
    @JoinColumn(name = "participant_info_uuid")
    private BICDirectoryEntry bicDirectoryEntry;
}