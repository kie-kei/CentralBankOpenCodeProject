package ru.bluewater.centralbankrestsrc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.AccRstrListRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccRstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccRstrListUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.AccRstrListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccRstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccRstrListGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccRstrListUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;
import ru.bluewater.centralbankrestsrc.dto.xml.AccRstrList;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccRstrListEntityMapper {
    AccRstrListEntity toAccRstrListEntity(AccRstrList accRstrList);

    AccRstrList toAccRstrList(AccRstrListEntity accRstrListEntity);
    @Mapping(target = "uuid", ignore = true)
    AccRstrListEntity fromCreateRequestToEntity(AccRstrListCreateRequestDTO requestDTO);
    AccRstrListCreateResponseDTO toCreateResponse(AccRstrListEntity entity);
    AccRstrListUpdateResponseDTO toUpdateResponse(AccRstrListEntity entity);
    AccRstrListGetResponseDTO toGetResponse(AccRstrListEntity entity);
    List<AccRstrListGetResponseDTO> toListResponse(List<AccRstrListEntity> accRstrListEntityList);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(AccRstrListUpdateRequestDTO requestDTO, @MappingTarget AccRstrListEntity entity);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(AccRstrListRequestDTO requestDTO, @MappingTarget AccRstrListEntity entity);

    @Mapping(target = "uuid", ignore = true)
    AccRstrListEntity toEntity(AccRstrListRequestDTO requestDTO);

}
