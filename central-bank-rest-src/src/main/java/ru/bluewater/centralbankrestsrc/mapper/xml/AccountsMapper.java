package ru.bluewater.centralbankrestsrc.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;
import ru.bluewater.centralbankrestsrc.dto.xml.Accounts;

@Mapper(componentModel = "spring")
public interface AccountsMapper {
    AccountsEntity toAccountsEntity(Accounts accounts);

    Accounts toAccounts(AccountsEntity accountsEntity);
}
