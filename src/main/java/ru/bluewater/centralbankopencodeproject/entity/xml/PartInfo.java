package ru.bluewater.centralbankopencodeproject.entity.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;


@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PartInfo {
    @XmlAttribute(name = "PartNo")
    private Integer partNo;

    @XmlAttribute(name = "PartQuantity")
    private Integer partQuantity;

    @XmlAttribute(name = "PartAggregateID")
    private Long partAggregateID;
}
