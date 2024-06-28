package ru.bluewater.centralbankopencodeproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @XmlAttribute(name = "Account")
    private Long account;
    @XmlAttribute(name = "RegulationAccountType")
    private String regulationAccountType;
    @XmlAttribute(name = "CK")
    private int CK;
    @XmlAttribute(name = "AccountCBRBIC")
    private String accountCBRBIC;
    @XmlAttribute(name = "DateIn")
    @XmlSchemaType(name = "date")
    private Date dateIn;
    @XmlAttribute(name = "AccountStatus")
    private String accountStatus;

}
