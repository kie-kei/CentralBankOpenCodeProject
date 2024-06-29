package ru.bluewater.centralbankopencodeproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    @NotNull(message = "DateIn should be not null")
    private Date dateIn;

    @XmlAttribute(name = "DateOut")
    @XmlSchemaType(name = "date")
    private Date dateOut;

    @XmlAttribute(name = "AccountStatus")
    @Size(min = 4, max = 4, message = "AccountStatus length should be 4")
    private String accountStatus;

}
