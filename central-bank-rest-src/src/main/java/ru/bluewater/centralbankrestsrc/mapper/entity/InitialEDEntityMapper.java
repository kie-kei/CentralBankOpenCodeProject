package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.create.InitialEDCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.InitialEDUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.InitialEDResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.InitialEDCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.InitialEDGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.InitialEDUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.InitialEDEntity;

@Mapper(componentModel = "spring")
public interface InitialEDEntityMapper {
    InitialEDResponseDTO toResponse(InitialEDEntity initialEDEntity);
    @Mapping(target = "uuid", ignore = true)
    InitialEDEntity fromCreateRequestToEntity(InitialEDCreateRequestDTO requestDTO);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(InitialEDUpdateRequestDTO requestDTO, @MappingTarget InitialEDEntity entity);
    InitialEDCreateResponseDTO toCreateResponse(InitialEDEntity entity);
    InitialEDGetResponseDTO toGetResponse(InitialEDEntity entity);
    InitialEDUpdateResponseDTO toUpdateResponse(InitialEDEntity entity);

//    @Mapping(target = "uuid", ignore = true)
//    InitialEDEntity fromCreateRequestToEntity(InitialEDCreateRequestDTO requestDTO);
}
