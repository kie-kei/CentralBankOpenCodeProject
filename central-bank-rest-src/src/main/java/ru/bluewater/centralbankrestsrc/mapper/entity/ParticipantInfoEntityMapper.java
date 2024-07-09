package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.ParticipantInfoRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.ParticipantInfoCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ParticipantInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ParticipantInfoResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.ParticipantInfoCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ParticipantInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ParticipantInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;

@Mapper(componentModel = "spring")
public interface ParticipantInfoEntityMapper {
    ParticipantInfoResponseDTO toParticipantInfoResponseDTO(ParticipantInfoEntity participantInfoEntity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "rstrList", ignore = true)
    void updateFromDTO(ParticipantInfoRequestDTO requestDTO, @MappingTarget ParticipantInfoEntity participantInfo);

    @Mapping(target = "uuid", ignore = true)
//    @Mapping(target = "rstrList", ignore = true)
    ParticipantInfoEntity toEntity(ParticipantInfoRequestDTO requestDTO);

    ParticipantInfoEntity fromCreateRequestToEntity(ParticipantInfoCreateRequestDTO requestDTO);

    ParticipantInfoCreateResponseDTO toCreateResponse(ParticipantInfoEntity participantInfoEntity);
    ParticipantInfoUpdateResponseDTO toUpdateResponse(ParticipantInfoEntity participantInfoEntity);
    ParticipantInfoGetResponseDTO toGetResponse(ParticipantInfoEntity participantInfoEntity);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(ParticipantInfoUpdateRequestDTO requestDTO, @MappingTarget ParticipantInfoEntity entity);

    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(ParticipantInfoRequestDTO requestDTO, @MappingTarget ParticipantInfoEntity entity);
}

