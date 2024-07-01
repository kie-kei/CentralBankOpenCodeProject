package ru.bluewater.centralbankopencodeproject.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.response.AccountsResponseDTO;
import ru.bluewater.centralbankopencodeproject.entity.AccountsEntity;

@Mapper(componentModel = "spring")
public interface AccountsEntityMapper {
    AccountsResponseDTO toAccountsResponseDTO(AccountsEntity accountsEntity);
}
