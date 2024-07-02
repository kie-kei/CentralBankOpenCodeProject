package ru.bluewater.centralbankrestsrc.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankrestapi.api.dto.response.BICDirectoryEntryResponseDTO;
import ru.bluewater.centralbankrestsrc.entity.BICDirectoryEntryEntity;

@Mapper(componentModel = "spring")
public interface BICDirectoryEntryEntityMapper {
    BICDirectoryEntryResponseDTO toBICDirectoryEntryResponseDTO(BICDirectoryEntryEntity bicDirectoryEntryEntity);
}
