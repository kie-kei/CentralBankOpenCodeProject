package ru.bluewater.centralbankrestsrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankrestapi.api.dto.request.AccountsRequestDTO;
import ru.bluewater.centralbankrestsrc.entity.AccRstrListEntity;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankrestsrc.mapper.entity.AccRstrListEntityMapper;
import ru.bluewater.centralbankrestsrc.mapper.entity.AccountsEntityMapper;
import ru.bluewater.centralbankrestsrc.respository.AccountsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final AccRstrListService accRstrListService;
    private final AccountsEntityMapper accountsEntityMapper;
    private final AccRstrListEntityMapper accRstrListEntityMapper;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository, AccRstrListService accRstrListService, AccountsEntityMapper accountsEntityMapper, AccRstrListEntityMapper accRstrListEntityMapper) {
        this.accountsRepository = accountsRepository;
        this.accRstrListService = accRstrListService;
        this.accountsEntityMapper = accountsEntityMapper;
        this.accRstrListEntityMapper = accRstrListEntityMapper;
    }

    @Transactional
    public void createAccounts(AccountsEntity accountsEntity) {
        List<AccRstrListEntity> accRstrListEntityList = accountsEntity.getAccRstrList();

        if (accRstrListEntityList != null)
            accRstrListEntityList.forEach(accRstrList -> {
                accRstrList.setAccounts(accountsEntity);
                accRstrListService.createAccRstrList(accRstrList);
            });

        accountsRepository.save(accountsEntity);
    }

    public List<AccountsEntity> createNewAccountsEntityList(BICDirectoryEntryEntity bicDirectoryEntry, List<AccountsRequestDTO> requestDTOs) {
        if (bicDirectoryEntry.getAccounts() != null) {
            accountsRepository.deleteAll(bicDirectoryEntry.getAccounts());
        }

        List<AccountsEntity> newEntities = new ArrayList<>();

        requestDTOs.forEach(accountDTO -> {
            AccountsEntity accountsEntity = accountsEntityMapper.toEntity(accountDTO);

            accountsEntity.setBicDirectoryEntry(bicDirectoryEntry);

            List<AccRstrListEntity> accRstrListEntityList = new ArrayList<>();

            if (accountDTO.getAccRstrList() != null) {
                accountDTO.getAccRstrList().forEach(accRstrDTO -> {
                    AccRstrListEntity accRstrListEntity = accRstrListEntityMapper.toEntity(accRstrDTO);
                    accRstrListEntity.setAccounts(accountsEntity);
                    accRstrListEntityList.add(accRstrListEntity);
                });
            }

            accountsEntity.setAccRstrList(accRstrListEntityList);

            newEntities.add(accountsEntity);
        });

        return newEntities;
    }
}
