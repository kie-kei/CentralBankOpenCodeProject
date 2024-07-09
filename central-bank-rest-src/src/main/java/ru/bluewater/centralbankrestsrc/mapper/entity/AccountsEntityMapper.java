package ru.bluewater.centralbankrestsrc.mapper.entity;

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
import ru.bluewater.centralbankrestsrc.entity.AccountsEntity;

@Mapper(componentModel = "spring")
public interface AccountsEntityMapper {
//    AccountsResponseDTO toAccountsResponseDTO(AccountsEntity accountsEntity);

    AccountsCreateResponseDTO toCreateResponse(AccountsEntity accountsEntity);
    @Mapping(target = "uuid", ignore = true)
//    @Mapping(target = "accRstrList", ignore = true)
    AccountsEntity toEntity(AccountsRequestDTO requestDTO);
    @Mapping(target = "uuid", ignore = true)
    AccountsEntity fromCreateRequestToEntity(AccountsCreateRequestDTO requestDTO);
    AccountsUpdateResponseDTO toUpdateResponse(AccountsEntity accountsEntity);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(AccountsUpdateRequestDTO requestDTO, @MappingTarget AccountsEntity entity);
    @Mapping(target = "uuid", ignore = true)
    void updateFromRequest(AccountsRequestDTO requestDTO, @MappingTarget AccountsEntity entity);
    AccountsGetResponseDTO toGetResponse(AccountsEntity accountsEntity);
}
