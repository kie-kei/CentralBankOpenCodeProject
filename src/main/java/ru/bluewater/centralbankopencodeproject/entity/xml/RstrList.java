package ru.bluewater.centralbankopencodeproject.entity.xml;


import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import ru.bluewater.centralbankopencodeproject.util.adapter.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Date;


@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class RstrList {
    @XmlAttribute(name = "Rstr")
    private String rstr;

    @XmlAttribute(name = "RstrDate")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate rstrDate;
}
