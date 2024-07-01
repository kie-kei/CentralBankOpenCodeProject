package ru.bluewater.centralbankopencodeproject.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.response.RstrListResponseDTO;
import ru.bluewater.centralbankopencodeproject.entity.RstrListEntity;

@Mapper(componentModel = "spring")
public interface RstrListEntityMapper {
    RstrListResponseDTO toRstrListResponseDTO(RstrListEntity rstrList);
}
