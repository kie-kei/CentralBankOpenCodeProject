package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;
import ru.bluewater.centralbankrestsrc.respository.AccRstrListRepository;

@Service
public class AccRstrListService {
    private final AccRstrListRepository repository;

    @Autowired
    public AccRstrListService(AccRstrListRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createAccRstrList(AccRstrListEntity accRstrList) {
        repository.save(accRstrList);
    }
}
