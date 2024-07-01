package ru.bluewater.centralbankopencodeproject.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.response.RootResponseDTO;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;

@Mapper(componentModel = "spring")
public interface RootEntityMapper {
    RootResponseDTO toRootResponseDTO(RootEntity rootEntity);
}
