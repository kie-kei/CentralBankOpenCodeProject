package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ParticipantInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ParticipantInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ParticipantInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ParticipantInfoNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankrestsrc.mapper.ParticipantInfoEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.ParticipantInfoRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantInfoService {
    private final ParticipantInfoRepository participantInfoRepository;
    private final ParticipantInfoEntityMapper participantInfoEntityMapper;

    @Transactional
    public ParticipantInfoUpdateResponseDTO updateParticipantInfo(
            UUID uuid,
            ParticipantInfoUpdateRequestDTO requestDTO
    ) throws ParticipantInfoNotFoundException {
        ParticipantInfoEntity participantInfo = participantInfoRepository.findById(uuid).orElseThrow(() ->
                new ParticipantInfoNotFoundException(uuid)
        );
        participantInfoEntityMapper.updateFromRequest(requestDTO, participantInfo);

        return participantInfoEntityMapper.toUpdateResponse(participantInfo);
    }

    public ParticipantInfoGetResponseDTO findParticipantInfoByUuid(UUID uuid) throws ParticipantInfoNotFoundException {
        ParticipantInfoEntity participantInfo = participantInfoRepository.findById(uuid).orElseThrow(() ->
                new ParticipantInfoNotFoundException(uuid)
        );
        return participantInfoEntityMapper.toGetResponse(participantInfo);
    }

}
