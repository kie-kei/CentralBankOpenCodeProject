package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bluewater.centralbankrestapi.api.dto.response.RootResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestsrc.mapper.entity.RootEntityMapper;
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
    private final RootEntityMapper rootEntityMapper;
    private final RstrListService rstrListService;
    private final AccRstrListService accRstrListService;

    @Autowired
    public RootService(RootRepository repository, ParticipantInfoService participantInfoService, BICDirectoryEntryService bicDirectoryEntryService, AccountsService accountsService, InitialEDService initialEDService, PartInfoService partInfoService, RootEntityMapper rootEntityMapper, RstrListService rstrListService, AccRstrListService accRstrListService) {
        this.rootRepository = repository;
        this.participantInfoService = participantInfoService;
        this.bicDirectoryEntryService = bicDirectoryEntryService;
        this.accountsService = accountsService;
        this.initialEDService = initialEDService;
        this.partInfoService = partInfoService;
        this.rootEntityMapper = rootEntityMapper;
        this.rstrListService = rstrListService;
        this.accRstrListService = accRstrListService;
    }

    @Transactional
    public RootEntity createRootEntity(RootEntity rootEntity, Principal principal) {
        InitialEDEntity initialEDEntity = rootEntity.getInitialED();
        PartInfoEntity partInfoEntity = rootEntity.getPartInfo();

        setAuditFieldsOnCreateRootEntity(rootEntity, principal);

        if (initialEDEntity != null) {
            initialEDEntity.setRootEntity(rootEntity);
//            initialEDService.createInitialED(initialEDEntity);
        }

        if (partInfoEntity != null) {
            partInfoEntity.setRootEntity(rootEntity);
//            partInfoService.createPartInfo(partInfoEntity);
        }

        rootEntity.getBicDirectoryEntry().forEach(entry -> {
            entry.setRootEntity(rootEntity);

            ParticipantInfoEntity participantInfoEntity = entry.getParticipantInfo();
            List<AccountsEntity> accounts = entry.getAccounts();

            List<SWBICSEntity> swbicsEntities = entry.getSwbics(); // не факт

            if(swbicsEntities != null && !swbicsEntities.isEmpty()){
                swbicsEntities.forEach(
                        swbicsEntity -> {
                            swbicsEntity.setBicDirectoryEntry(entry);
                        });
            }

            if (participantInfoEntity != null && participantInfoEntity.getUuid() == null) {
                participantInfoEntity.setBicDirectoryEntry(entry);
//                participantInfoService.createParticipantInfo(participantInfoEntity);
                List<RstrListEntity> rstrListEntities = participantInfoEntity.getRstrList();

                if(rstrListEntities != null && !rstrListEntities.isEmpty()){
                    rstrListEntities.forEach(
                            rstrListEntity -> {
                                rstrListEntity.setParticipantInfo(participantInfoEntity);
//                                rstrListService.createRstrList(rstrListEntity);
                            }
                    );
                }

            }

            if(accounts != null) {
                accounts.forEach(acc -> {
                    acc.setBicDirectoryEntry(entry);
//                    accountsService.createAccounts(acc);
                    List<AccRstrListEntity> accRstrListEntities = acc.getAccRstrList(); //убрать начиная отсюда вроде без этого работает
                    if(accRstrListEntities != null && !accRstrListEntities.isEmpty()){
                        accRstrListEntities.forEach(
                                accRstrListEntity -> {
                                    accRstrListEntity.setAccounts(acc);
//                                    accRstrListService.createAccRstrList(accRstrListEntity);

                                });
                    }
                });
            }

//            bicDirectoryEntryService.createBICDirectoryEntry(entry);
        });

        return rootRepository.save(rootEntity);
    }

    public RootResponseDTO findRootByUuid(UUID uuid) throws RootNotFoundException {
        RootEntity rootEntity = rootRepository.findById(uuid).orElseThrow(() -> new RootNotFoundException(uuid));
        return rootEntityMapper.toRootResponseDTO(rootEntity);
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
