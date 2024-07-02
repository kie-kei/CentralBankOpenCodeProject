package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.InitialEDResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.InitialEDEntity;

@Mapper(componentModel = "spring")
public interface InitialEDEntityMapper {
    InitialEDResponseDTO toInitialEDResponseDTO(InitialEDEntity initialEDEntity);
}
