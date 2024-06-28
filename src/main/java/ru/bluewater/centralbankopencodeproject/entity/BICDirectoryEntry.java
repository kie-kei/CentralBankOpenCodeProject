package ru.bluewater.centralbankopencodeproject.entity;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.List;
import java.util.UUID;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class BICDirectoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @XmlAttribute(name = "BIC")
    private String BIC;
    @XmlAttribute(name = "ParticipantInfo")
    @OneToOne
    @JoinColumn(name = "participant_info_uuid")
    private ParticipantInfo participantInfo;
    @OneToMany
    @JoinColumn(name = "accounts_uuid")
    @XmlAttribute(name = "Accounts")
    private List<Accounts> accounts;
}
