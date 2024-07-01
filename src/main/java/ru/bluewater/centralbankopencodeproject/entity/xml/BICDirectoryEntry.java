package ru.bluewater.centralbankopencodeproject.entity.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class BICDirectoryEntry {

    @XmlAttribute(name = "BIC")
    private String BIC;

    @XmlAttribute(name = "ChangeType")
    private String changeType;

    @XmlElement(name = "ParticipantInfo")
    private ParticipantInfo participantInfo;

    @XmlElement(name = "Accounts")
    private List<Accounts> accounts;

    @XmlElement(name = "SWBICS")
    private List<SWBICS> swbics;

}

