package ru.bluewater.centralbankrestsrc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.ED807RequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.ED807CreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ED807UpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ED807ResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ED807GetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ED807UpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.dto.xml.ED807;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ED807EntityMapper {
    ED807Entity toRootEntity(ED807 ed807);

    ED807 entityToED807(ED807Entity ED807Entity);

    ED807 dtoToED807(ED807ResponseDTO ED807ResponseDTO);
    ED807ResponseDTO toRootResponseDTO(ED807Entity ed807Entity);

    ED807Entity toRootEntity(ED807RequestDTO requestDTO);

    @Mapping(target = "uuid", ignore = true)
    void updateFromDto(ED807UpdateRequestDTO updateRequestDTO, @MappingTarget ED807Entity ed807Entity);

    ED807UpdateResponseDTO toRootUpdateResponseDTO(ED807Entity ed807Entity);

    List<ED807GetResponseDTO> toListRootGetResponseDTO(List<ED807Entity> ed807EntityList);

    ED807Entity fromCreateRequestToEntity(ED807CreateRequestDTO requestDTO);

}
