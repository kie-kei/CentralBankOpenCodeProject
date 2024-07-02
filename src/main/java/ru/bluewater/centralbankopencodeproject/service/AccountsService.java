package ru.bluewater.centralbankopencodeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.AccRstrListEntity;
import ru.bluewater.centralbankopencodeproject.entity.AccountsEntity;
import ru.bluewater.centralbankopencodeproject.respository.AccountsRepository;

import java.util.List;

@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final AccRstrListService accRstrListService;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository, AccRstrListService accRstrListService) {
        this.accountsRepository = accountsRepository;
        this.accRstrListService = accRstrListService;
    }

    @Transactional
    public void createAccounts(AccountsEntity accountsEntity){
        List<AccRstrListEntity> accRstrListEntityList = accountsEntity.getAccRstrList();

        if (accRstrListEntityList != null)
            accRstrListEntityList.forEach(accRstrList -> {
                accRstrList.setAccounts(accountsEntity);
                accRstrListService.createAccRstrList(accRstrList);
            });

        accountsRepository.save(accountsEntity);
    }
}
