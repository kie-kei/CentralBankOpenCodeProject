package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.RstrListResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;

@Mapper(componentModel = "spring")
public interface RstrListEntityMapper {
    RstrListResponseDTO toRstrListResponseDTO(RstrListEntity rstrList);
}
