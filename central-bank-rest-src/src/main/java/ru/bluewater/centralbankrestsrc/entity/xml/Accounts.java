package ru.bluewater.centralbankrestsrc.entity.xml;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import ru.bluewater.centralbankrestsrc.util.adapter.LocalDateAdapter;

import java.time.LocalDate;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Accounts {
    @XmlAttribute(name = "Account")
    private String account;

    @XmlAttribute(name = "RegulationAccountType")
    private String regulationAccountType;

    @XmlAttribute(name = "CK")
    private String CK;

    @XmlAttribute(name = "AccountCBRBIC")
    private String accountCBRBIC;

    @XmlAttribute(name = "DateIn")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateIn;

    @XmlAttribute(name = "DateOut")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOut;

    @XmlAttribute(name = "AccountStatus")
    private String accountStatus;

    @XmlElement(name = "AccRstrList")
    private List<AccRstrList> accRstrList;

}
