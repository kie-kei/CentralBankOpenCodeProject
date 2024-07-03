package ru.bluewater.centralbankrestsrc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BicDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BicDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.RootEntity;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;
import ru.bluewater.centralbankrestsrc.mapper.entity.BICDirectoryEntryEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.BICDirectoryEntryRepository;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BICDirectoryEntryService {
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;
    private final SWBICSService swbicsService;
    private final ParticipantInfoService participantInfoService;
    private final BICDirectoryEntryEntityMapper bicDirectoryEntryEntityMapper;
    private final AccountsService accountsService;
    private final RootService rootService;
    @Autowired
    public BICDirectoryEntryService(BICDirectoryEntryRepository bicDirectoryEntryRepository, SWBICSService swbicsService, ParticipantInfoService participantInfoService, BICDirectoryEntryEntityMapper bicDirectoryEntryEntityMapper, AccountsService accountsService, RootService rootService) {
        this.bicDirectoryEntryRepository = bicDirectoryEntryRepository;
        this.swbicsService = swbicsService;
        this.participantInfoService = participantInfoService;
        this.bicDirectoryEntryEntityMapper = bicDirectoryEntryEntityMapper;
        this.accountsService = accountsService;
        this.rootService = rootService;
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

    @Transactional
    public BicDirectoryEntryUpdateResponseDTO updateBicDirectoryEntry
            (UUID uuid, BicDirectoryEntryUpdateRequestDTO requestDTO, Principal principal) throws BicDirectoryEntryNotFoundException, RootNotFoundException {
        BICDirectoryEntryEntity existingBicDirectoryEntry = bicDirectoryEntryRepository.findById(uuid)
                .orElseThrow(() -> new BicDirectoryEntryNotFoundException(uuid));

        bicDirectoryEntryEntityMapper.updateFromDto(requestDTO, existingBicDirectoryEntry);

        if (requestDTO.getParticipantInfo() != null) {
            participantInfoService.updateParticipantInfo(existingBicDirectoryEntry, requestDTO.getParticipantInfo());
        }

        if (requestDTO.getAccounts() != null) {
            existingBicDirectoryEntry.setAccounts(accountsService
                    .createNewAccountsEntityList(existingBicDirectoryEntry, requestDTO.getAccounts()));
        }

        if (requestDTO.getSwbics() != null) {
            existingBicDirectoryEntry.setSwbics(swbicsService
                    .createSWBICSList(existingBicDirectoryEntry, requestDTO.getSwbics()));
        }

        RootEntity rootEntity = rootService.findRootEntityByUuid(existingBicDirectoryEntry.getRootEntity().getUuid());
        rootService.setAuditFieldsOnUpdateRootEntity(rootEntity, principal);
        BICDirectoryEntryEntity updatedBicDirectoryEntry = bicDirectoryEntryRepository.save(existingBicDirectoryEntry);

        log.debug(updatedBicDirectoryEntry + "updated");

        return bicDirectoryEntryEntityMapper.toUpdateResponse(updatedBicDirectoryEntry);
    }

    @Transactional
    public BicDirectoryEntryCreateResponseDTO createBICDirectoryEntryFromDTO(BicDirectoryEntryCreateRequestDTO requestDTO, Principal principal)
            throws RootNotFoundException {
        RootEntity rootEntity = rootService.findRootEntityByUuid(requestDTO.getRootId());

        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryEntityMapper.toEntity(requestDTO);

        if (requestDTO.getParticipantInfo() != null) {
            bicDirectoryEntry.setParticipantInfo(participantInfoService
                    .createParticipantInfoFromDTO(requestDTO.getParticipantInfo(), bicDirectoryEntry));
        }

        if (requestDTO.getAccounts() != null) {
            bicDirectoryEntry.setAccounts(accountsService
                    .createNewAccountsEntityList(bicDirectoryEntry, requestDTO.getAccounts()));
        }

        if (requestDTO.getSwbics() != null) {
            bicDirectoryEntry.setSwbics(swbicsService
                    .createSWBICSList(bicDirectoryEntry, requestDTO.getSwbics()));
        }

        rootEntity.getBicDirectoryEntry().add(bicDirectoryEntry);
        bicDirectoryEntry.setRootEntity(rootEntity);

        rootService.setAuditFieldsOnUpdateRootEntity(rootEntity, principal);

        bicDirectoryEntryRepository.save(bicDirectoryEntry);

        log.debug(bicDirectoryEntry + "created");

        return bicDirectoryEntryEntityMapper.toCreateResponse(bicDirectoryEntry);
    }

    @Transactional
    public void deleteBICDirectoryEntry(UUID uuid) throws BicDirectoryEntryNotFoundException {
        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryRepository.findById(uuid)
                .orElseThrow(() -> new BicDirectoryEntryNotFoundException(uuid));

        bicDirectoryEntryRepository.delete(bicDirectoryEntry);
    }
}
