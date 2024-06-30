package ru.bluewater.centralbankopencodeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfo;
import ru.bluewater.centralbankopencodeproject.entity.RstrList;
import ru.bluewater.centralbankopencodeproject.respository.ParticipantInfoRepository;

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
    public ParticipantInfo createParticipantInfo(ParticipantInfo participantInfo){
        RstrList rstrList = participantInfo.getRstrList();
        if (rstrList != null && rstrList.getUuid() == null) {
            rstrListService.createRstrList(rstrList);
        }
        return repository.save(participantInfo);
    }
}
