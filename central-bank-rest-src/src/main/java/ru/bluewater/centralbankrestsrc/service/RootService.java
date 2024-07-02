package ru.bluewater.centralbankrestsrc.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bluewater.centralbankrestapi.api.exception.FileNotFoundException;
import ru.bluewater.centralbankrestsrc.respository.RootRepository;
import ru.bluewater.centralbankrestsrc.entity.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Validated
public class RootService {
    private final RootRepository rootRepository;
    private final ParticipantInfoService participantInfoService;
    private final BICDirectoryEntryService bicDirectoryEntryService;
    private final AccountsService accountsService;
    private final InitialEDService initialEDService;
    private final PartInfoService partInfoService;

    @Autowired
    public RootService(RootRepository repository, ParticipantInfoService participantInfoService, BICDirectoryEntryService bicDirectoryEntryService, AccountsService accountsService, InitialEDService initialEDService, PartInfoService partInfoService) {
        this.rootRepository = repository;
        this.participantInfoService = participantInfoService;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.accountsService = accountsService;
        this.initialEDService = initialEDService;
        this.partInfoService = partInfoService;
    }

    @Transactional
    public RootEntity saveRootEntity(RootEntity rootEntity, Principal principal) {
        InitialEDEntity initialEDEntity = rootEntity.getInitialED();
        PartInfoEntity partInfoEntity = rootEntity.getPartInfo();

        setAuditFieldsOnCreateRootEntity(rootEntity, principal);

        if (initialEDEntity != null) {
            initialEDEntity.setRootEntity(rootEntity);
            initialEDService.createInitialED(initialEDEntity);
        }

        if (partInfoEntity != null) {
            partInfoEntity.setRootEntity(rootEntity);
            partInfoService.createPartInfo(partInfoEntity);
        }

        rootEntity.getBicDirectoryEntry().forEach(entry -> {
            entry.setRootEntity(rootEntity);

            ParticipantInfoEntity participantInfoEntity = entry.getParticipantInfo();
            List<AccountsEntity> accounts = entry.getAccounts();

            if (participantInfoEntity != null && participantInfoEntity.getUuid() == null) {
                participantInfoEntity.setBicDirectoryEntry(entry);
                participantInfoService.createParticipantInfo(participantInfoEntity);
            }

            if(accounts != null) {
                accounts.forEach(acc -> {
                    acc.setBicDirectoryEntry(entry);
                    accountsService.createAccounts(acc);
                });
            }

            bicDirectoryEntryService.createBICDirectoryEntry(entry);
        });

        return rootRepository.save(rootEntity);
    }

    @Transactional
    public RootEntity findRootByUuid(UUID uuid) throws FileNotFoundException {
        return rootRepository.findById(uuid).orElseThrow(() -> new FileNotFoundException(uuid));
    }

    private void setAuditFieldsOnCreateRootEntity(RootEntity rootEntity, Principal principal){
        rootEntity.setCreatedBy(principal.getName());
        rootEntity.setCreatedAt(LocalDate.now());
    }

    private void setAuditFieldsOnUpdateRootEntity(RootEntity rootEntity, Principal principal){
        rootEntity.setUpdatedAt(LocalDate.now());
        rootEntity.setUpdatedBy(principal.getName());
    }
}
