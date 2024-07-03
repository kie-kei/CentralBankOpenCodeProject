package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.SWBICSRequestDTO;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;
import ru.bluewater.centralbankrestsrc.mapper.entity.SWBICSEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.SWBICSRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SWBICSService {
    private final SWBICSRepository repository;
    private final SWBICSEntityMapper swbicsEntityMapper;

    @Autowired
    public SWBICSService(SWBICSRepository repository, SWBICSEntityMapper swbicsEntityMapper) {
        this.repository = repository;
        this.swbicsEntityMapper = swbicsEntityMapper;
    }

    @Transactional
    public void createSWBICS(SWBICSEntity swbicsEntity) {
        repository.save(swbicsEntity);
    }

    public List<SWBICSEntity> createSWBICSList(BICDirectoryEntryEntity bicDirectoryEntryEntity, List<SWBICSRequestDTO> swbicsRequestDTOS) {
        if (bicDirectoryEntryEntity.getSwbics() != null) {
            repository.deleteAll(bicDirectoryEntryEntity.getSwbics());
        }
        List<SWBICSEntity> newEntities = new ArrayList<>();

        swbicsRequestDTOS.forEach(swbicsRequestDTO -> {
            SWBICSEntity swbicsEntity = swbicsEntityMapper.toEntity(swbicsRequestDTO);
            swbicsEntity.setBicDirectoryEntry(bicDirectoryEntryEntity);
            newEntities.add(swbicsEntity);
        });

        return newEntities;
    }
}
