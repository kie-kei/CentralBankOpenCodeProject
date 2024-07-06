package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BICDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BICDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.BICDirectoryEntryListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.BICDirectoryEntryGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;
import ru.bluewater.centralbankrestsrc.mapper.entity.BICDirectoryEntryEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.BICDirectoryEntryRepository;
import ru.bluewater.centralbankrestsrc.respository.RootRepository;

import java.util.UUID;

@Service
public class BICDirectoryEntryService {
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;
    private final SWBICSService swbicsService;
    private final ParticipantInfoService participantInfoService;
    private final BICDirectoryEntryEntityMapper bicDirectoryEntryEntityMapper;
    private final AccountsService accountsService;
    private final ED807Service ed807Service;
    private final RootRepository rootRepository;
    @Autowired
    public BICDirectoryEntryService(
            BICDirectoryEntryRepository bicDirectoryEntryRepository,
            SWBICSService swbicsService, ParticipantInfoService participantInfoService,
            BICDirectoryEntryEntityMapper bicDirectoryEntryEntityMapper,
            AccountsService accountsService,
            ED807Service ed807Service,
            RootRepository rootRepository) {
        this.bicDirectoryEntryRepository = bicDirectoryEntryRepository;
        this.swbicsService = swbicsService;
        this.participantInfoService = participantInfoService;
        this.bicDirectoryEntryEntityMapper = bicDirectoryEntryEntityMapper;
        this.accountsService = accountsService;
        this.ed807Service = ed807Service;
        this.rootRepository = rootRepository;
    }

    @Transactional
    public BICDirectoryEntryCreateResponseDTO createBICDirectoryEntry(BICDirectoryEntryCreateRequestDTO requestDTO) throws RootNotFoundException {
        var bicDir = bicDirectoryEntryEntityMapper.fromCreateRequestToEntity(requestDTO);
        var ed807 = rootRepository.findById(requestDTO.getRootId()).orElseThrow(() ->
                new RootNotFoundException(requestDTO.getRootId())
        );
        ed807.getBicDirectoryEntry().add(bicDir);
        bicDirectoryEntryRepository.save(bicDir);
        return bicDirectoryEntryEntityMapper.toCreateResponse(bicDir);
    }

    public BICDirectoryEntryListResponseDTO findBICDirectoryEntryListByEd807Uuid(UUID ed807Uuid) throws RootNotFoundException {
        ED807Entity ed807 = rootRepository.findById(ed807Uuid).orElseThrow(() -> new RootNotFoundException(ed807Uuid));
        var bicList = ed807.getBicDirectoryEntry();
        return new BICDirectoryEntryListResponseDTO(bicDirectoryEntryEntityMapper.toListGetResponse(bicList));
    }

    @Transactional
    public BicDirectoryEntryUpdateResponseDTO updateBICDirectoryEntry(
            UUID uuid,
            BicDirectoryEntryUpdateRequestDTO requestDTO
    ) throws BicDirectoryEntryNotFoundException {
        var exitEntity = bicDirectoryEntryRepository.findById(uuid).orElseThrow(() ->
                new BicDirectoryEntryNotFoundException(uuid)
        );
        bicDirectoryEntryEntityMapper.updateFromDto(requestDTO, exitEntity);
        return bicDirectoryEntryEntityMapper.toUpdateResponse(exitEntity);
    }

    public BICDirectoryEntryGetResponseDTO findBICDirectoryEntry(UUID uuid) throws BicDirectoryEntryNotFoundException {
        var entity = bicDirectoryEntryRepository.findById(uuid).orElseThrow(() ->
                new BicDirectoryEntryNotFoundException(uuid)
        );
        return bicDirectoryEntryEntityMapper.toGetResponse(entity);
    }

    // до комментирования no usage
//    @Transactional
//    public void createBICDirectoryEntry(BICDirectoryEntryEntity bicDirectoryEntryEntity) {
//        bicDirectoryEntryRepository.save(bicDirectoryEntryEntity);
//    }

    // Хз работает или нет но пока думаю
//    @Transactional
//    public BicDirectoryEntryUpdateResponseDTO updateBicDirectoryEntry
//            (UUID uuid, BicDirectoryEntryUpdateRequestDTO requestDTO, Principal principal) throws BicDirectoryEntryNotFoundException, RootNotFoundException {
//
//        logger.debug("updateBicDirectoryEntry start");
//
//        BICDirectoryEntryEntity existingBicDirectoryEntry = bicDirectoryEntryRepository.findById(uuid)
//                .orElseThrow(() -> new BicDirectoryEntryNotFoundException(uuid));
//
//        bicDirectoryEntryEntityMapper.updateFromDto(requestDTO, existingBicDirectoryEntry);
//
////        if (requestDTO.getParticipantInfo() != null) {
////            participantInfoService.updateParticipantInfo(existingBicDirectoryEntry, requestDTO.getParticipantInfo());
////        }
////
////        if (requestDTO.getAccounts() != null) {
////            existingBicDirectoryEntry.setAccounts(accountsService
////                    .createNewAccountsEntityList(existingBicDirectoryEntry, requestDTO.getAccounts()));
////        }
////
////        if (requestDTO.getSwbics() != null) {
////            existingBicDirectoryEntry.setSwbics(swbicsService
////                    .createSWBICSList(existingBicDirectoryEntry, requestDTO.getSwbics()));
////        }
//
//        BICDirectoryEntryEntity updatedBicDirectoryEntry = bicDirectoryEntryRepository.save(existingBicDirectoryEntry);
//
//        logger.debug("updateBicDirectoryEntry finish");
//
//        return bicDirectoryEntryEntityMapper.toUpdateResponse(updatedBicDirectoryEntry);
//    }

    // работает но пока думаю
//    @Transactional
//    public BicDirectoryEntryCreateResponseDTO createBICDirectoryEntryFromDTO(BicDirectoryEntryCreateRequestDTO requestDTO, Principal principal)
//            throws RootNotFoundException {
//        System.out.println(" createBICDirectoryEntryFromDTO start ");
//        System.out.println(requestDTO.getParticipantInfo().toString());
//        ED807Entity ed807Entity = ED807Service.findRootEntityByUuid(requestDTO.getRootId());
//
//        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryEntityMapper.fromCreateRequestToEntity(requestDTO);
//
//        System.out.println(bicDirectoryEntry.getParticipantInfo());
////        if (requestDTO.getParticipantInfo() != null) {
////            bicDirectoryEntry.setParticipantInfo(participantInfoService
////                    .createParticipantInfoFromDTO(requestDTO.getParticipantInfo(), bicDirectoryEntry));
////        }
////
////        if (requestDTO.getAccounts() != null) {
////            bicDirectoryEntry.setAccounts(accountsService
////                    .createNewAccountsEntityList(bicDirectoryEntry, requestDTO.getAccounts()));
////        }
////
////        if (requestDTO.getSwbics() != null) {
////            bicDirectoryEntry.setSwbics(swbicsService
////                    .createSWBICSList(bicDirectoryEntry, requestDTO.getSwbics()));
////        }
//
//        ed807Entity.getBicDirectoryEntry().add(bicDirectoryEntry);
//
//        bicDirectoryEntryRepository.save(bicDirectoryEntry);
//
//        logger.debug("createBICDirectoryEntryFromDTO end");
//
//        return bicDirectoryEntryEntityMapper.toCreateResponse(bicDirectoryEntry);
//    }

    @Transactional
    public void deleteBICDirectoryEntry(UUID uuid) throws BicDirectoryEntryNotFoundException {
        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryRepository.findById(uuid)
                .orElseThrow(() -> new BicDirectoryEntryNotFoundException(uuid));

        bicDirectoryEntryRepository.delete(bicDirectoryEntry);
    }
}
