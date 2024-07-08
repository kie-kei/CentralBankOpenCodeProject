package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.SWBICSRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.SWBICSCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.SWBICSUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.SWBICSResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.SWBICSCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.SWBICSGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.SWBICSUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;

@Mapper(componentModel = "spring")
public interface SWBICSEntityMapper {
    SWBICSResponseDTO toSWBICSResponseDTO(SWBICSEntity swbicsEntity);

    SWBICSCreateResponseDTO toCreateResponse(SWBICSEntity entity);
    SWBICSUpdateResponseDTO toUpdateResponse(SWBICSEntity entity);
    SWBICSGetResponseDTO toGetResponse(SWBICSEntity entity);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(SWBICSUpdateRequestDTO requestDTO, @MappingTarget SWBICSEntity entity);
    @Mapping(target = "uuid", ignore = true)
    SWBICSEntity toEntity(SWBICSRequestDTO requestDTO);

    SWBICSEntity fromCreateRequestToEntity(SWBICSCreateRequestDTO requestDTO);
}
