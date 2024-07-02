package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.ParticipantInfoResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;

@Mapper(componentModel = "spring")
public interface ParticipantInfoEntityMapper {
    ParticipantInfoResponseDTO toParticipantInfoResponseDTO(ParticipantInfoEntity participantInfoEntity);
}
