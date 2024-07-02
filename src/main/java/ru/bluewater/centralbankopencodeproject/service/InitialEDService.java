package ru.bluewater.centralbankopencodeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.InitialEDEntity;
import ru.bluewater.centralbankopencodeproject.respository.InitialEDRepository;

@Service
public class InitialEDService {
    private final InitialEDRepository repository;
    @Autowired
    public InitialEDService(InitialEDRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createInitialED(InitialEDEntity initialEDEntity) {
        repository.save(initialEDEntity);
    }
}
