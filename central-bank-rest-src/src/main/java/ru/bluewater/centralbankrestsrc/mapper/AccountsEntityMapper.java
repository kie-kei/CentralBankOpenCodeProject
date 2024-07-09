package ru.bluewater.centralbankrestsrc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.AccountsRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.create.AccountsCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.AccountsUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.AccountsResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.AccountsCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.AccountsGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.AccountsUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.dto.xml.Accounts;
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountsEntityMapper {
    AccountsEntity toAccountsEntity(Accounts accounts);

    Accounts toAccounts(AccountsEntity accountsEntity);
    AccountsCreateResponseDTO toCreateResponse(AccountsEntity accountsEntity);
    @Mapping(target = "uuid", ignore = true)
    AccountsEntity toEntity(AccountsRequestDTO requestDTO);
    @Mapping(target = "uuid", ignore = true)
    AccountsEntity fromCreateRequestToEntity(AccountsCreateRequestDTO requestDTO);
    AccountsUpdateResponseDTO toUpdateResponse(AccountsEntity accountsEntity);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(AccountsUpdateRequestDTO requestDTO, @MappingTarget AccountsEntity entity);
    AccountsGetResponseDTO toGetResponse(AccountsEntity accountsEntity);
    List<AccountsGetResponseDTO> toListResponse(List<AccountsEntity> accountsEntityList);
}
