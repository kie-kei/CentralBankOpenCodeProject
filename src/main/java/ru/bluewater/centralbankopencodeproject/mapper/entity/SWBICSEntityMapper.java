package ru.bluewater.centralbankopencodeproject.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.response.SWBICSResponseDTO;
import ru.bluewater.centralbankopencodeproject.entity.SWBICSEntity;

@Mapper(componentModel = "spring")
public interface SWBICSEntityMapper {
    SWBICSResponseDTO toSWBICSResponseDTO(SWBICSEntity swbicsEntity);
}
