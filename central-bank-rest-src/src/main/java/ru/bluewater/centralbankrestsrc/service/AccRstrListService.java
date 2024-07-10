package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccRstrListCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccRstrListUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccRstrListCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.AccRstrListListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccRstrListGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccRstrListUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.AccRstrListNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.AccountsNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.entity.ED807Entity;
import ru.bluewater.centralbankrestsrc.mapper.AccRstrListEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.AccRstrListRepository;
import ru.bluewater.centralbankrestsrc.respository.AccountsRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccRstrListService {
    private final AccRstrListRepository accRstrListRepository;
    private final AccountsRepository accountsRepository;
    private final AccRstrListEntityMapper accRstrListMapper;

    @Transactional
    public AccRstrListCreateResponseDTO createAccRstrList(
            UUID accountsUuid,
            AccRstrListCreateRequestDTO requestDTO
    ) throws AccountsNotFoundException {
        AccountsEntity accountsEntity = accountsRepository.findById(accountsUuid).orElseThrow(() ->
                new AccountsNotFoundException(accountsUuid));

        AccRstrListEntity accRstrListEntity = accRstrListRepository.save(
                accRstrListMapper.fromCreateRequestToEntity(requestDTO)
        );
        accountsEntity.getAccRstrList().add(accRstrListEntity);

        return accRstrListMapper.toCreateResponse(accRstrListEntity);
    }

    @Transactional
    public AccRstrListUpdateResponseDTO updateAccRstrList(
            UUID uuid,
            AccRstrListUpdateRequestDTO requestDTO
    ) throws AccRstrListNotFoundException {
        AccRstrListEntity accRstrList = accRstrListRepository.findById(uuid).orElseThrow(() ->
                new AccRstrListNotFoundException(uuid));

        accRstrListMapper.updateFromRequest(requestDTO, accRstrList);

        return accRstrListMapper.toUpdateResponse(accRstrList);
    }

    public AccRstrListGetResponseDTO findAccRstrListByUuid(UUID uuid) throws AccRstrListNotFoundException {
        AccRstrListEntity accRstrListEntity = accRstrListRepository.findById(uuid).orElseThrow(() ->
                new AccRstrListNotFoundException(uuid)
        );

        return accRstrListMapper.toGetResponse(accRstrListEntity);
    }

    public AccRstrListListResponseDTO findListAccRstrList(UUID uuid) throws AccountsNotFoundException {
        AccountsEntity accountsEntity = accountsRepository.findById(uuid)
                .orElseThrow(() -> new AccountsNotFoundException(uuid));
        List<AccRstrListEntity> accRstrListEntityList = accountsEntity.getAccRstrList();

        if (accRstrListEntityList == null || accRstrListEntityList.isEmpty())
            return new AccRstrListListResponseDTO();

        return new AccRstrListListResponseDTO(
                accRstrListMapper.toListResponse(accRstrListEntityList)
        );
    }

    @Transactional
    public void deleteAccRstrList(UUID uuid) throws AccRstrListNotFoundException {
        AccRstrListEntity accRstrListEntity = accRstrListRepository.findById(uuid)
                .orElseThrow(() -> new AccRstrListNotFoundException(uuid));

        AccountsEntity accounts = accountsRepository.findByAccRstrList(accRstrListEntity);
        if (accounts != null) {
            accounts.getAccRstrList().remove(accRstrListEntity);
            accountsRepository.save(accounts);
        }
        accRstrListRepository.delete(accRstrListEntity);
    }
}
