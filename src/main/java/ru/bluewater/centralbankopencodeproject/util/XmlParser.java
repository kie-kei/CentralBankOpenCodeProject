package ru.bluewater.centralbankopencodeproject.util;

import jakarta.xml.bind.*;
import org.glassfish.jaxb.core.marshaller.CharacterEscapeHandler;
import org.glassfish.jaxb.core.marshaller.DumbEscapeHandler;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;

import java.io.*;

public class XmlParser {

    private static JAXBContext getContext() throws JAXBException {
        return JAXBContext.newInstance(RootEntity.class);
    }

    public static String toXml(RootEntity rootEntity) throws JAXBException {
        JAXBContext context = getContext();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);


        StringWriter writer = new StringWriter();
        marshaller.marshal(rootEntity, writer);
        String xmlString = writer.toString();

        return xmlString.replace("&quot;", "\"") ;
    }

    public static RootEntity fromXml(String xml) throws JAXBException {
        JAXBContext context = getContext();
        Unmarshaller unmarshaller = context.createUnmarshaller();


        StringReader reader = new StringReader(xml);
        return (RootEntity) unmarshaller.unmarshal(reader);
    }

    public static RootEntity fromXmlFile(InputStream inputStream) throws JAXBException {
        JAXBContext context = getContext();
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (RootEntity) unmarshaller.unmarshal(inputStream);
    }

    public static void toXmlFile(RootEntity rootEntity, File file) throws JAXBException {
        JAXBContext context = getContext();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(rootEntity, file);
    }

}
