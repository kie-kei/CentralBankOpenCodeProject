package ru.bluewater.centralbankopencodeproject.mapper.entity;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.api.dto.response.BICDirectoryEntryResponseDTO;
import ru.bluewater.centralbankopencodeproject.entity.BICDirectoryEntryEntity;

@Mapper(componentModel = "spring")
public interface BICDirectoryEntryEntityMapper {
    BICDirectoryEntryResponseDTO toBICDirectoryEntryResponseDTO(BICDirectoryEntryEntity bicDirectoryEntryEntity);
}
