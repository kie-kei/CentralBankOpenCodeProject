package ru.bluewater.centralbankopencodeproject.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@XmlAccessorType(XmlAccessType.FIELD)
public class Accounts {
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
