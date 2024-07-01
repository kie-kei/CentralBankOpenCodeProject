package ru.bluewater.centralbankopencodeproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankopencodeproject.respository.BICDirectoryEntryRepository;

@Service
@Slf4j
public class BICDirectoryEntryService {
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Autowired
    public BICDirectoryEntryService(BICDirectoryEntryRepository bicDirectoryEntryRepository) {
        this.bicDirectoryEntryRepository = bicDirectoryEntryRepository;
    }
    @Transactional
    public void createBICDirectoryEntry(BICDirectoryEntryEntity bicDirectoryEntryEntity){
        bicDirectoryEntryRepository.save(bicDirectoryEntryEntity);
        log.debug(bicDirectoryEntryEntity + "saved");
    }
}
