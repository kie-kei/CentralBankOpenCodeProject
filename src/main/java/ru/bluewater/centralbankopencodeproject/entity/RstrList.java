package ru.bluewater.centralbankopencodeproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String rstr;
    @XmlAttribute(name = "RstrDate")
    @XmlSchemaType(name = "date")
    private Date rstrDate;
}
