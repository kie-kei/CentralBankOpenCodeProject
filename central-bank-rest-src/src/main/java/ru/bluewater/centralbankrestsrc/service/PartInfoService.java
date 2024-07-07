package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.PartInfoCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.PartInfoUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.PartInfoCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.PartInfoGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.PartInfoUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.RootNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;
import ru.bluewater.centralbankrestsrc.entity.PartInfoEntity;
import ru.bluewater.centralbankrestsrc.mapper.entity.PartInfoEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.PartInfoRepository;
import ru.bluewater.centralbankrestsrc.respository.RootRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartInfoService {
    private final PartInfoRepository partInfoRepository;
    private final PartInfoEntityMapper partInfoEntityMapper;
    private final RootRepository ed807Repository;


    @Transactional
    public PartInfoCreateResponseDTO createPartInfoByEd807Uuid(PartInfoCreateRequestDTO requestDTO) throws RootNotFoundException {
        ED807Entity ed807Entity = ed807Repository.findById(requestDTO.getEd807Uuid()).orElseThrow(() ->
                new RootNotFoundException(requestDTO.getEd807Uuid()));

        PartInfoEntity partInfoEntity = partInfoEntityMapper.fromCreateRequestToEntity(requestDTO);
        partInfoEntity.setEd807Entity(ed807Entity);

        return partInfoEntityMapper.toCreateResponse(partInfoRepository.save(partInfoEntity));
    }

    public PartInfoGetResponseDTO findPartInfoByEd807Uuid(UUID ed807Uuid) throws RootNotFoundException {
        PartInfoEntity partInfo = partInfoRepository.findById(ed807Uuid).orElseThrow(() ->
                new RootNotFoundException(ed807Uuid));

        return partInfoEntityMapper.toGetResponse(partInfo);
    }

    @Transactional
    public PartInfoUpdateResponseDTO updatePartInfoByEd807Uuid(
            PartInfoUpdateRequestDTO requestDTO
    ) throws RootNotFoundException {
        PartInfoEntity partInfoEntity = partInfoRepository.findById(requestDTO.getEd807Uuid()).orElseThrow(() ->
                new RootNotFoundException(requestDTO.getEd807Uuid()));

        partInfoEntityMapper.updateFromRequest(requestDTO, partInfoEntity);

        return partInfoEntityMapper.toUpdateResponse(partInfoRepository.save(partInfoEntity));

    }
}
