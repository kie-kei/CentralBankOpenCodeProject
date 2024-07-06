package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.bluewater.centralbankrestapi.api.dto.request.SWBICSRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.SWBICSCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.SWBICSResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;

@Mapper(componentModel = "spring")
public interface SWBICSEntityMapper {
    SWBICSResponseDTO toSWBICSResponseDTO(SWBICSEntity swbicsEntity);

    @Mapping(target = "uuid", ignore = true)
    SWBICSEntity toEntity(SWBICSRequestDTO requestDTO);

    SWBICSEntity fromCreateRequestToEntity(SWBICSCreateRequestDTO requestDTO);
}
