package ru.bluewater.centralbankopencodeproject.entity.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SWBICS {
    @XmlAttribute(name = "SWBIC")
    private String swbic;
    @XmlAttribute(name = "DefaultSWBIC")
    private Boolean defaultSWBIC;
}
