package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bluewater.centralbankrestapi.api.dto.request.update.RootUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.RootResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.RootListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.RootGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.RootUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.*;
import ru.bluewater.centralbankrestsrc.mapper.entity.RootEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.RootRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Validated
public class RootService {
    private final RootRepository rootRepository;
    private final ParticipantInfoService participantInfoService;
    private final AccountsService accountsService;
    private final InitialEDService initialEDService;
    private final PartInfoService partInfoService;
    private final RootEntityMapper rootEntityMapper;
    private final RstrListService rstrListService;
    private final AccRstrListService accRstrListService;

    @Autowired
    public RootService(RootRepository repository, ParticipantInfoService participantInfoService, AccountsService accountsService, InitialEDService initialEDService, PartInfoService partInfoService, RootEntityMapper rootEntityMapper, RstrListService rstrListService, AccRstrListService accRstrListService) {
        this.rootRepository = repository;
        this.participantInfoService = participantInfoService;
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
        }

        if (partInfoEntity != null) {
            partInfoEntity.setRootEntity(rootEntity);
        }

        rootEntity.getBicDirectoryEntry().forEach(entry -> {
            entry.setRootEntity(rootEntity);

            ParticipantInfoEntity participantInfoEntity = entry.getParticipantInfo();
            List<AccountsEntity> accounts = entry.getAccounts();

            List<SWBICSEntity> swbicsEntities = entry.getSwbics(); // не факт

            if (swbicsEntities != null && !swbicsEntities.isEmpty()) {
                swbicsEntities.forEach(
                        swbicsEntity -> {
                            swbicsEntity.setBicDirectoryEntry(entry);
                        });
            }

            if (participantInfoEntity != null && participantInfoEntity.getUuid() == null) {
                participantInfoEntity.setBicDirectoryEntry(entry);
                List<RstrListEntity> rstrListEntities = participantInfoEntity.getRstrList();

                if (rstrListEntities != null && !rstrListEntities.isEmpty()) {
                    rstrListEntities.forEach(
                            rstrListEntity -> {
                                rstrListEntity.setParticipantInfo(participantInfoEntity);
                            }
                    );
                }

            }

            if (accounts != null) {
                accounts.forEach(acc -> {
                    acc.setBicDirectoryEntry(entry);
                    List<AccRstrListEntity> accRstrListEntities = acc.getAccRstrList(); //убрать начиная отсюда вроде без этого работает
                    if (accRstrListEntities != null && !accRstrListEntities.isEmpty()) {
                        accRstrListEntities.forEach(
                                accRstrListEntity -> {
                                    accRstrListEntity.setAccounts(acc);
                                });
                    }
                });
            }
        });

        return rootRepository.save(rootEntity);
    }

    @Transactional
    public RootUpdateResponseDTO updateRoot(UUID uuid, RootUpdateRequestDTO requestDTO) throws RootNotFoundException {
        var rootEntity = rootRepository.findById(uuid).orElseThrow(() -> new RootNotFoundException(uuid));
        rootEntityMapper.updateFromDto(requestDTO, rootEntity);
        return rootEntityMapper.toRootUpdateResponseDTO(rootEntity);

    }


    public RootResponseDTO findRootByUuid(UUID uuid) throws RootNotFoundException {
        RootEntity rootEntity = rootRepository.findById(uuid).orElseThrow(() -> new RootNotFoundException(uuid));
        return rootEntityMapper.toRootResponseDTO(rootEntity);
    }

    public RootEntity findRootEntityByUuid(UUID uuid) throws RootNotFoundException {
        return rootRepository.findById(uuid).orElseThrow(() -> new RootNotFoundException(uuid));
    }

    public RootListResponseDTO findRootList(){
        var list = rootRepository.findAll();
        List<RootGetResponseDTO> rootGetResponseDTOS = rootEntityMapper.toListRootGetResponseDTO(list);
        return new RootListResponseDTO(rootGetResponseDTOS);
    }

    private void setAuditFieldsOnCreateRootEntity(RootEntity rootEntity, Principal principal) {
        rootEntity.setCreatedBy(principal.getName());
        rootEntity.setCreatedAt(LocalDateTime.now());
    }

    public void setAuditFieldsOnUpdateRootEntity(RootEntity rootEntity, Principal principal) {
        rootEntity.setUpdatedAt(LocalDateTime.now());
        rootEntity.setUpdatedBy(principal.getName());
    }
}
