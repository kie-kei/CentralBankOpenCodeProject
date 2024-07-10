package ru.bluewater.centralbankrestsrc.dto.xml;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import ru.bluewater.centralbankrestsrc.util.adapter.LocalDateAdapter;
import ru.bluewater.centralbankrestsrc.util.adapter.ZonedDateTimeAdapter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@XmlRootElement(name = "ED807", namespace = "urn:cbr-ru:ed:v2.0")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ED807 {
    @XmlElement(name = "BICDirectoryEntry")
    private List<BICDirectoryEntry> bicDirectoryEntry;

    @XmlElement(name = "PartInfo")
    private PartInfo partInfo;

    @XmlElement(name = "InitialED")
    private InitialED initialED;

    @XmlAttribute(name = "EDNo")
    private Integer edNo; // numberOfElectronicMessage

    @XmlAttribute(name = "EDDate")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate edDate; // dateOfCompilationElectronicMessage format YYYY-MM-DD

    @XmlAttribute(name = "EDAuthor")
    private Long edAuthor; // electronicMessageAuthorId unique

    @XmlAttribute(name = "EDReceiver")
    private Long edReceiver;

    @XmlAttribute(name = "CreationReason")
    private String creationReason; // creationReasonCode

    @XmlAttribute(name = "CreationDateTime")
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime creationDateTime; // electronicMessageCreationDateTime format YYYY-MM-DDThh:mm:ssZ

    @XmlAttribute(name = "InfoTypeCode")
    private String infoTypeCode; // typeOfInformationPresentationCode

    @XmlAttribute(name = "BusinessDay")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate businessDay; // format YYYY-MM-DD

    @XmlAttribute(name = "DirectoryVersion")
    private Integer directoryVersion; // directoryBICVersion, nullable since [0..1]


}