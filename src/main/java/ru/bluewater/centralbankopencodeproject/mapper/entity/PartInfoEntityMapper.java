package ru.bluewater.centralbankopencodeproject.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.response.PartInfoResponseDTO;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfoEntity;

@Mapper(componentModel = "spring")
public interface PartInfoEntityMapper {
    PartInfoResponseDTO toPartInfoResponseDTO(ParticipantInfoEntity participantInfoEntity);
}
