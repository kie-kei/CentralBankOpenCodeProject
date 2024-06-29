package ru.bluewater.centralbankopencodeproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class RstrList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @XmlAttribute(name = "Rstr")
    @Size(min = 4, max = 4, message = "Rstr length should be 4")
    private String rstr;
    @XmlAttribute(name = "RstrDate")
    @XmlSchemaType(name = "date")
    private Date rstrDate;
}
