package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.RootResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.RootEntity;

@Mapper(componentModel = "spring")
public interface RootEntityMapper {
    RootResponseDTO toRootResponseDTO(RootEntity rootEntity);
}
