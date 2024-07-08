package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.RstrListRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.RstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.RstrListUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RstrListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.RstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.RstrListGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.RstrListUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;

@Mapper(componentModel = "spring")
public interface RstrListEntityMapper {
    RstrListResponseDTO toRstrListResponseDTO(RstrListEntity entity);

    @Mapping(target = "uuid", ignore = true)
    RstrListEntity toEntity(RstrListRequestDTO rstrListRequestDTO);

    RstrListEntity fromCreateRequestToEntity(RstrListCreateRequestDTO requestDTO);
    RstrListUpdateResponseDTO toUpdateResponse(RstrListEntity entity);
    RstrListCreateResponseDTO toCreateResponse(RstrListEntity entity);
    RstrListGetResponseDTO toGetResponse(RstrListEntity entity);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(RstrListUpdateRequestDTO requestDTO, @MappingTarget RstrListEntity entity);
}
