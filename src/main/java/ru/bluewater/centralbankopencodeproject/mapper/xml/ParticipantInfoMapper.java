package ru.bluewater.centralbankopencodeproject.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.ParticipantInfo;

@Mapper(componentModel = "spring")
public interface ParticipantInfoMapper {
    ParticipantInfoEntity toParticipantInfoEntity(ParticipantInfo participantInfo);
    ParticipantInfo toParticipantInfo(ParticipantInfoEntity participantInfoEntity);
}
