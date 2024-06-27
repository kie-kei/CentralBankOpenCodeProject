package ru.bluewater.centralbankopencodeproject.entity;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.List;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
public class BICDirectoryEntry {
    @XmlAttribute(name = "BIC")
    private String BIC;
    @XmlAttribute(name = "ParticipantInfo")
    private ParticipantInfo participantInfo;
    @XmlAttribute(name = "Accounts")
    private List<Accounts> accounts;
}
