package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BICDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryFullUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.BICDirectoryEntryResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BICDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.BICDirectoryEntryListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.BICDirectoryEntryGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestsrc.entity.*;
import ru.bluewater.centralbankrestsrc.mapper.entity.*;
import ru.bluewater.centralbankrestsrc.mapper.xml.AccRstrListMapper;
import ru.bluewater.centralbankrestsrc.mapper.xml.AccountsMapper;
import ru.bluewater.centralbankrestsrc.respository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BICDirectoryEntryService {
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;
    private final BICDirectoryEntryEntityMapper bicDirectoryEntryEntityMapper;
    private final RootRepository rootRepository;
    private final AccRstrListRepository accRstrListRepository;
    private final AccRstrListMapper accRstrListMapper;
    private final AccountsEntityMapper accountsEntityMapper;
    private final AccountsRepository accountsRepository;
    private final SWBICSRepository swbicsRepository;
    private final SWBICSEntityMapper swbicsEntityMapper;
    private final ParticipantInfoRepository participantInfoRepository;
    private final ParticipantInfoEntityMapper participantInfoEntityMapper;

    @Transactional
    public BICDirectoryEntryCreateResponseDTO createBICDirectoryEntry(
            UUID ed807Uuid,
            BICDirectoryEntryCreateRequestDTO requestDTO
    ) throws ED807NotFoundException {
        var bicDir = bicDirectoryEntryEntityMapper.fromCreateRequestToEntity(requestDTO);
        var ed807 = rootRepository.findById(ed807Uuid).orElseThrow(() ->
                new ED807NotFoundException(ed807Uuid)
        );
        ed807.getBicDirectoryEntry().add(bicDir);
        bicDirectoryEntryRepository.save(bicDir);
        return bicDirectoryEntryEntityMapper.toCreateResponse(bicDir);
    }

    public BICDirectoryEntryListResponseDTO findBICDirectoryEntryListByEd807Uuid(UUID ed807Uuid) throws ED807NotFoundException {
        ED807Entity ed807 = rootRepository.findById(ed807Uuid).orElseThrow(() -> new ED807NotFoundException(ed807Uuid));
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

    @Transactional
    public List<BICDirectoryEntryResponseDTO> updateFullBicDirectoryEntry(
            List<BicDirectoryEntryFullUpdateRequestDTO> BicDirectoryEntryDTOs) {

        List<BICDirectoryEntryEntity> bicDirectoryEntryEntityList = new ArrayList<>();

        BicDirectoryEntryDTOs.forEach(bicDirectoryEntryDTO -> {
            BICDirectoryEntryEntity bicDirectoryEntryEntity = bicDirectoryEntryRepository.findById(bicDirectoryEntryDTO.getUuid()).get();

            ParticipantInfoEntity participantInfoEntity = participantInfoRepository.findById(bicDirectoryEntryDTO.getUuid()).get();

            participantInfoEntityMapper.updateFromRequest(bicDirectoryEntryDTO.getParticipantInfo(), participantInfoEntity);

            bicDirectoryEntryDTO.getAccounts().forEach(accountsRequestDTO -> {
                AccountsEntity accountsEntity = accountsRepository.findById(accountsRequestDTO.getUuid()).get();

                accountsRequestDTO.getAccRstrList().forEach(accRstrListRequestDTO -> {
                    AccRstrListEntity accRstrListEntity = accRstrListRepository.findById(accRstrListRequestDTO.getUuid()).get();
                    accRstrListMapper.updateFromRequest(accRstrListRequestDTO, accRstrListEntity);
                });

                accountsEntityMapper.updateFromRequest(accountsRequestDTO, accountsEntity);
            });

            bicDirectoryEntryDTO.getSwbics().forEach(swbicsRequestDTO -> {
                SWBICSEntity swbicsEntity = swbicsRepository.findById(swbicsRequestDTO.getUuid()).get();
                swbicsEntityMapper.updateFromRequest(swbicsRequestDTO, swbicsEntity);
            });

            bicDirectoryEntryEntityMapper.updateFromDto(bicDirectoryEntryDTO, bicDirectoryEntryEntity);

            bicDirectoryEntryEntityList.add(bicDirectoryEntryEntity);

        });

        return bicDirectoryEntryEntityMapper
                .toListResponse(bicDirectoryEntryRepository.saveAll(bicDirectoryEntryEntityList));
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
