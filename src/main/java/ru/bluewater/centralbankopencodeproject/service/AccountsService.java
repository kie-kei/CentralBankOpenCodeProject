package ru.bluewater.centralbankopencodeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bluewater.centralbankopencodeproject.entity.AccountsEntity;
import ru.bluewater.centralbankopencodeproject.respository.AccountsRepository;

@Service
public class AccountsService {
    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Transactional
    public void createAccounts(AccountsEntity accountsEntity){
        accountsRepository.save(accountsEntity);
    }
}
