package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.bluewater.centralbankrestapi.api.dto.request.RstrListRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.RstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RstrListResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;

@Mapper(componentModel = "spring")
public interface RstrListEntityMapper {
    RstrListResponseDTO toRstrListResponseDTO(RstrListEntity rstrList);

    @Mapping(target = "uuid", ignore = true)
    RstrListEntity toEntity(RstrListRequestDTO rstrListRequestDTO);

    RstrListEntity fromCreateRequestToEntity(RstrListCreateRequestDTO requestDTO);
}
