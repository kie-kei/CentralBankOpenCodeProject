package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bluewater.centralbankrestapi.api.dto.request.create.ED807CreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ED807UpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ED807ResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.ED807ListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ED807GetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ED807UpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestsrc.dto.FileDTO;
import ru.bluewater.centralbankrestsrc.entity.*;
import ru.bluewater.centralbankrestsrc.dto.xml.ED807;
import ru.bluewater.centralbankrestsrc.mapper.entity.ED807EntityMapper;
import ru.bluewater.centralbankrestsrc.mapper.xml.ED807Mapper;
import ru.bluewater.centralbankrestsrc.mapper.xml.InitialEDMapper;
import ru.bluewater.centralbankrestsrc.mapper.xml.PartInfoMapper;
import ru.bluewater.centralbankrestsrc.respository.InitialEDRepository;
import ru.bluewater.centralbankrestsrc.respository.PartInfoRepository;
import ru.bluewater.centralbankrestsrc.respository.RootRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Validated
public class ED807Service {
    private final RootRepository rootRepository;

    private final ED807EntityMapper ed807EntityMapper;
    private final ED807Mapper ed807Mapper;
    private final PartInfoMapper partInfoMapper;
    private final InitialEDMapper initialEDMapper;
    private final InitialEDRepository initialEDRepository;
    private final PartInfoRepository partInfoRepository;


    @Autowired
    public ED807Service(RootRepository repository, ED807EntityMapper ed807EntityMapper, ED807Mapper ed807Mapper, PartInfoMapper partInfoMapper, InitialEDMapper initialEDMapper, InitialEDRepository initialEDRepository, PartInfoRepository partInfoRepository) {
        this.rootRepository = repository;
        this.ed807EntityMapper = ed807EntityMapper;
        this.ed807Mapper = ed807Mapper;
        this.partInfoMapper = partInfoMapper;
        this.initialEDMapper = initialEDMapper;
        this.initialEDRepository = initialEDRepository;
        this.partInfoRepository = partInfoRepository;
    }


    @Transactional
    public ED807Entity createED807FromFileDTO(FileDTO fileDTO, Principal principal){
        ED807 ed807 = fileDTO.getEd807();
        String filename = fileDTO.getFilename();

        ED807Entity ed807Entity =  ed807Mapper.toRootEntity(ed807);
        setAuditFieldsOnCreateRootEntity(ed807Entity, principal);
        ed807Entity.setFileName(filename);

        if (ed807.getPartInfo() != null){
            PartInfoEntity partInfoEntity = partInfoMapper.toPartInfoEntity(ed807.getPartInfo());
            partInfoRepository.save(partInfoEntity);
        }

        if(ed807.getInitialED() != null){
            InitialEDEntity initialEDEntity = initialEDMapper.toInitialEDEntity(ed807.getInitialED());
            initialEDRepository.save(initialEDEntity);
        }

        if (ed807Entity.getBicDirectoryEntry() != null) {
            List<BICDirectoryEntryEntity> bicDicList = ed807Entity.getBicDirectoryEntry();
            bicDicList.forEach(x -> {
                ParticipantInfoEntity participantInfoEntity = x.getParticipantInfo();
                participantInfoEntity.setBicDirectoryEntry(x);
            });
        }


        rootRepository.save(ed807Entity);

        return ed807Entity;
    }


//    @Transactional
//    public ED807Entity createRootEntity(ED807Entity ed807Entity, Principal principal) {
//        setAuditFieldsOnCreateRootEntity(ed807Entity, principal);
//
//        InitialEDEntity initialEDEntity = ed807Entity.getInitialED();
//        PartInfoEntity partInfoEntity = ed807Entity.getPartInfo();
//        List<BICDirectoryEntryEntity> bicDicList = ed807Entity.getBicDirectoryEntry();
//
//        if (bicDicList != null){
//            bicDicList.forEach(x -> {
//                ParticipantInfoEntity participantInfoEntity = x.getParticipantInfo();
//                participantInfoEntity.setBicDirectoryEntry(x);
//            });
//
//        }
//        if (initialEDEntity != null) {
//            initialEDEntity.setEd807Entity(ed807Entity);
//        }
//        if (partInfoEntity != null) {
//            partInfoEntity.setEd807Entity(ed807Entity);
//        }
//        return rootRepository.save(ed807Entity);
//    }

    @Transactional
    public ED807UpdateResponseDTO updateRoot(UUID uuid, ED807UpdateRequestDTO requestDTO) throws ED807NotFoundException {
        var rootEntity = rootRepository.findById(uuid).orElseThrow(() -> new ED807NotFoundException(uuid));
        ed807EntityMapper.updateFromDto(requestDTO, rootEntity);
        return ed807EntityMapper.toRootUpdateResponseDTO(rootEntity);

    }


    public ED807ResponseDTO findRootByUuid(UUID uuid) throws ED807NotFoundException {
        ED807Entity ED807Entity = rootRepository.findById(uuid).orElseThrow(() -> new ED807NotFoundException(uuid));
        return ed807EntityMapper.toRootResponseDTO(ED807Entity);
    }

    public ED807Entity findRootEntityByUuid(UUID uuid) throws ED807NotFoundException {
        return rootRepository.findById(uuid).orElseThrow(() -> new ED807NotFoundException(uuid));
    }

    public ED807ListResponseDTO findRootList(){
        var list = rootRepository.findAll();
        List<ED807GetResponseDTO> ED807GetResponseDTOS = ed807EntityMapper.toListRootGetResponseDTO(list);
        return new ED807ListResponseDTO(ED807GetResponseDTOS);
    }

    @Transactional
    public ED807ResponseDTO createED807(ED807CreateRequestDTO requestDTO, Principal principal){
        ED807Entity entity = ed807EntityMapper.fromCreateRequestToEntity(requestDTO);
        setAuditFieldsOnCreateRootEntity(entity, principal);
        rootRepository.save(entity);
        return ed807EntityMapper.toRootResponseDTO(entity);
    }

    @Transactional
    public void deleteED807(UUID uuid) throws ED807NotFoundException {
        var ed807 = rootRepository.findById(uuid).orElseThrow(() -> new ED807NotFoundException(uuid));
        rootRepository.delete(ed807);
    }

    public void setAuditFieldsOnCreateRootEntity(ED807Entity ed807Entity, Principal principal) {
        ed807Entity.setCreatedBy(principal.getName());
        ed807Entity.setCreatedAt(LocalDateTime.now());
    }

}
