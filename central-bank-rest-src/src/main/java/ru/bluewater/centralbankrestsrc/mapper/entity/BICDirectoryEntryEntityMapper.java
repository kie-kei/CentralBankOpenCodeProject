package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BICDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryFullUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.BICDirectoryEntryResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BICDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.list.BICDirectoryEntryListResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.read.BICDirectoryEntryGetResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BICDirectoryEntryEntityMapper {
    BICDirectoryEntryResponseDTO toBICDirectoryEntryResponseDTO(BICDirectoryEntryEntity bicDirectoryEntryEntity);

    @Mapping(target = "uuid", ignore = true)
//    @Mapping(target = "participantInfo", ignore = true)
//    @Mapping(target = "accounts", ignore = true)
//    @Mapping(target = "swbics", ignore = true)
    void updateFromDto(BicDirectoryEntryUpdateRequestDTO updateRequestDTO, @MappingTarget BICDirectoryEntryEntity bicDirectoryEntry);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "participantInfo", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "swbics", ignore = true)
    void updateFromDto(BicDirectoryEntryFullUpdateRequestDTO updateRequestDTO, @MappingTarget BICDirectoryEntryEntity bicDirectoryEntry);

    BicDirectoryEntryUpdateResponseDTO toUpdateResponse(BICDirectoryEntryEntity bicDirectoryEntryEntity);

//    @Mapping(target = "participantInfo", ignore = true)
//    @Mapping(target = "accounts", ignore = true)
//    @Mapping(target = "swbics", ignore = true)
    BICDirectoryEntryEntity fromCreateRequestToEntity(
            BICDirectoryEntryCreateRequestDTO bicDirectoryEntryCreateRequestDTO
    );

    BICDirectoryEntryCreateResponseDTO toCreateResponse(BICDirectoryEntryEntity bicDirectoryEntryEntity);
    BICDirectoryEntryEntity fromUpdateRequestToEntity(BicDirectoryEntryUpdateRequestDTO requestDTO);
    BICDirectoryEntryGetResponseDTO toGetResponse(BICDirectoryEntryEntity entity);

    List<BICDirectoryEntryGetResponseDTO> toListGetResponse(List<BICDirectoryEntryEntity> entryEntityList);

    List<BICDirectoryEntryResponseDTO> toListResponse(List<BICDirectoryEntryEntity> entryEntityList);
}
