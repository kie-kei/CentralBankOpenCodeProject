package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;
import ru.bluewater.centralbankrestsrc.respository.SWBICSRepository;

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
