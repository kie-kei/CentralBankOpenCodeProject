package ru.bluewater.centralbankopencodeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankopencodeproject.entity.RstrListEntity;
import ru.bluewater.centralbankopencodeproject.respository.ParticipantInfoRepository;

import java.util.List;

@Service
public class ParticipantInfoService {
    private final ParticipantInfoRepository repository;
    private final RstrListService rstrListService;

    @Autowired
    public ParticipantInfoService(ParticipantInfoRepository repository, RstrListService rstrListService) {
        this.repository = repository;
        this.rstrListService = rstrListService;
    }

    @Transactional
    public ParticipantInfoEntity createParticipantInfo(ParticipantInfoEntity participantInfoEntity){
        List<RstrListEntity> list = participantInfoEntity.getRstrList();

        if (list != null)
            list.forEach(rstrListService::createRstrList);

        return repository.save(participantInfoEntity);
    }
}
