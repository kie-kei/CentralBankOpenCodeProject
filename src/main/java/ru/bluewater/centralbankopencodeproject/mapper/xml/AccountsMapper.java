package ru.bluewater.centralbankopencodeproject.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.entity.AccountsEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.Accounts;

@Mapper(componentModel = "spring")
public interface AccountsMapper {
    AccountsEntity toAccountsEntity(Accounts accounts);
    Accounts toAccounts(AccountsEntity accountsEntity);
}
