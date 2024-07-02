package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankrestsrc.entity.xml.ParticipantInfo;

@Mapper(componentModel = "spring")
public interface ParticipantInfoMapper {
    ParticipantInfoEntity toParticipantInfoEntity(ParticipantInfo participantInfo);
    ParticipantInfo toParticipantInfo(ParticipantInfoEntity participantInfoEntity);
}
