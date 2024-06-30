package ru.bluewater.centralbankopencodeproject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "bic_directory_entry")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"rootEntity"})
@XmlAccessorType(XmlAccessType.FIELD)
public class BICDirectoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @XmlTransient
    private UUID uuid;

    @XmlAttribute(name = "BIC")
    @JsonProperty("BIC")
    @Size(min = 9, max = 9, message = "BIC length should be 9")
    private String BIC;

    @XmlElement(name = "ParticipantInfo")
    @OneToOne(mappedBy = "bicDirectoryEntry", cascade = CascadeType.ALL)
    private ParticipantInfo participantInfo;

    @OneToMany(mappedBy = "bicDirectoryEntry", cascade = CascadeType.ALL)
    @XmlElement(name = "Accounts")
    private List<Accounts> accounts;

    @JsonIgnore
    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "root_entity_uuid", referencedColumnName = "uuid")
    private RootEntity rootEntity;
}
