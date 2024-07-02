package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.AccountsResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;

@Mapper(componentModel = "spring")
public interface AccountsEntityMapper {
    AccountsResponseDTO toAccountsResponseDTO(AccountsEntity accountsEntity);
}
