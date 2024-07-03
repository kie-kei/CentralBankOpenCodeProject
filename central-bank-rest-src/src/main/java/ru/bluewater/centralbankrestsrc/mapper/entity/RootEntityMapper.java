package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.RootRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.RootUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RootResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.RootUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.RootEntity;

@Mapper(componentModel = "spring")
public interface RootEntityMapper {
    RootResponseDTO toRootResponseDTO(RootEntity rootEntity);
    RootEntity toRootEntity(RootRequestDTO requestDTO);
    @Mapping(target = "uuid", ignore = true)
    void updateFromDto(RootUpdateRequestDTO updateRequestDTO, @MappingTarget RootEntity rootEntity);

    RootUpdateResponseDTO toRootUpdateResponseDTO(RootEntity rootEntity);
}
