package ru.bluewater.centralbankopencodeproject.entity.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import ru.bluewater.centralbankopencodeproject.util.adapter.LocalDateAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AccRstrList {
    @XmlAttribute(name = "AccRstr")
    private String accRstr;

    @XmlAttribute(name = "AccRstrDate")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate accRstrDate;

    @XmlAttribute(name = "SuccessorBIC")
    private Integer successorBIC;
}
