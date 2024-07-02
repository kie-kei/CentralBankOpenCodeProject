package ru.bluewater.centralbankopencodeproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankopencodeproject.entity.SWBICSEntity;
import ru.bluewater.centralbankopencodeproject.respository.BICDirectoryEntryRepository;

import java.util.List;

@Service
@Slf4j
public class BICDirectoryEntryService {
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;
    private final SWBICSService swbicsService;

    @Autowired
    public BICDirectoryEntryService(BICDirectoryEntryRepository bicDirectoryEntryRepository, SWBICSService swbicsService) {
        this.bicDirectoryEntryRepository = bicDirectoryEntryRepository;
        this.swbicsService = swbicsService;
    }
    @Transactional
    public void createBICDirectoryEntry(BICDirectoryEntryEntity bicDirectoryEntryEntity) {
        List<SWBICSEntity> swbicsEntityList = bicDirectoryEntryEntity.getSwbics();

        if (swbicsEntityList != null)
            swbicsEntityList.forEach(swbicsEntity -> {
                swbicsEntity.setBicDirectoryEntry(bicDirectoryEntryEntity);
                swbicsService.createSWBICS(swbicsEntity);
            });

        bicDirectoryEntryRepository.save(bicDirectoryEntryEntity);
        log.debug(bicDirectoryEntryEntity + "saved");
    }
}
