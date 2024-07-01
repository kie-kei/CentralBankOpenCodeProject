package ru.bluewater.centralbankopencodeproject.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.response.InitialEDResponseDTO;
import ru.bluewater.centralbankopencodeproject.entity.InitialEDEntity;

@Mapper(componentModel = "spring")
public interface InitialEDEntityMapper {
    InitialEDResponseDTO toInitialEDResponseDTO(InitialEDEntity initialEDEntity);
}
