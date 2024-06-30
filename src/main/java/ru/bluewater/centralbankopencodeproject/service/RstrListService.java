package ru.bluewater.centralbankopencodeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.RstrList;
import ru.bluewater.centralbankopencodeproject.respository.RstrListRepository;

@Service
public class RstrListService {
    private final RstrListRepository rstrListRepository;

    @Autowired
    public RstrListService(RstrListRepository rstrListRepository) {
        this.rstrListRepository = rstrListRepository;
    }

    @Transactional
    public void createRstrList(RstrList rstrList){
        rstrListRepository.save(rstrList);
    }
}
