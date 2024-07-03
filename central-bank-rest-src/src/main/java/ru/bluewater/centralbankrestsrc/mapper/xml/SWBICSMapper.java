package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;
import ru.bluewater.centralbankrestsrc.entity.xml.SWBICS;

@Mapper(componentModel = "spring")
public interface SWBICSMapper {
    SWBICSEntity toSWBICSEntity(SWBICS swbics);

    SWBICS toSWBICS(SWBICSEntity swbicsEntity);
}
