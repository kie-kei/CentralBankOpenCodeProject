package ru.bluewater.centralbankopencodeproject.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @JsonProperty("BIC")
    @Min(value = 100000000)
    @Max(value = 999999999)
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
