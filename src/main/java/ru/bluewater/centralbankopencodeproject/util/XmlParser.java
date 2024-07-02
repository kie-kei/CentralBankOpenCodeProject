package ru.bluewater.centralbankopencodeproject.util;

import jakarta.xml.bind.*;
import ru.bluewater.centralbankopencodeproject.entity.xml.ED807;

import java.io.*;

public class XmlParser {

    private static JAXBContext getContext() throws JAXBException {
            return JAXBContext.newInstance(ED807.class);
    }

    public static String toXml(ED807 ed807) throws JAXBException {
        JAXBContext context = getContext();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);


        StringWriter writer = new StringWriter();
        marshaller.marshal(ed807, writer);

        return writer.toString();
    }

    public static ED807 fromXmlFile(InputStream inputStream) throws JAXBException {
        JAXBContext context = getContext();
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (ED807) unmarshaller.unmarshal(inputStream);
    }
}
