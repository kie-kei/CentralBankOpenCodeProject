package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.SWBICSCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.SWBICSUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.SWBICSCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.SWBICSGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.SWBICSUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.SWBICSNotFoundException;
import ru.bluewater.centralbankrestsrc.dto.xml.SWBICS;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.SWBICSEntity;
import ru.bluewater.centralbankrestsrc.mapper.entity.SWBICSEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.BICDirectoryEntryRepository;
import ru.bluewater.centralbankrestsrc.respository.SWBICSRepository;

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


//    @Transactional
//    public void createSWBICS(SWBICSEntity swbicsEntity) {
//        repository.save(swbicsEntity);
//    }
//
//    public List<SWBICSEntity> createSWBICSList(BICDirectoryEntryEntity bicDirectoryEntryEntity, List<SWBICSRequestDTO> swbicsRequestDTOS) {
//        if (bicDirectoryEntryEntity.getSwbics() != null) {
//            repository.deleteAll(bicDirectoryEntryEntity.getSwbics());
//        }
//        List<SWBICSEntity> newEntities = new ArrayList<>();
//
//        swbicsRequestDTOS.forEach(swbicsRequestDTO -> {
//            SWBICSEntity swbicsEntity = swbicsEntityMapper.toEntity(swbicsRequestDTO);
//            newEntities.add(swbicsEntity);
//        });
//
//        return newEntities;
//    }
}
