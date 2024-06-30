package ru.bluewater.centralbankopencodeproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import ru.bluewater.centralbankopencodeproject.util.adapter.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "bicDirectoryEntry")
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @XmlTransient
    private UUID uuid;

    @XmlAttribute(name = "Account")
    @Size(min = 20, max = 20, message = "Account length should be 20")
    @NotEmpty
    private String account;

    @XmlAttribute(name = "RegulationAccountType")
    @Size(min = 4, max = 4, message = "RegulationAccountType length should be 4")
    @NotEmpty
    private String regulationAccountType;

    @XmlAttribute(name = "CK")
    @JsonProperty("CK")
    @Size(min = 2, max = 2, message = "CK length should be 2")
    private String CK;

    @XmlAttribute(name = "AccountCBRBIC")
    @Size(min = 9, max = 9, message = "AccountCBRBIC length should be 9")
    @NotEmpty(message = "AccountCBRBIC should be not empty")
    private String accountCBRBIC;

    @XmlAttribute(name = "DateIn")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull(message = "DateIn should be not null")
    private LocalDate dateIn;

    @XmlAttribute(name = "DateOut")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOut;

    @XmlAttribute(name = "AccountStatus")
    @Size(min = 4, max = 4, message = "AccountStatus length should be 4")
    private String accountStatus;

    @XmlTransient
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bic_directory_entry_uuid")
    private BICDirectoryEntry bicDirectoryEntry;

}
