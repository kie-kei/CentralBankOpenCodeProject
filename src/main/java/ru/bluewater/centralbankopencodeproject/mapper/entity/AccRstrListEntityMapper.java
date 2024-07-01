package ru.bluewater.centralbankopencodeproject.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.response.AccRstrListResponseDTO;
import ru.bluewater.centralbankopencodeproject.entity.AccRstrListEntity;

@Mapper(componentModel = "spring")
public interface AccRstrListEntityMapper {
    AccRstrListResponseDTO toAccRstrListResponseDTO(AccRstrListEntity accRstrListEntity);
}
