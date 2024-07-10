package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.SWBICSCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.SWBICSUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.SWBICSCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.SWBICSListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.SWBICSGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.SWBICSUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.RstrListNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.SWBICSNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;
import ru.bluewater.centralbankrestsrc.mapper.SWBICSEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.BICDirectoryEntryRepository;
import ru.bluewater.centralbankrestsrc.respository.SWBICSRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SWBICSService {
    private final SWBICSRepository swbicsRepository;
    private final SWBICSEntityMapper swbicsEntityMapper;
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Transactional
    public SWBICSCreateResponseDTO createSwbics(
            UUID bicDirectoryEntryUuid,
            SWBICSCreateRequestDTO requestDTO
    ) throws BicDirectoryEntryNotFoundException {
        BICDirectoryEntryEntity bicDirectoryEntryEntity = bicDirectoryEntryRepository.findById(bicDirectoryEntryUuid)
                .orElseThrow(() -> new BicDirectoryEntryNotFoundException(bicDirectoryEntryUuid));

        SWBICSEntity swbicsEntity = swbicsRepository.save(
                swbicsEntityMapper.fromCreateRequestToEntity(requestDTO)
        );

        bicDirectoryEntryEntity.getSwbics().add(swbicsEntity);

        return swbicsEntityMapper.toCreateResponse(swbicsEntity);
    }

    @Transactional
    public SWBICSUpdateResponseDTO updateSwbics(
            UUID uuid,
            SWBICSUpdateRequestDTO requestDTO
    ) throws SWBICSNotFoundException {
        SWBICSEntity swbicsEntity = swbicsRepository.findById(uuid).orElseThrow(() ->
                new SWBICSNotFoundException(uuid)
        );

        swbicsEntityMapper.updateFromRequest(requestDTO, swbicsEntity);

        return swbicsEntityMapper.toUpdateResponse(swbicsEntity);
    }

    public SWBICSGetResponseDTO findSwbicsByUuid(UUID uuid) throws SWBICSNotFoundException {
        SWBICSEntity swbicsEntity = swbicsRepository.findById(uuid).orElseThrow(() ->
                new SWBICSNotFoundException(uuid)
        );

        return swbicsEntityMapper.toGetResponse(swbicsEntity);
    }

    public SWBICSListResponseDTO findListSWBICS(UUID uuid) throws BicDirectoryEntryNotFoundException {
        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryRepository.findById(uuid)
                .orElseThrow(() -> new BicDirectoryEntryNotFoundException(uuid));

        List<SWBICSEntity> swbicsEntityList = bicDirectoryEntry.getSwbics();
        if (swbicsEntityList == null || swbicsEntityList.isEmpty())
            return new SWBICSListResponseDTO();

        return new SWBICSListResponseDTO(swbicsEntityMapper.toList(swbicsEntityList));
    }

    @Transactional
    public void deleteSWBICS(UUID uuid) throws SWBICSNotFoundException {
        SWBICSEntity swbics = swbicsRepository.findById(uuid)
                .orElseThrow(() -> new SWBICSNotFoundException(uuid));

        BICDirectoryEntryEntity bicDirectoryEntryEntity = bicDirectoryEntryRepository.findBySwbics(swbics);
        if (bicDirectoryEntryEntity != null) {
            bicDirectoryEntryEntity.getSwbics().remove(swbics);
            bicDirectoryEntryRepository.save(bicDirectoryEntryEntity);
        }
        swbicsRepository.delete(swbics);
    }
}
