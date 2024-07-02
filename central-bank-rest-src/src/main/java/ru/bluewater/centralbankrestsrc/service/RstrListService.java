package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;
import ru.bluewater.centralbankrestsrc.respository.RstrListRepository;

@Service
public class RstrListService {
    private final RstrListRepository rstrListRepository;

    @Autowired
    public RstrListService(RstrListRepository rstrListRepository) {
        this.rstrListRepository = rstrListRepository;
    }

    @Transactional
    public void createRstrList(RstrListEntity rstrListEntity){
        rstrListRepository.save(rstrListEntity);
    }
}
