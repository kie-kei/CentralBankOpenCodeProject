package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.RstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.RstrListUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.RstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.RstrListListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.RstrListGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.RstrListUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.ParticipantInfoNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.RstrListNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.ParticipantInfoEntity;
import ru.bluewater.centralbankrestsrc.entity.RstrListEntity;
import ru.bluewater.centralbankrestsrc.mapper.RstrListEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.ParticipantInfoRepository;
import ru.bluewater.centralbankrestsrc.respository.RstrListRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RstrListService {
    private final RstrListRepository rstrListRepository;
    private final ParticipantInfoRepository participantInfoRepository;
    private final RstrListEntityMapper rstrListEntityMapper;

    @Transactional
    public RstrListCreateResponseDTO createRstrList(UUID uuid, RstrListCreateRequestDTO requestDTO) throws ParticipantInfoNotFoundException {
        ParticipantInfoEntity participantInfoEntity = participantInfoRepository.findById(uuid).orElseThrow(()
                -> new ParticipantInfoNotFoundException(uuid)
        );

        RstrListEntity rstrList = rstrListRepository.save(rstrListEntityMapper.fromCreateRequestToEntity(requestDTO));
        participantInfoEntity.getRstrList().add(rstrList);

        return rstrListEntityMapper.toCreateResponse(rstrList);
    }

    @Transactional
    public RstrListUpdateResponseDTO updateRstrList(UUID uuid, RstrListUpdateRequestDTO requestDTO) throws RstrListNotFoundException {
        RstrListEntity rstrList = rstrListRepository.findById(uuid).orElseThrow(() ->
                new RstrListNotFoundException(uuid)
        );

        rstrListEntityMapper.updateFromRequest(requestDTO, rstrList);

        return rstrListEntityMapper.toUpdateResponse(rstrList);
    }

    public RstrListListResponseDTO findListRstrList(UUID uuid) throws ParticipantInfoNotFoundException {
        ParticipantInfoEntity participantInfo = participantInfoRepository.findById(uuid).orElseThrow(() ->
                new ParticipantInfoNotFoundException(uuid)
        );
        List<RstrListEntity> rstrListEntities = participantInfo.getRstrList();

        if(rstrListEntities == null || rstrListEntities.isEmpty()){
            return new RstrListListResponseDTO();
        }

        return new RstrListListResponseDTO(
                rstrListEntityMapper.toList(rstrListEntities)
        );
    }

    public RstrListGetResponseDTO findRstrListByUuid(UUID uuid) throws RstrListNotFoundException {
        RstrListEntity rstrList = rstrListRepository.findById(uuid).orElseThrow(() ->
                new RstrListNotFoundException(uuid)
        );
        return rstrListEntityMapper.toGetResponse(rstrList);
    }
}
