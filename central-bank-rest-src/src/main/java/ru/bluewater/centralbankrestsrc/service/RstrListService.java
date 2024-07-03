package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.RstrListRequestDTO;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;
import ru.bluewater.centralbankrestsrc.mapper.entity.RstrListEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.RstrListRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RstrListService {
    private final RstrListRepository rstrListRepository;
    private final RstrListEntityMapper rstrListEntityMapper;

    @Autowired
    public RstrListService(RstrListRepository rstrListRepository, RstrListEntityMapper rstrListEntityMapper) {
        this.rstrListRepository = rstrListRepository;
        this.rstrListEntityMapper = rstrListEntityMapper;
    }

    @Transactional
    public void createRstrList(RstrListEntity rstrListEntity) {
        rstrListRepository.save(rstrListEntity);
    }

    public List<RstrListEntity> createRstrLists(ParticipantInfoEntity participantInfo, List<RstrListRequestDTO> rstrListRequestDTOS) {
        if (participantInfo.getRstrList() != null) {
            rstrListRepository.deleteAll(participantInfo.getRstrList());
        }

        List<RstrListEntity> newEntities = new ArrayList<>();

        rstrListRequestDTOS.forEach(rstrListRequestDTO -> {
            RstrListEntity rstrListEntity = rstrListEntityMapper.toEntity(rstrListRequestDTO);
            rstrListEntity.setParticipantInfo(participantInfo);
            newEntities.add(rstrListEntity);
        });

        return newEntities;
    }
}
