package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.ParticipantInfoRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ParticipantInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ParticipantInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ParticipantInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ParticipantInfoNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;
import ru.bluewater.centralbankrestsrc.mapper.entity.ParticipantInfoEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.ParticipantInfoRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantInfoService {
    private final ParticipantInfoRepository participantInfoRepository;
    private final RstrListService rstrListService;
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

    public ParticipantInfoGetResponseDTO participantInfoGetResponseDTO(UUID uuid) throws ParticipantInfoNotFoundException {
        ParticipantInfoEntity participantInfo = participantInfoRepository.findById(uuid).orElseThrow(() ->
                new ParticipantInfoNotFoundException(uuid)
        );
        return participantInfoEntityMapper.toGetResponse(participantInfo);
    }


//    @Transactional
//    public ParticipantInfoEntity createParticipantInfo(ParticipantInfoEntity participantInfoEntity){
//        List<RstrListEntity> list = participantInfoEntity.getRstrList();
//
//        if (list != null)
//            list.forEach(rstrListService::createRstrList);
//
//        return participantInfoRepository.save(participantInfoEntity);
//    }
//
//    public void updateParticipantInfo(BICDirectoryEntryEntity existingEntity, ParticipantInfoRequestDTO requestDTO) {
//        ParticipantInfoEntity participantInfo = existingEntity.getParticipantInfo();
//
//        if (participantInfo == null) {
//            participantInfo = new ParticipantInfoEntity();
//            existingEntity.setParticipantInfo(participantInfo);
//        }
//
//        participantInfoEntityMapper.updateFromDTO(requestDTO, participantInfo);
//
//        if (requestDTO.getRstrList() != null) {
//            participantInfo.setRstrList(rstrListService.createRstrLists(participantInfo, requestDTO.getRstrList()));
//        }
//    }
//
//    public ParticipantInfoEntity createParticipantInfoFromDTO(ParticipantInfoRequestDTO requestDTO, BICDirectoryEntryEntity bicDirectoryEntryEntity) {
//        ParticipantInfoEntity participantInfo = participantInfoEntityMapper.toEntity(requestDTO);
//
//
//        if (requestDTO.getRstrList() != null) {
//            participantInfo.setRstrList(rstrListService.createRstrLists(participantInfo, requestDTO.getRstrList()));
//        }
//
//        return participantInfo;
//    }
}
