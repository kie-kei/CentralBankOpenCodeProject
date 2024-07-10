package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import ru.bluewater.centralbankrestsrc.mapper.ED807EntityMapper;
import ru.bluewater.centralbankrestsrc.mapper.InitialEDEntityMapper;
import ru.bluewater.centralbankrestsrc.mapper.PartInfoEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.InitialEDRepository;
import ru.bluewater.centralbankrestsrc.respository.PartInfoRepository;
import ru.bluewater.centralbankrestsrc.respository.ED807Repository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ED807Service {
    private final ED807Repository ed807Repository;

    private final ED807EntityMapper ed807EntityMapper;
    private final PartInfoEntityMapper partInfoMapper;
    private final InitialEDEntityMapper initialEDMapper;
    private final InitialEDRepository initialEDRepository;
    private final PartInfoRepository partInfoRepository;

    @Transactional
    public ED807Entity createED807FromFileDTO(FileDTO fileDTO, Principal principal){
        ED807 ed807 = fileDTO.getEd807();
        String filename = fileDTO.getFilename();

        ED807Entity ed807Entity = ed807EntityMapper.toEd807Entity(ed807);
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


        ed807Repository.save(ed807Entity);

        return ed807Entity;
    }

    @Transactional
    public ED807UpdateResponseDTO updateRoot(UUID uuid, ED807UpdateRequestDTO requestDTO) throws ED807NotFoundException {
        var rootEntity = ed807Repository.findById(uuid).orElseThrow(() -> new ED807NotFoundException(uuid));
        ed807EntityMapper.updateFromDto(requestDTO, rootEntity);
        return ed807EntityMapper.toRootUpdateResponseDTO(rootEntity);

    }

//    public ED807ResponseDTO findEd807Full(UUID uuid) throws ED807NotFoundException {
//        ED807Entity ed807Entity = ed807Repository.findById(uuid)
//                .orElseThrow(() -> new ED807NotFoundException(uuid));
//    }
    public ED807GetResponseDTO findEd807ByUuid(UUID uuid) throws ED807NotFoundException{
        ED807Entity ed807Entity = ed807Repository.findById(uuid).orElseThrow(() -> new ED807NotFoundException(uuid));
        return ed807EntityMapper.toGetResponse(ed807Entity);
    }

    public ED807ResponseDTO findFullEd807ByUuid(UUID uuid) throws ED807NotFoundException {
        ED807Entity ED807Entity = ed807Repository.findById(uuid).orElseThrow(() -> new ED807NotFoundException(uuid));
        Optional<PartInfoEntity> partInfo = partInfoRepository.findById(uuid);
        Optional<InitialEDEntity> initialEDEntity = initialEDRepository.findById(uuid);

        ED807ResponseDTO responseDTO = ed807EntityMapper.toRootResponseDTO(ED807Entity);

        partInfo.ifPresent(partInfoEntity -> responseDTO.setPartInfo(partInfoMapper.toPartInfoResponseDTO(partInfoEntity)));
        initialEDEntity.ifPresent(entity -> responseDTO.setInitialED(initialEDMapper.toResponse(entity)));
        return responseDTO;
    }

    public ED807ListResponseDTO findEd807List(){
        var list = ed807Repository.findAll();
        List<ED807GetResponseDTO> ED807GetResponseDTOS = ed807EntityMapper.toListRootGetResponseDTO(list);
        return new ED807ListResponseDTO(ED807GetResponseDTOS);
    }

    @Transactional
    public ED807ResponseDTO createED807(ED807CreateRequestDTO requestDTO, Principal principal){
        ED807Entity entity = ed807EntityMapper.fromCreateRequestToEntity(requestDTO);
        setAuditFieldsOnCreateRootEntity(entity, principal);
        ed807Repository.save(entity);
        return ed807EntityMapper.toRootResponseDTO(entity);
    }

    @Transactional
    public void deleteED807(UUID uuid) throws ED807NotFoundException {
        var ed807 = ed807Repository.findById(uuid).orElseThrow(() -> new ED807NotFoundException(uuid));
        ed807Repository.delete(ed807);
    }

    public void setAuditFieldsOnCreateRootEntity(ED807Entity ed807Entity, Principal principal) {
        ed807Entity.setCreatedBy(principal.getName());
        ed807Entity.setCreatedAt(LocalDateTime.now());
    }

}
