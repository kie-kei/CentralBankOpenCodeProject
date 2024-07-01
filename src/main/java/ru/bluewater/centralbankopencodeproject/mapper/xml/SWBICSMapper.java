package ru.bluewater.centralbankopencodeproject.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.entity.SWBICSEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.SWBICS;

@Mapper(componentModel = "spring")
public interface SWBICSMapper {
    SWBICSEntity toSWBICSEntity(SWBICS swbics);
    SWBICS toSWBICS(SWBICSEntity swbicsEntity);
}
