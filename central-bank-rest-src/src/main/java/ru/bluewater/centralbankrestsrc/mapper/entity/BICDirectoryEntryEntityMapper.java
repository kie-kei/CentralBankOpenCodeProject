package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.bluewater.centralbankrestapi.api.dto.request.create.BicDirectoryEntryCreateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.request.update.BicDirectoryEntryUpdateRequestDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.BICDirectoryEntryResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.create.BicDirectoryEntryCreateResponseDTO;
import ru.bluewater.centralbankrestapi.api.dto.response.update.BicDirectoryEntryUpdateResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;

@Mapper(componentModel = "spring")
public interface BICDirectoryEntryEntityMapper {
    BICDirectoryEntryResponseDTO toBICDirectoryEntryResponseDTO(BICDirectoryEntryEntity bicDirectoryEntryEntity);

    @Mapping(target = "uuid", ignore = true)
//    @Mapping(target = "participantInfo", ignore = true)
//    @Mapping(target = "accounts", ignore = true)
//    @Mapping(target = "swbics", ignore = true)
    void updateFromDto(BicDirectoryEntryUpdateRequestDTO updateRequestDTO, @MappingTarget BICDirectoryEntryEntity bicDirectoryEntry);

    BicDirectoryEntryUpdateResponseDTO toUpdateResponse(BICDirectoryEntryEntity bicDirectoryEntryEntity);

    @Mapping(target = "participantInfo", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "swbics", ignore = true)
    BICDirectoryEntryEntity toEntity(BicDirectoryEntryCreateRequestDTO bicDirectoryEntryCreateRequestDTO);

    BicDirectoryEntryCreateResponseDTO toCreateResponse(BICDirectoryEntryEntity bicDirectoryEntryEntity);
}
