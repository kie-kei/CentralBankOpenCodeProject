package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BICDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BICDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.full.BICDirectoryEntryFullResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.BICDirectoryEntryListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.BICDirectoryEntryGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;
import ru.bluewater.centralbankrestsrc.mapper.BICDirectoryEntryEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.BICDirectoryEntryRepository;
import ru.bluewater.centralbankrestsrc.respository.ED807Repository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BICDirectoryEntryService {
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;
    private final BICDirectoryEntryEntityMapper bicDirectoryEntryEntityMapper;
    private final ED807Repository ed807Repository;

    @Transactional
    public BICDirectoryEntryCreateResponseDTO createBICDirectoryEntry(
            UUID ed807Uuid,
            BICDirectoryEntryCreateRequestDTO requestDTO
    ) throws ED807NotFoundException {
        var bicDir = bicDirectoryEntryEntityMapper.fromCreateRequestToEntity(requestDTO);
        var ed807 = ed807Repository.findById(ed807Uuid).orElseThrow(() ->
                new ED807NotFoundException(ed807Uuid)
        );

        bicDir.getParticipantInfo().setBicDirectoryEntry(bicDir);

        bicDirectoryEntryRepository.save(bicDir);
        ed807.getBicDirectoryEntry().add(bicDir);

        return bicDirectoryEntryEntityMapper.toCreateResponse(bicDir);
    }

    public BICDirectoryEntryFullResponseDTO findBicDirectoryEntryFullByEd807Uuid(UUID ed807Uuid) throws ED807NotFoundException {
        ED807Entity ed807 = ed807Repository.findById(ed807Uuid).orElseThrow(() -> new ED807NotFoundException(ed807Uuid));
        var bicList = ed807.getBicDirectoryEntry();
        return new BICDirectoryEntryFullResponseDTO(bicDirectoryEntryEntityMapper.toFullListResponse(bicList));
    }

    public BICDirectoryEntryListResponseDTO findBicDirectoryEntryListByEd807Uuid(UUID ed807Uuid) throws ED807NotFoundException {
        ED807Entity ed807 = ed807Repository.findById(ed807Uuid)
                .orElseThrow(() -> new ED807NotFoundException(ed807Uuid));
        List<BICDirectoryEntryEntity> bicList = ed807.getBicDirectoryEntry();

        if(bicList == null || bicList.isEmpty())
            return new BICDirectoryEntryListResponseDTO();

        return new BICDirectoryEntryListResponseDTO(bicDirectoryEntryEntityMapper.toListResponse(bicList));
    }


    @Transactional
    public BicDirectoryEntryUpdateResponseDTO updateBicDirectoryEntry(
            UUID uuid,
            BicDirectoryEntryUpdateRequestDTO requestDTO
    ) throws BicDirectoryEntryNotFoundException {
        var exitEntity = bicDirectoryEntryRepository.findById(uuid).orElseThrow(() ->
                new BicDirectoryEntryNotFoundException(uuid)
        );
        bicDirectoryEntryEntityMapper.updateFromDto(requestDTO, exitEntity);
        return bicDirectoryEntryEntityMapper.toUpdateResponse(exitEntity);
    }

    public BICDirectoryEntryGetResponseDTO findBicDirectoryEntryByUuid(UUID uuid) throws BicDirectoryEntryNotFoundException {
        var entity = bicDirectoryEntryRepository.findById(uuid).orElseThrow(() ->
                new BicDirectoryEntryNotFoundException(uuid)
        );
        return bicDirectoryEntryEntityMapper.toGetResponse(entity);
    }


    @Transactional
    public void deleteBicDirectoryEntry(UUID uuid) throws BicDirectoryEntryNotFoundException {
        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryRepository.findById(uuid)
                .orElseThrow(() -> new BicDirectoryEntryNotFoundException(uuid));

        bicDirectoryEntryRepository.delete(bicDirectoryEntry);
    }
}
