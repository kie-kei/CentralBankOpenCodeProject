package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.ParticipantInfoRequestDTO;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;
import ru.bluewater.centralbankrestsrc.mapper.entity.ParticipantInfoEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.ParticipantInfoRepository;

import java.util.List;

@Service
public class ParticipantInfoService {
    private final ParticipantInfoRepository repository;
    private final RstrListService rstrListService;
    private final ParticipantInfoEntityMapper participantInfoEntityMapper;

    @Autowired
    public ParticipantInfoService(ParticipantInfoRepository repository, RstrListService rstrListService, ParticipantInfoEntityMapper participantInfoEntityMapper) {
        this.repository = repository;
        this.rstrListService = rstrListService;
        this.participantInfoEntityMapper = participantInfoEntityMapper;
    }

    @Transactional
    public ParticipantInfoEntity createParticipantInfo(ParticipantInfoEntity participantInfoEntity){
        List<RstrListEntity> list = participantInfoEntity.getRstrList();

        if (list != null)
            list.forEach(rstrListService::createRstrList);

        return repository.save(participantInfoEntity);
    }

    public void updateParticipantInfo(BICDirectoryEntryEntity existingEntity, ParticipantInfoRequestDTO requestDTO) {
        ParticipantInfoEntity participantInfo = existingEntity.getParticipantInfo();

        if (participantInfo == null) {
            participantInfo = new ParticipantInfoEntity();
            existingEntity.setParticipantInfo(participantInfo);
        }

        participantInfoEntityMapper.updateFromDTO(requestDTO, participantInfo);

        if (requestDTO.getRstrList() != null) {
            participantInfo.setRstrList(rstrListService.createRstrLists(participantInfo, requestDTO.getRstrList()));
        }
    }

    public ParticipantInfoEntity createParticipantInfoFromDTO(ParticipantInfoRequestDTO requestDTO, BICDirectoryEntryEntity bicDirectoryEntryEntity) {
        ParticipantInfoEntity participantInfo = participantInfoEntityMapper.toEntity(requestDTO);


        if (requestDTO.getRstrList() != null) {
            participantInfo.setRstrList(rstrListService.createRstrLists(participantInfo, requestDTO.getRstrList()));
        }

        return participantInfo;
    }
}
