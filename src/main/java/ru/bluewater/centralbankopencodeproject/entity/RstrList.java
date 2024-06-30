package ru.bluewater.centralbankopencodeproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rstr_list")
public class RstrList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @XmlTransient
    private UUID uuid;
    @XmlAttribute(name = "Rstr")
    @Size(min = 4, max = 4, message = "Rstr length should be 4")
    private String rstr;
    @XmlAttribute(name = "RstrDate")
    @XmlSchemaType(name = "date")
    private Date rstrDate;
    @OneToOne
    @XmlTransient
    @JoinColumn(name = "participant_info_uuid")
    private ParticipantInfo participantInfo;
}
