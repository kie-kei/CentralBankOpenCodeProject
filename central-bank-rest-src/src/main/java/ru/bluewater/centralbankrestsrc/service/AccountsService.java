package ru.bluewater.centralbankrestsrc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccountsCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccountsUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccountsCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.AccountsListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccountsGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccountsUpdateResponseDTO;
import ru.bluewater.centralbankrestapi.api.exception.AccountsNotFoundException;
import ru.bluewater.centralbankrestapi.api.exception.BicDirectoryEntryNotFoundException;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.mapper.AccountsEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.AccountsRepository;
import ru.bluewater.centralbankrestsrc.respository.BICDirectoryEntryRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final AccountsEntityMapper accountsEntityMapper;
    private final BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Transactional
    public AccountsCreateResponseDTO createAccounts(UUID bicDirectoryEntryUuid, AccountsCreateRequestDTO requestDTO) throws BicDirectoryEntryNotFoundException {
        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryRepository.findById(bicDirectoryEntryUuid)
                .orElseThrow(() -> new BicDirectoryEntryNotFoundException(bicDirectoryEntryUuid));
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

    public AccountsListResponseDTO findListAccounts(UUID uuid) throws BicDirectoryEntryNotFoundException {
        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryRepository.findById(uuid)
                .orElseThrow(() -> new BicDirectoryEntryNotFoundException(uuid));

        List<AccountsEntity> accountsEntities = bicDirectoryEntry.getAccounts();
        if(accountsEntities == null || accountsEntities.isEmpty()){
            return new AccountsListResponseDTO();
        }

        return new AccountsListResponseDTO(
                accountsEntityMapper.toListResponse(accountsEntities)
        );
    }

    @Transactional
    public void deleteAccounts(UUID uuid) throws AccountsNotFoundException {
        AccountsEntity account = accountsRepository.findById(uuid).orElseThrow(AccountsNotFoundException::new);
        BICDirectoryEntryEntity bicDirectoryEntry = bicDirectoryEntryRepository.findByAccount(account);
        if (bicDirectoryEntry != null) {
            bicDirectoryEntry.getAccounts().remove(account);
            bicDirectoryEntryRepository.save(bicDirectoryEntry);
        }
        accountsRepository.delete(account);
    }
}
