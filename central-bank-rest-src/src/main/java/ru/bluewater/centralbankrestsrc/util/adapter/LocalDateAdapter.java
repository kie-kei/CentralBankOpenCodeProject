package ru.bluewater.centralbankrestsrc.util.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        if (v == null || v.isEmpty()) {
            return null;
        }
        return LocalDate.parse(v, DATE_FORMATTER);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        if (v == null) {
            return null;
        }
        return v.format(DATE_FORMATTER);
    }
}

