package ru.bluewater.centralbankrestsrc.mapper;

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
import ru.bluewater.centralbankrestsrc.dto.xml.RstrList;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RstrListEntityMapper {
    RstrListEntity toRstrListEntity(RstrList rstrList);

    RstrList toRstrList(RstrListEntity rstrListEntity);
    RstrListResponseDTO toRstrListResponseDTO(RstrListEntity entity);

    @Mapping(target = "uuid", ignore = true)
    RstrListEntity toEntity(RstrListRequestDTO rstrListRequestDTO);

    RstrListEntity fromCreateRequestToEntity(RstrListCreateRequestDTO requestDTO);
    RstrListUpdateResponseDTO toUpdateResponse(RstrListEntity entity);
    RstrListCreateResponseDTO toCreateResponse(RstrListEntity entity);
    RstrListGetResponseDTO toGetResponse(RstrListEntity entity);
    List<RstrListGetResponseDTO> toList(List<RstrListEntity> rstrListEntities);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(RstrListUpdateRequestDTO requestDTO, @MappingTarget RstrListEntity entity);
}
