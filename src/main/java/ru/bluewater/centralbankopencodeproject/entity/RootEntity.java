package ru.bluewater.centralbankopencodeproject.entity;

import jakarta.xml.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@XmlRootElement(name = "ED807")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootEntity {
    @XmlAttribute(name = "BicDirectoryEntry")
    private BICDirectoryEntry bicDirectoryEntry;
    @XmlAttribute(name = "xmlns")
    private String xmlns;
    @XmlAttribute(name = "EDNo")
    private Long eDNo;
    @XmlAttribute(name = "EDDate")
    @XmlSchemaType(name = "date")
    private Date eDDate;
    @XmlAttribute(name = "EDAuthor")
    private Long EDAuthor;
    @XmlAttribute(name = "CreationReason")
    private String creationReason;
    @XmlAttribute(name = "CreationDateTime")
    @XmlSchemaType(name = "dateTime")
    private Date creationDateTime;
    @XmlAttribute(name = "InfoTypeCode")
    private String infoTypeCode;
    @XmlAttribute(name = "BusinessDay")
    @XmlSchemaType(name = "date")
    private Date businessDay;
    @XmlAttribute(name = "DirectoryVersion")
    private int directoryVersion;

}
