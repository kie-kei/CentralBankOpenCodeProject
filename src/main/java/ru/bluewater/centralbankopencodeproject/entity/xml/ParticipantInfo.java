package ru.bluewater.centralbankopencodeproject.entity.xml;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import ru.bluewater.centralbankopencodeproject.entity.RstrListEntity;
import ru.bluewater.centralbankopencodeproject.util.adapter.LocalDateAdapter;

import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ParticipantInfo {

    @XmlAttribute(name = "NameP")
    private String nameP;

    @XmlAttribute(name = "EnglName")
    private String englName;

    @XmlAttribute(name = "RegN")
    private String regN;

    @XmlAttribute(name = "CntrCd")
    private String cntrCd;

    @XmlAttribute(name = "Rgn")
    private String rgn;

    @XmlAttribute(name = "Ind")
    private String ind;

    @XmlAttribute(name = "Tnp")
    private String tnp;

    @XmlAttribute(name = "Nnp")
    private String nnp;

    @XmlAttribute(name = "Adr")
    private String adr;

    @XmlAttribute(name = "PrntBIC")
    private String prntBIC;

    @XmlAttribute(name = "DateIn")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateIn;

    @XmlAttribute(name = "DateOut")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOut;

    @XmlAttribute(name = "PtType")
    private String ptType;

    @XmlAttribute(name = "Srvcs")
    private String srvcs;

    @XmlAttribute(name = "XchType")
    private String xchType;

    @XmlAttribute(name = "UID")
    private String uid;

    @XmlAttribute(name = "ParticipantStatus")
    private String participantStatus;

    @XmlElement(name = "RstrList")
    private List<RstrList> rstrList;
}