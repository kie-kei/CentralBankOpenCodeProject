package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.SWBICSResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;

@Mapper(componentModel = "spring")
public interface SWBICSEntityMapper {
    SWBICSResponseDTO toSWBICSResponseDTO(SWBICSEntity swbicsEntity);
}
