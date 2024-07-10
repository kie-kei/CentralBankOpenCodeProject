package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.InitialEDCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.InitialEDUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.InitialEDCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.InitialEDGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.InitialEDUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.AccRstrListNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.ED807NotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.InitialEDNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;
import ru.bluewater.centralbankrestsrc.entity.InitialEDEntity;
import ru.bluewater.centralbankrestsrc.mapper.InitialEDEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.InitialEDRepository;
import ru.bluewater.centralbankrestsrc.respository.ED807Repository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InitialEDService {
    private final InitialEDRepository initialEDRepository;
    private final ED807Repository ed807Repository;
    private final InitialEDEntityMapper initialEDEntityMapper;
    @Transactional
    public InitialEDCreateResponseDTO createInitialEdByEd807Uuid(UUID ed807Uuid, InitialEDCreateRequestDTO requestDTO) throws ED807NotFoundException {
        ED807Entity ed807Entity = ed807Repository.findById(ed807Uuid).orElseThrow(() ->
                new ED807NotFoundException(ed807Uuid));

        InitialEDEntity initialEDEntity = initialEDEntityMapper.fromCreateRequestToEntity(requestDTO);
        initialEDEntity.setEd807Entity(ed807Entity);

        return initialEDEntityMapper.toCreateResponse(initialEDRepository.save(initialEDEntity));
    }

    public InitialEDGetResponseDTO findInitialEdByEd807Uuid(UUID ed807Uuid) throws InitialEDNotFoundException {
        InitialEDEntity initialEDEntity = initialEDRepository.findById(ed807Uuid).orElseThrow(() ->
                new InitialEDNotFoundException(ed807Uuid));

        return initialEDEntityMapper.toGetResponse(initialEDEntity);
    }

    @Transactional
    public InitialEDUpdateResponseDTO updateInitialEdByEd807Uuid(
            UUID uuid,
            InitialEDUpdateRequestDTO requestDTO
    ) throws InitialEDNotFoundException {
        InitialEDEntity initialEDEntity = initialEDRepository.findById(uuid).orElseThrow(() ->
                new InitialEDNotFoundException(uuid));

        initialEDEntityMapper.updateFromRequest(requestDTO, initialEDEntity);

        return initialEDEntityMapper.toUpdateResponse(initialEDRepository.save(initialEDEntity));

    }

    @Transactional
    public void deleteInitialED(UUID uuid) throws InitialEDNotFoundException {
        InitialEDEntity initialEDEntity = initialEDRepository.findById(uuid)
                .orElseThrow(() -> new InitialEDNotFoundException(uuid));

        initialEDRepository.delete(initialEDEntity);
    }
}
