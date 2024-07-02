package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.AccRstrListResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;

@Mapper(componentModel = "spring")
public interface AccRstrListEntityMapper {
    AccRstrListResponseDTO toAccRstrListResponseDTO(AccRstrListEntity accRstrListEntity);
}
