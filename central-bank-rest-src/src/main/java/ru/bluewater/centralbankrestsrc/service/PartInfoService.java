package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestsrc.entity.PartInfoEntity;
import ru.bluewater.centralbankrestsrc.respository.PartInfoRepository;

@Service
public class PartInfoService {
    private final PartInfoRepository repository;

    @Autowired
    public PartInfoService(PartInfoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createPartInfo(PartInfoEntity partInfo) {
        repository.save(partInfo);
    }
}
