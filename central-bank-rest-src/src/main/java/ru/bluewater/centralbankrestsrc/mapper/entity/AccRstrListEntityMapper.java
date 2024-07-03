package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.bluewater.centralbankrestapi.api.dto.request.AccRstrListRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.AccRstrListResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;

@Mapper(componentModel = "spring")
public interface AccRstrListEntityMapper {
    AccRstrListResponseDTO toAccRstrListResponseDTO(AccRstrListEntity accRstrListEntity);

    @Mapping(target = "uuid", ignore = true)
    AccRstrListEntity toEntity(AccRstrListRequestDTO requestDTO);
}
