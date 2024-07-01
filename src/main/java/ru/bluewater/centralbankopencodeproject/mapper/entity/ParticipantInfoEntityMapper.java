package ru.bluewater.centralbankopencodeproject.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.response.ParticipantInfoResponseDTO;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfoEntity;

@Mapper(componentModel = "spring")
public interface ParticipantInfoEntityMapper {
    ParticipantInfoResponseDTO toParticipantInfoResponseDTO(ParticipantInfoEntity participantInfoEntity);
}
