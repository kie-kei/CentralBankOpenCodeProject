package ru.bluewater.centralbankopencodeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.PartInfoEntity;
import ru.bluewater.centralbankopencodeproject.respository.PartInfoRepository;

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
