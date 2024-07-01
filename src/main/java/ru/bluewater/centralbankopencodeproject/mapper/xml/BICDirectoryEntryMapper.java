package ru.bluewater.centralbankopencodeproject.mapper.xml;

import org.mapstruct.Mapper;
import ru.bluewater.centralbankopencodeproject.entity.BICDirectoryEntryEntity;
import ru.bluewater.centralbankopencodeproject.entity.xml.BICDirectoryEntry;

@Mapper(componentModel = "spring")
public interface BICDirectoryEntryMapper {
    BICDirectoryEntryEntity toBICDirectoryEntryEntity(BICDirectoryEntry bicDirectoryEntry);
    BICDirectoryEntry toBICDirectoryEntry(BICDirectoryEntryEntity bicDirectoryEntryEntity);
}
