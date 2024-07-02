package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.PartInfoResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;

@Mapper(componentModel = "spring")
public interface PartInfoEntityMapper {
    PartInfoResponseDTO toPartInfoResponseDTO(ParticipantInfoEntity participantInfoEntity);
}
