package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.create.PartInfoCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.PartInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.PartInfoResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.PartInfoCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.PartInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.PartInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.PartInfoEntity;

@Mapper(componentModel = "spring")
public interface PartInfoEntityMapper {
    PartInfoResponseDTO toPartInfoResponseDTO(PartInfoEntity partInfo);
    PartInfoGetResponseDTO toGetResponse(PartInfoEntity partInfo);
    PartInfoUpdateResponseDTO toUpdateResponse(PartInfoEntity partInfo);

    @Mapping(target = "uuid", ignore = true)
    PartInfoEntity fromCreateRequestToEntity(PartInfoCreateRequestDTO requestDTO);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(PartInfoUpdateRequestDTO requestDTO, @MappingTarget PartInfoEntity entity);
    PartInfoCreateResponseDTO toCreateResponse(PartInfoEntity partInfoEntity);
}
