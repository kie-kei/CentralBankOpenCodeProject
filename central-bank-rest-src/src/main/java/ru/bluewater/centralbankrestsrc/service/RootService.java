package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.bluewater.centralbankrestapi.api.dto.request.update.ED807UpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.ED807ResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.ED807ListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.ED807GetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.ED807UpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.*;
import ru.bluewater.centralbankrestsrc.mapper.entity.ED807EntityMapper;
import ru.bluewater.centralbankrestsrc.respository.RootRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Validated
public class RootService {
    private final RootRepository rootRepository;

    private final ED807EntityMapper ED807EntityMapper;


    @Autowired
    public RootService(RootRepository repository, ED807EntityMapper ED807EntityMapper) {
        this.rootRepository = repository;
        this.ED807EntityMapper = ED807EntityMapper;
    }

    @Transactional
    public ED807Entity createRootEntity(ED807Entity ed807Entity, Principal principal) {
        InitialEDEntity initialEDEntity = ed807Entity.getInitialED();
        PartInfoEntity partInfoEntity = ed807Entity.getPartInfo();

//        setAuditFieldsOnCreateRootEntity(ED807Entity.getUuid(), principal);


        if (initialEDEntity != null) {
            initialEDEntity.setEd807Entity(ed807Entity);
        }

        if (partInfoEntity != null) {
            partInfoEntity.setEd807Entity(ed807Entity);
        }

        ed807Entity.setCreatedBy(principal.getName());
        ed807Entity.setCreatedAt(LocalDateTime.now());

        return rootRepository.save(ed807Entity);
    }

    @Transactional
    public ED807UpdateResponseDTO updateRoot(UUID uuid, ED807UpdateRequestDTO requestDTO) throws RootNotFoundException {
        var rootEntity = rootRepository.findById(uuid).orElseThrow(() -> new RootNotFoundException(uuid));
        ED807EntityMapper.updateFromDto(requestDTO, rootEntity);
        return ED807EntityMapper.toRootUpdateResponseDTO(rootEntity);

    }


    public ED807ResponseDTO findRootByUuid(UUID uuid) throws RootNotFoundException {
        ED807Entity ED807Entity = rootRepository.findById(uuid).orElseThrow(() -> new RootNotFoundException(uuid));
        return ED807EntityMapper.toRootResponseDTO(ED807Entity);
    }

    public ED807Entity findRootEntityByUuid(UUID uuid) throws RootNotFoundException {
        return rootRepository.findById(uuid).orElseThrow(() -> new RootNotFoundException(uuid));
    }

    public ED807ListResponseDTO findRootList(){
        var list = rootRepository.findAll();
        List<ED807GetResponseDTO> ED807GetResponseDTOS = ED807EntityMapper.toListRootGetResponseDTO(list);
        return new ED807ListResponseDTO(ED807GetResponseDTOS);
    }

//    @Transactional
//    public void setAuditFieldsOnCreateRootEntity(UUID uuid, Principal principal) {
////        rootRepository.createAuditFields(uuid, LocalDateTime.now(), principal.getName());
//    }

}
