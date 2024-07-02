package ru.bluewater.centralbankopencodeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.SWBICSEntity;
import ru.bluewater.centralbankopencodeproject.respository.SWBICSRepository;

@Service
public class SWBICSService {
    private final SWBICSRepository repository;
    @Autowired
    public SWBICSService(SWBICSRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createSWBICS(SWBICSEntity swbicsEntity) { repository.save(swbicsEntity); }
}
