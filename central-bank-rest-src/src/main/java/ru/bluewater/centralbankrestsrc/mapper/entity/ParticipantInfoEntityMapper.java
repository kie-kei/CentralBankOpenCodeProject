package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.ParticipantInfoRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ParticipantInfoResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;

@Mapper(componentModel = "spring")
public interface ParticipantInfoEntityMapper {
    ParticipantInfoResponseDTO toParticipantInfoResponseDTO(ParticipantInfoEntity participantInfoEntity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "rstrList", ignore = true)
    void updateFromDTO(ParticipantInfoRequestDTO requestDTO, @MappingTarget ParticipantInfoEntity participantInfo);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "rstrList", ignore = true)
    ParticipantInfoEntity toEntity(ParticipantInfoRequestDTO requestDTO);
}

