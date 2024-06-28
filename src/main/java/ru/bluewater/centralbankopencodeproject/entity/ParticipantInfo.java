package ru.bluewater.centralbankopencodeproject.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class ParticipantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @XmlAttribute(name = "NameP")
    private String nameP;
    @XmlAttribute(name = "CntrCd")
    private String cntrCd;
    @XmlAttribute(name = "Rgn")
    private int rgn;
    @XmlAttribute(name = "Ind")
    private int ind;
    @XmlAttribute(name = "Tnp")
    private String tnp;
    @XmlAttribute(name = "Nnp")
    private String nnp;
    @XmlAttribute(name = "Adr")
    private String adr;
    @XmlAttribute(name = "DateIn")
    @XmlSchemaType(name = "date")
    private Date dateIn;
    @XmlAttribute(name = "PtType")
    private int ptType;
    @XmlAttribute(name = "Srvcs")
    private int srvcs;
    @XmlAttribute(name = "XchType")
    private int xchType; // ХАЧ ТАЙП ()
    @XmlAttribute(name = "UID")
    private int UID;
    @XmlAttribute(name = "ParticipantStatus")
    private String participantStatus;
    @XmlAttribute(name = "RstrList")
    @OneToOne
    @JoinColumn(name = "rstr_list_uuid")
    private RstrList rstrList;

}
