package ru.bluewater.centralbankrestsrc.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import ru.bluewater.centralbankrestsrc.util.adapter.LocalDateAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class InitialED {
    @XmlAttribute(name = "EDNo")
    private Integer edNo;

    @XmlAttribute(name = "EDDate")
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate edDate;

    @XmlAttribute(name = "EDAuthor")
    private Long edAuthor; // electronicMessageAuthorId unique
}
