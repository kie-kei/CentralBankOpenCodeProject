package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.AccountsRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccountsCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccountsUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccountsCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccountsGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccountsUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.AccountsNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.mapper.entity.AccRstrListEntityMapper;
import ru.bluewater.centralbankrestsrc.mapper.entity.AccountsEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.AccountsRepository;
import ru.bluewater.centralbankrestsrc.respository.BICDirectoryEntryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final AccountsEntityMapper accountsEntityMapper;
//    private final AccRstrListEntityMapper accRstrListEntityMapper;
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Transactional
    public AccountsCreateResponseDTO createAccounts(AccountsCreateRequestDTO requestDTO) throws BicDirectoryEntryNotFoundException {
        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryRepository.findById(
                requestDTO.getBicDirectoryEntryUuid()).orElseThrow(() ->
                new BicDirectoryEntryNotFoundException(requestDTO.getBicDirectoryEntryUuid())
        );
        AccountsEntity accountsEntity = accountsRepository.save(
                accountsEntityMapper.fromCreateRequestToEntity(requestDTO)
        );

        bicDirectoryEntry.getAccounts().add(accountsEntity);

        return accountsEntityMapper.toCreateResponse(accountsEntity);
    }

    @Transactional
    public AccountsUpdateResponseDTO updateAccounts(
            UUID uuid,
            AccountsUpdateRequestDTO requestDTO
    ) throws AccountsNotFoundException {
        AccountsEntity accountsEntity = accountsRepository.findById(uuid).orElseThrow(() ->
                new AccountsNotFoundException(uuid)
        );

        accountsEntityMapper.updateFromRequest(requestDTO, accountsEntity);

        return accountsEntityMapper.toUpdateResponse(accountsRepository.save(accountsEntity));
    }

    public AccountsGetResponseDTO findAccountsByUuid(UUID uuid) throws AccountsNotFoundException {
        AccountsEntity accountsEntity = accountsRepository.findById(uuid).orElseThrow(() ->
                new AccountsNotFoundException(uuid)
        );
        return accountsEntityMapper.toGetResponse(accountsEntity);
    }

//    public void deleteAccounts(UUID uuid) throws AccountsNotFoundException {
//        accountsRepository.findById(uuid).ifPresent(account -> {
//            BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryRepository.findByAccount(account);
//            if (bicDirectoryEntry != null) {
//                bicDirectoryEntry.getAccounts().remove(account);
//                bicDirectoryEntryRepository.save(bicDirectoryEntry);
//            }
//            accountsRepository.delete(account);
//        });
////        accountsRepository.deleteById(uuid);
//    }




//    @Transactional
//    public void createAccounts(AccountsEntity accountsEntity) {
//        List<AccRstrListEntity> accRstrListEntityList = accountsEntity.getAccRstrList();
//
//        if (accRstrListEntityList != null)
//            accRstrListEntityList.forEach(accRstrListService::createAccRstrList);
//
//        accountsRepository.save(accountsEntity);
//    }

//    public List<AccountsEntity> createNewAccountsEntityList(BICDirectoryEntryEntity bicDirectoryEntry, List<AccountsRequestDTO> requestDTOs) {
//        if (bicDirectoryEntry.getAccounts() != null) {
//            accountsRepository.deleteAll(bicDirectoryEntry.getAccounts());
//        }
//
//        List<AccountsEntity> newEntities = new ArrayList<>();
//
//        requestDTOs.forEach(accountDTO -> {
//            AccountsEntity accountsEntity = accountsEntityMapper.toEntity(accountDTO);
//
//
//            List<AccRstrListEntity> accRstrListEntityList = new ArrayList<>();
//
//            if (accountDTO.getAccRstrList() != null) {
//                accountDTO.getAccRstrList().forEach(accRstrDTO -> {
//                    AccRstrListEntity accRstrListEntity = accRstrListEntityMapper.toEntity(accRstrDTO);
//                    accRstrListEntityList.add(accRstrListEntity);
//                });
//            }
//
//            accountsEntity.setAccRstrList(accRstrListEntityList);
//
//            newEntities.add(accountsEntity);
//        });
//
//        return newEntities;
//    }
}
