package ru.bluewater.centralbankopencodeproject.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bluewater.centralbankopencodeproject.entity.AccountsEntity;
import ru.bluewater.centralbankopencodeproject.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankopencodeproject.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankopencodeproject.entity.RootEntity;
import ru.bluewater.centralbankopencodeproject.respository.RootRepository;

import java.util.List;
import java.util.UUID;

@Service
@Validated
public class RootService {
    private final RootRepository rootRepository;
    private final ParticipantInfoService participantInfoService;
    private final BICDirectoryEntryService bicDirectoryEntryService;

    @Autowired
    public RootService(RootRepository repository, ParticipantInfoService participantInfoService, BICDirectoryEntryService bicDirectoryEntryService) {
        this.rootRepository = repository;
        this.participantInfoService = participantInfoService;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
    }

    @Transactional
    public RootEntity saveRootEntity(@Valid RootEntity rootEntity) {
        for (BICDirectoryEntryEntity entry : rootEntity.getBicDirectoryEntry()) {
            entry.setRootEntity(rootEntity);
            ParticipantInfoEntity participantInfoEntity = entry.getParticipantInfo();
            List<AccountsEntity> accounts = entry.getAccounts();
            if (participantInfoEntity != null && participantInfoEntity.getUuid() == null) {
                participantInfoEntity.setBicDirectoryEntry(entry);
                participantInfoService.createParticipantInfo(participantInfoEntity);
            }
            if(accounts != null){
                for(AccountsEntity acc: accounts){
                    acc.setBicDirectoryEntry(entry);
                }
            }
            bicDirectoryEntryService.createBICDirectoryEntry(entry);
        }
        return rootRepository.save(rootEntity);
    }
    @Transactional
    public RootEntity findRootByUuid(UUID uuid) {
        return rootRepository.findById(uuid).get();
    }
}
