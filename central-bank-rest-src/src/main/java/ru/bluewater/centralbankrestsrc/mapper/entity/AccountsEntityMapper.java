package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.bluewater.centralbankrestapi.api.dto.request.AccountsRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.AccountsResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;

@Mapper(componentModel = "spring")
public interface AccountsEntityMapper {
    AccountsResponseDTO toAccountsResponseDTO(AccountsEntity accountsEntity);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "accRstrList", ignore = true)
    @Mapping(target = "bicDirectoryEntry", ignore = true)
    AccountsEntity toEntity(AccountsRequestDTO requestDTO);
}
