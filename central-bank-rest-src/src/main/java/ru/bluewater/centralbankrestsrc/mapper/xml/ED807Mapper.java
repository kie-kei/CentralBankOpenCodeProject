package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.ED807ResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;
import ru.bluewater.centralbankrestsrc.dto.xml.ED807;

@Mapper(componentModel = "spring")
public interface ED807Mapper {
    ED807Entity toRootEntity(ED807 ed807);

    ED807 entityToED807(ED807Entity ED807Entity);

    ED807 dtoToED807(ED807ResponseDTO ED807ResponseDTO);
}
